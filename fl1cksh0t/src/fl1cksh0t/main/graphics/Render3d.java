package fl1cksh0t.main.graphics;

import fl1cksh0t.main.Game;

//This class renders the 3D environment - the below is standard practice for doing so
public class Render3d extends Render {
	public Render3d(int width, int height) {
		super(width, height);
	}

	//Draws the floor of our game
	public void floor(Game game) {

		double floorPosition = 8;
		double ceilingPosition = 16;
		double forward = game.controls.z;
		double right = game.controls.x;

		double rotation =  game.controls.rotation;
		double cosine = Math.cos(rotation);
		double sine = Math.sin(rotation);

		for (int y = 0; y < height; y++) {
			double ceiling = (y - height / 2.0) / height;

			double z = floorPosition / ceiling;

			//fixes confusing floor and ceiling clash
			if(ceiling < 0) {
				z = ceilingPosition / -ceiling;
			}

			for (int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height;
				depth *= z;

				double xx = depth * cosine + z * sine;
				double yy = z * cosine - depth * sine;

				int xPix = (int) (xx + right);
				int yPix = (int) (yy + forward);

				pixels[x + y * width] = ((xPix & 15) * 16) | ( (yPix & 15) * 16)  << 8;
			}
		}
	}
}
