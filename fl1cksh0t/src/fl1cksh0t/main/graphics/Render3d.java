package fl1cksh0t.main.graphics;

//This class renders the 3D environment - the below is standard practice for doing so
public class Render3d extends Render {
	public Render3d(int width, int height)
	{
		super(width, height);
	}

	double time = 0;

	//Draws the floor of our game
	public void floor() {
		for (int y = 0; y < height; y++) {
			double ceiling = (y - height / 2.0) / height;

			double z = 8 / ceiling;

			time += 0.0005;

			for (int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height;
				depth *= z;
				int xx = (int) (depth) & 15; //Casting because depth is a double
				int yy = (int) (z + time) & 15;
				pixels[x + y * width] = (xx * 16) | (yy * 16)  << 8;
			}
		}
	}
}
