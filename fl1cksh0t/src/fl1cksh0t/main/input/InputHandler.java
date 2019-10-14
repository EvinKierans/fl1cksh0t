package fl1cksh0t.main.input;

import fl1cksh0t.main.Display;

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
	Robot r;

	public void CenterMouse()
	{
		try {
			r = new Robot();
			r.mouseMove((Display.WIDTH/2), (Display.HEIGHT/2));
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//CenterMouse();
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if(mouseX <= 0 || mouseX >= Display.WIDTH - 1) {
			CenterMouse();
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
