//fl1cksh0t will be a first person arena shooter type game made by Evin Kierans

package fl1cksh0t.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

//Import the graphics package screen
import fl1cksh0t.main.graphics.Screen;
import fl1cksh0t.main.input.Controller;
import fl1cksh0t.main.input.InputHandler;

public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = -3316329364487365225L;

	//Declaring resolution and window title + version
	public static final int WIDTH = 1280, HEIGHT = 720;
	public static final String TITLE = "fl1cksh0t - Pre-Alpha v0.1";

	//private variables for making the game
	private Thread thread;					//Optimizing CPU performance
	private Screen screen;					//Instance of Screen
	private Game game;						//Instance of game
	private boolean running = false;		//Boolean function to see if program is running or not
	private BufferedImage img;				//basic buffered image
	private int[] pixels;					//pixel array for updating vision
	private InputHandler input;				//Instance of input handler
	private int newX = 0;					//value for recording X location
	private int oldX = 0;					//value for recording X location
	private int newY = 0;					//value for recording Y location
	private int oldY = 0;					//value for recording Y location
	private int fps; 						//int for graphic fps counter

	public static double XmouseSpeed;
	public static double YmouseSpeed;

	public Display() {
		//Creating an instance of the program window, and defining the screen for it
		new Window(WIDTH, HEIGHT, TITLE, this);
		screen = new Screen(WIDTH, HEIGHT);
		game = new Game();
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();

		input = new InputHandler();
		addKeyListener(input);
		addFocusListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);

	}

	//Function to start the game
	public synchronized void start() {
		//Checks to make sure it's running successfully
		if (running) {
			return;
		}
		//Setting program onto thread
		running = true;
		thread = new Thread(this);
		thread.start();
		
		System.out.println("Operation Flickshot is a go");
	}

	//Function to finish the game
	private void stop() {
		System.out.println("Game finito");
		if (!running) {
			return;
		}
		running = false;
		//Try and Catch function to see if the game fails to quit
		try {
			thread.join();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	public void run() {
		//Game loop - counts FPS and ticks the tock
		long lastTime = System.nanoTime();
		try {
		    Thread.sleep(100);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		double amountOfTicks = 100.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running == true) {
			requestFocus();
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				delta--;
			}

			if(running == true) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS = " + frames);
				fps = frames;
				frames = 0;	//comment this out if you want 'total frames rendered'
			}
			//Print statement for mouse coords
			System.out.println("(X, Y): (" + InputHandler.mouseX + ", " + InputHandler.mouseY + ")");

			//X-Axis movement
			newX = InputHandler.mouseX;
			if (newX > oldX) {
				//System.out.println("Right!!!");
				Controller.turnRight = true;
			} else if (newX < oldX) {
				//System.out.println("Left!!!");
				Controller.turnLeft = true;
			} else if (newX == oldX) {
				//System.out.println("Still!!!");
				Controller.turnLeft = false;
				Controller.turnRight = false;
			}

			XmouseSpeed = Math.abs(newX - oldX);
			//reset X to relative position
			oldX = newX;

			//Y-Axis movement
			newY = InputHandler.mouseY;
			if (newY < oldY) {
				//System.out.println("Up!!!");
				Controller.turnDown = true;
			} else if (newY > oldY) {
				Controller.turnUp = true;
				//System.out.println("Down!!!");
			} else if (newY == oldY) {
				//System.out.println("Still!!!");
				Controller.turnUp = false;
				Controller.turnDown = false;
			}

			YmouseSpeed = Math.abs(newY - oldY);
			//reset Y to relative position
			oldY = newY;
		}
		stop();
	}

	//Ticks the game loop
	private void tick() {
		game.tick(input.key);
	}

	//Render method allows us to update the screen on the window
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		//only want bs to run once, as it's in the render function, which is called repeatedly
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		//Render the instance of game onto the screen
		screen.render(game);

		//loop fills out the pixels
		for (int i = 0; i<WIDTH * HEIGHT; i++) {
			pixels[i] = screen.pixels[i];
		} 

		//draws nothing
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g.setFont(new Font("Verdanna", 1, 16));
		g.setColor(Color.YELLOW);
		g.drawString( fps+" FPS", 10,20);
		g.dispose();
		bs.show();
	}

	//Main function
	public static void main(String args[]) {	//main function
		new Display();
	}
	
}
