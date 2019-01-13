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
		int anim = (int) (Math.sin(System.currentTimeMillis() % 1000.0 / 1000 * Math.PI * 2) * 100);	//makes it move and jingle :O
		draw(test, (width - 256 ) / 2 + anim, (height - 256) / 2 + anim);	//testing render from Render.java
	}
}
