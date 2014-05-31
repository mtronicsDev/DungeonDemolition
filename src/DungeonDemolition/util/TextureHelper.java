package dungeonDemolition.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureHelper {

    public static BufferedImage loadImage(String imageName) {

        BufferedImage image = null;

        try {

            image = ImageIO.read(new File("res/textures/" + imageName + ".png"));

        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

        return image;

    }

}
