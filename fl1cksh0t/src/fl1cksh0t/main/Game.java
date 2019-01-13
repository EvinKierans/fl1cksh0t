package fl1cksh0t.main;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = -3316329364487365225L;

	public static final int WIDTH = 800, HEIGHT = 600;
	
	public Game()
	{
		new Window(WIDTH, HEIGHT, "fl1cksh0t v3rs10n: V3ry B374", this);
	}
	
	public synchronized void start()
	{
		
	}
	
	public void run()
	{
		
	}
	
	public static void main(String args[])	//main function
	{
		new Game();
	}
	
}
