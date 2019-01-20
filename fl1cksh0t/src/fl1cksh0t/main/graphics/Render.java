package fl1cksh0t.main.graphics;

public class Render 
{
	public final int width;
	public final int height;
	public final int[] pixels;
	
	public Render(int width, int height)
	{
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void draw(Render render, int xOffs, int yOffs)	//making x-offset and y-offset
	{
		for(int y = 0; y < render.height; y++)
		{
			int yPix = y + yOffs;
			if(yPix < 0 || yPix >= this.height)
			{
				continue;
			}
			for(int x = 0; x < render.width; x++)
			{
				int xPix = x + xOffs;
				if(xPix < 0 || xPix >= this.width)
				{
					continue;
				}
				
				int alpha = render.pixels[x + y * render.width];	//alpha support of sorts
				if(alpha > 0 )										// bit of a heavy dip in performance
				{
					pixels[xPix + yPix * width] = alpha;
				}
			}
		}
	}
}
