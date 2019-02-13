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
		boolean turnRight = key[KeyEvent.VK_RIGHT];
		boolean turnLeft = key[KeyEvent.VK_LEFT];

		controls.tick(forward, back, right, left, turnRight, turnLeft);
	}
}
