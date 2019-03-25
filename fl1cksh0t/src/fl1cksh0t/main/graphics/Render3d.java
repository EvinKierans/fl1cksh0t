package fl1cksh0t.main.graphics;

import fl1cksh0t.main.Game;
import fl1cksh0t.main.input.Controller;

//This class renders the 3D environment - the below is standard practice for doing so
public class Render3d extends Render {

    private double renderDistance = 7500;

    //array that takes on depth
    public double[] zBuffer;

	public Render3d(int width, int height) {
		super(width, height);
		zBuffer = new double[width*height];
	}

	//Draws the floor of our game
	public void floor(Game game) {

		double floorPosition = 8;
		double ceilingPosition = 50;
		double forward = game.controls.z;
		double right = game.controls.x;
		double up = game.controls.y;
		double viewbobMove = Math.sin(game.time / 6.0) * 0; //default bob cycle -> walking

		if(Controller.sprintMove) {
			viewbobMove = Math.sin(game.time / 6.0) * 0.8;
		}
		if(Controller.proneMove) {
			viewbobMove = Math.sin(game.time / 6.0) * 0.3;
		}
		if(Controller.crouchMove) {
			viewbobMove = Math.sin(game.time / 6.0) * 0.2;
		}

		double horizontalRotation =  game.controls.rotation;
		double cosine = Math.cos(horizontalRotation);
		double sine = Math.sin(horizontalRotation);

		for (int y = 0; y < height; y++) {
			double ceiling = (y - height / 2.0) / height;

			double z = (floorPosition + up) / ceiling;

			//fixes confusing floor and ceiling clash
			if(ceiling < 0) {
				z = (ceilingPosition - up) / -ceiling;
			}

			//Movement bobbing mechanics
			if(Controller.walkMove == true) {
				z = (floorPosition + up + viewbobMove) / ceiling;

				//fixes confusing floor and ceiling clash
				if(ceiling < 0) {
					z = (ceilingPosition - up - viewbobMove) / -ceiling;
				}
			}

			for (int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height;
				depth *= z;

				double xx = depth * cosine + z * sine;
				double yy = z * cosine - depth * sine;

				int xPix = (int) (xx + right);
				int yPix = (int) (yy + forward);

				zBuffer[x + y * width] = z;

				//textures need rework
				//Error is as follows
				//Exception in thread "Thread-0" java.lang.ExceptionInInitializerError
				pixels[x + y * width] = ((xPix & 15) * 16) | ( (yPix & 15) * 16)  << 8;
				//pixels[x + y * width] = Texture.floor.pixels[(xPix & 7) + (yPix & 7) * 8];

				//ultimate render distance limitation
				if(z > renderDistance) {
					pixels[x + y * width] = 0;
				}
			}
		}
	}

	//beta pixel by pixel render distance limiter - not as brute force
	public int renderDistancePixel() {

		int colour = 0;
		int brightness = 0;

		for(int i = 0; i < width * height; i++) {
			colour = pixels[i];
			brightness = (int) (renderDistance / (zBuffer[i]));
		}

		if (brightness < 0) brightness = 0;
		if (brightness > 255) brightness = 255;

		int r = (colour >> 16) & 0xff;
		int g = (colour >> 8) & 0xff;
		int b = (colour) & 0xff;

		r = r * brightness / 255;
		g = g * brightness / 255;
		b = b * brightness / 255;

		return r << 16 | g << 8 | b;
	}

	//gradient for fade / fog
	public void renderDistanceLimiter() {
		for(int i = 0; i < width * height; i++) {
			int colour = pixels[i];
			int brightness = (int) (renderDistance / (zBuffer[i]));

			//sets ultimate minimum for brightness
			if(brightness < 0) {
				brightness = 0;
			}
			//sets ultimate maximum for brightness
			if(brightness > 255) {
				brightness = 255;
			}

			int r = (colour >> 16) & 0xff;
			int g = (colour >> 8) & 0xff;
			int b = (colour) & 0xff;

			r = r * brightness/255;
			g = g * brightness/255;
			b = b * brightness/255;

			pixels[i] = r << 16 | g << 8 | b;
		}
	}
}
