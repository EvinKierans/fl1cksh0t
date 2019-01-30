package fl1cksh0t.main.graphics;

public class Render 
{
	//Variables for the pixel assignment array
	public final int width;
	public final int height;
	public final int[] pixels;

	//Render constructor acts as a getter
	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}

	//making x-offset and y-offset
	public void draw(Render render, int xOffs, int yOffs) {
		for(int y = 0; y < render.height; y++) {
			int yPix = y + yOffs;
			if(yPix < 0 || yPix >= this.height) {
				continue;
			}
			for(int x = 0; x < render.width; x++) {
				int xPix = x + xOffs;
				if(xPix < 0 || xPix >= this.width) {
					continue;
				}

				//Alpha support - allows non-pixels to exist
				int alpha = render.pixels[x + y * render.width];
				if(alpha > 0 ) {
					pixels[xPix + yPix * width] = alpha;
				}
			}
		}
	}
}
