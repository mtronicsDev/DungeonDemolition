package dungeonDemolition.objects;

import dungeonDemolition.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DungeonTile {

    private static List<BufferedImage> textures = new ArrayList<BufferedImage>();
    public byte id;
    public Vector2f position;

    public DungeonTile(byte id, Vector2f position) {

        if (textures.size() == 0) {

            try {

                for (int i = 0; i < 11; i++)
                    textures.add(ImageIO.read(new File("res/textures/" + i + ".png")));

            } catch (Exception e) {

                e.printStackTrace();
                System.exit(1);

            }

        }

        this.id = id;
        this.position = position;

    }

    public void render(Graphics graphics) {

        if (!(((position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20 < -40 )
                || ((position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20) >= ObjectController.display.size.x
                || ((position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20) < -40
                || ((position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20) >= ObjectController.display.size.y))

            graphics.drawImage(textures.get(id),
                    (int) (position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20,
                    (int) (position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20,
                    40, 40, null);

    }

}
