package fl1cksh0t.main.graphics;

import java.util.Random;

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
			test.pixels[i] = random.nextInt();	
		}
	}
	
	public void render()
	{		
		int rotationSpeed = 1;
		
		for(int i = 0; i < width*height; i++)
		{
			pixels[i] = 0;
		}

		for(int i = 0; i< 20; i++)
		{
			int animX = (int) (Math.sin((System.currentTimeMillis()+i*10) % 1000.0 / 1000 * Math.PI * (rotationSpeed * 2)) * 200);	//makes it move and jingle :O
			int animY = (int) (Math.cos((System.currentTimeMillis()+i*10) % 1000.0 / 1000 * Math.PI * (rotationSpeed * 2)) * 200);	//+i*100 is a genius part to optimise performance. When i is 1000 we get ~45-50 fps. When i is 10 * 100 we get 1100 fps
			draw(test, (width - 256 ) / 2 + animX, (height - 256) / 2 + animY);	//testing render from Render.java
		}
	}
}
