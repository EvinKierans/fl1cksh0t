package fl1cksh0t.main.input;

import fl1cksh0t.main.Display;

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

    public static boolean jumping = false;
    double initialHeight = 0;

    public double maximumJumpHeight = 1;
    public double jumpHeightIndex = 0.1;
    public long now;
    public long lastJumpTime = 0;
    public long jumpCooldown = 500;

    public void tick (boolean forward, boolean back, boolean right, boolean left, boolean turnLeftKEY, boolean turnRightKEY, boolean turnUpKEY, boolean turnDownKEY, boolean jump, boolean crouch, boolean sprint, boolean prone) {

        //m_yaw or sensitivity for mouse
        double xrotationSpeed = 0.0022 * Display.XmouseSpeed;
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

        //TODO
        if(jump) {
            y += maximumJumpHeight;

            initialHeight = y;

//            now = System.currentTimeMillis();
//            System.out.println("Jumping = true (in jump)");
//            jumpCooldownChecker();
//            System.out.println("jumping = false (in jump)");
//            lastJumpTime = System.currentTimeMillis();
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

//    public void jump(double initialHeight) {
//
//        System.out.println("Jump rising");
//        while (y < initialHeight + maximumJumpHeight) {
//            System.out.println("Jump++++");
//            y += jumpHeightIndex;
//            if (y == initialHeight + maximumJumpHeight) {
//                jumping = false;
//                jumpFall(initialHeight);
//            }
//        }
//    }
//
//    public void jumpFall(double initialHeight) {
//        System.out.println("Jump falling ------------------");
//        while(y > initialHeight){
//            System.out.println("Jump----");
//            y-=jumpHeightIndex;
//        }
//    }
//
//    public void jumpCooldown() {
//        System.out.println("Jumping Cooldown Activated");
//    }
//
//    public void jumpCooldownChecker() {
//        if(now - lastJumpTime > jumpCooldown) {
//            System.out.println("Jump not cooled");
//            jump(initialHeight);
//        } else {
//            jumpCooldown();
//            return;
//        }
//    }
}
