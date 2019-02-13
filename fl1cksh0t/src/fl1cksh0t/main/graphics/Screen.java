package fl1cksh0t.main.graphics;

import java.util.Random;
import fl1cksh0t.main.Game;

public class Screen extends Render {
	private Render test;
	private Render3d render;
	
	public Screen(int width, int height) {
		super(width, height);
		Random random = new Random();
		render = new Render3d(width, height);
		test = new Render(256, 256);
		for(int i = 0; i < 256*256; i++) {
			test.pixels[i] = random.nextInt() * (random.nextInt(5) / 4);	
		}
	}
	
	public void render(Game game) {
		//Fills out pixel array and draws to screen
		for(int i = 0; i < width*height; i++) {
			pixels[i] = 0;
		}

		//Renders the floor as declared in Render3D
		render.floor(game);
		render.renderDistanceLimiter();
		draw(render, 0, 0);
	}
}
