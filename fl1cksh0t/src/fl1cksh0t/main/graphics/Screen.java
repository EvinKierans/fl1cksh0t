package fl1cksh0t.main.graphics;

import java.util.Random;

import fl1cksh0t.main.Game;

public class Screen extends Render
{
	private Render test;
	
	public Screen(int width, int height)
	{
		super(width, height);
		Random random = new Random();
		test = new Render(256, 256);
		for(int i = 0; i < 256*256; i++)
		{
			test.pixels[i] = random.nextInt() * (random.nextInt(5) / 4);	
		}
	}
	
	public void render(Game game)
	{		
		//double rotationPeriod = 1;
		
		for(int i = 0; i < width*height; i++)
		{
			pixels[i] = 0;
		}

		for(int i = 0; i< 50; i++)
		{
			int animX = (int) (Math.sin((game.time + i*2) % 1000.0 / 100) * 100);
			int animY = (int) (Math.cos((game.time + i*2) % 1000.0 / 100) * 100);	//+i*100 is a genius part to optimize performance. When i is 1000 we get ~45-50 fps. When i is 10 * 100 we get 1100 fps

			draw(test, (width - 256 ) / 2 + animX, (height - 256) / 2 + animY);	//testing render from Render.java
		}
	}
}
