package fl1cksh0t.main.input;

import fl1cksh0t.main.Display;

import java.awt.*;

import static fl1cksh0t.main.input.InputHandler.lastClickTime;
import static java.awt.event.KeyEvent.VK_SPACE;

public class Controller {
    private double xa, ya, za, rotationa, verticalRotationa;
    public double rotation, verticalRotation, x, y, z;

    public static boolean turnLeft = false;
    public static boolean turnRight = false;
    public static boolean turnUp = false;
    public static boolean turnDown = false;

    public static boolean walkMove = false;
    public static boolean sprintMove = false;
    public static boolean crouchMove = false;
    public static boolean proneMove = false;

    public double sensitivity = 2.0;

    public void tick (boolean forward, boolean back, boolean right, boolean left, boolean turnLeftKEY, boolean turnRightKEY, boolean turnUpKEY, boolean turnDownKEY, boolean jump, boolean crouch, boolean sprint, boolean prone) {

        //m_yaw or sensitivity for mouse
        double xrotationSpeed = 0.00022 * Display.XmouseSpeed * sensitivity;
        double yrotationSpeed = 0.022 * Display.YmouseSpeed;

        //speed for keyboard
        double xKeyrotationSpeed = 0.022;
        double yKeyrotationSpeed = 0.022;

        //Movement variables
        double walkSpeed = 0.5;
        double xMove = 0;
        double zMove = 0;
        double crouchDepth = 0.5;
        double proneDepth = 0.75;
        double jumpHeight = 1.75;
        long now;
        long jumpCooldown = 500;

        if(forward) {
            zMove++;
            if(forward && !jump){
                walkMove = true;
            } else {
                walkMove = false;
            }
        }

        if(back) {
            zMove--;
            walkMove = true;
        }

        if(right) {
            xMove++;
            walkMove = true;
        }

        if(left) {
            xMove--;
            walkMove = true;
        }

        if(!forward && !back && !right && !left) {
            walkMove = false;
        }

        if(turnLeft) {
            if(InputHandler.mouseButton == 3) {

            } else {
                rotationa -= xrotationSpeed;
            }
        }

        if(turnRight) {
            if(InputHandler.mouseButton == 3) {

            } else {
                rotationa += xrotationSpeed;
            }
        }

        if(turnUp) {
            verticalRotationa += yrotationSpeed;
        }

        if(turnDown) {
            verticalRotationa -= yrotationSpeed;
        }

        if(turnLeftKEY) {
            rotationa += xKeyrotationSpeed;
        }

        if(turnRightKEY) {
            rotationa -= xKeyrotationSpeed;
        }

        if(turnUpKEY) {
            rotationa += yKeyrotationSpeed;
        }

        if(turnDownKEY) {
            rotationa -= yKeyrotationSpeed;
        }

        if(jump) {
            now = System.currentTimeMillis();
            //Jump cooldown
            if(now - lastClickTime > jumpCooldown) {
                //jump height needs serious adjusting
                y += jumpHeight*2;
                //Key release so fall after jump
                try {
                    Robot r = new Robot();
                    r.keyRelease(VK_SPACE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                lastClickTime = System.currentTimeMillis();
                return;
            } else {
                return;
            }

        }

        if(crouch) {
            y -= crouchDepth;
            walkSpeed = walkSpeed / 3;
            crouchMove = true;
        } else if(prone) {
            y -= proneDepth;
            walkSpeed = walkSpeed / 5;
            proneMove = true;
        }

        if(sprint) {
            walkSpeed = walkSpeed * 2.5;
            sprintMove = true;
        }

        if(!crouch) {
            crouchMove = false;
        }
        if(!sprint) {
            sprintMove = false;
        }
        if(!prone) {
            proneMove = false;
        }

        xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
        za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;

        x += xa;
        y += ya;
        z += za;

        xa *= 0.1;
        ya *= 0.1;
        za *= 0.1;

        y *= 0.9;

        verticalRotation = verticalRotationa;
        verticalRotationa *= 0.5;

        rotation += rotationa;
        rotationa *= 0.5;
    }

}
