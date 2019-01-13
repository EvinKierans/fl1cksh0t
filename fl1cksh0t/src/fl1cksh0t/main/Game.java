package fl1cksh0t.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = -3316329364487365225L;

	public static final int WIDTH = 900, HEIGHT = 600;
	public static final String TITLE = "fl1cksh0t - Pre-Alpha v0.01";
	
	private Thread thread;
	private boolean running = false;

	
	public Game()
	{
		new Window(WIDTH, HEIGHT, TITLE, this);	//creating game window in game class
	}
	
	public synchronized void start()
	{
		if (running) 
		{
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		
		System.out.println("Operation Flickshot is a go");
	}
	
	private void stop()
	{
		if (!running)
		{
			return;
		}
		running = false;
		try
		{
			thread.join();	//waits for thread to DIE
			running = false;
		}catch (Exception e)	//if it wont die, make it with programming bullshit!
		 {
			e.printStackTrace();	//will tell us why it wont quit in console
			System.exit(0);
		 }
		
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running == true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			if(running == true)
			{
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS = " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick()
	{
		
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.green);
		g.fillRect(0, 0, 300, 600);
		g.setColor(Color.white);
		g.fillRect(300, 0, 300, 600);
		g.setColor(Color.orange);
		g.fillRect(600, 0, 300, 600);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[])	//main function
	{
		new Game();
	}
	
}
