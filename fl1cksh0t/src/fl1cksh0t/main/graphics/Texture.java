package fl1cksh0t.main.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Texture {

    public static Render floor = loadBitmap("/textures/grass2024.jpg");
    public static Render loadBitmap(String fileName) {
        try{
            BufferedImage image = ImageIO.read(Texture.class.getResource(fileName));
            int width = image.getWidth();
            int height = image.getHeight();
            Render result = new Render(width, height);
            image.getRGB(0, 0, width, height, result.pixels, 0, width);
            return result;
        } catch(Exception e){
            System.out.println("Texture: grass2024.jpg has caused a crash");
            throw new RuntimeException(e);
        }
    }

}
