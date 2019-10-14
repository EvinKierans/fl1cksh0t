package fl1cksh0t.main.input;

import fl1cksh0t.main.Display;
import fl1cksh0t.main.Game;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {

	//key input boolean
	public boolean[] key = new boolean[68836];
	public static int mouseX;
	public static int mouseY;
	public static int mouseButton;
	public static long lastClickTime = 0;
	private int lastX, lastY;
	private boolean isRecentering = false;
	Robot r;

	public void CenterMouse() {

	}

	// void drawMouseAim(Graphics g) {
	// 	g.setColor(Color.black);
	// 	g.drawLine((int)(DDDTutorial.ScreenSize.getWidth()/2 - aimSight), (int)(DDDTutorial.ScreenSize.getHeight()/2), (int)(DDDTutorial.ScreenSize.getWidth()/2 + aimSight), (int)(DDDTutorial.ScreenSize.getHeight()/2));
	// 	g.drawLine((int)(DDDTutorial.ScreenSize.getWidth()/2), (int)(DDDTutorial.ScreenSize.getHeight()/2 - aimSight), (int)(DDDTutorial.ScreenSize.getWidth()/2), (int)(DDDTutorial.ScreenSize.getHeight()/2 + aimSight));			
	// }

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		//CenterMouse();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			r = new Robot();
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
		mouseX += e.getX() - (int)(Display.WIDTH/2);
		mouseY += e.getY() - (int)(Display.HEIGHT/2);
		if (isRecentering && (int)(Display.WIDTH/2) == e.getX() && (int)(Display.HEIGHT/2) == e.getY())
		{
			isRecentering = false;
		}
		else// legitimate mouse event
		{
			//Calculate relative movement
			mouseX = e.getX() - (int)(Display.WIDTH/2);
			mouseY = e.getY() - (int)(Display.HEIGHT/2);

			//Recenter mouse and 'disable' next mouse move
			isRecentering = true;
			//r.mouseMove((int)(Display.WIDTH/2), (int)(Display.HEIGHT/2));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseButton = e.getButton();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		for(int i = 0; i < key.length; i++) {
			key[i] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode > 0 && keyCode < key.length) {
			key[keyCode] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode > 0 && keyCode < key.length) {
			key[keyCode] = false;
		}
	}
}
