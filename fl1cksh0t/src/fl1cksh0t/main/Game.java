package fl1cksh0t.main;

import fl1cksh0t.main.input.Controller;

import java.awt.event.KeyEvent;

public class Game {
    //Another instance of time in the game
	public int time;
	public Controller controls;


	public Game() {
		controls = new Controller();
	}

	//ticks in game
	public void tick(boolean[] key) {
		//Changes speed of rotation
		time++;

		boolean forward = key[KeyEvent.VK_W];
		boolean back = key[KeyEvent.VK_S];
		boolean right = key[KeyEvent.VK_D];
		boolean left = key[KeyEvent.VK_A];
		boolean turnRightKEY = key[KeyEvent.VK_RIGHT];
		boolean turnLeftKEY = key[KeyEvent.VK_LEFT];
		boolean turnUpKEY = key[KeyEvent.VK_UP];
		boolean turnDownKEY = key[KeyEvent.VK_DOWN];
		boolean jump = key[KeyEvent.VK_SPACE];
		boolean crouch = key[KeyEvent.VK_CONTROL];
		boolean sprint = key[KeyEvent.VK_SHIFT];
		boolean prone = key[KeyEvent.VK_C];

		controls.tick(forward, back, right, left, turnRightKEY, turnLeftKEY, turnUpKEY, turnDownKEY, jump, crouch, sprint, prone);
	}
}
