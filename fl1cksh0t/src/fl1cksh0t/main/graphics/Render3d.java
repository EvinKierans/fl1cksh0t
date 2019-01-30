package fl1cksh0t.main.graphics;

//This class renders the 3D environment - the below is standard practice for doing so
public class Render3d extends Render {
	public Render3d(int width, int height)
	{
		super(width, height);
	}

	//Draws the floor of our game
	public void floor() {
		for (int y = 0; y < height; y++) {
			double yDepth = y - height / 2.4;
			double z = 100.0 / yDepth;
			for (int x = 0; x < width; x++) {
				double xDepth = x - width / 2;
				xDepth *= z;
				int xx = (int) (xDepth) & 5; //Casting because xDepth is a double
				pixels[x + y * width] = xx * 120;
			}
		}
	}
}
