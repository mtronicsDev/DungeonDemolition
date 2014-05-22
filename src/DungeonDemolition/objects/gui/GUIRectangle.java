package dungeonDemolition.objects.gui;

import dungeonDemolition.util.Vector2i;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUIRectangle extends GUIElement {

    public Vector2i size;
    public BufferedImage texture;
    public Color color;
    public boolean filled;

    public GUIRectangle(Vector2i position, String textureName) {

        this(position, null, textureName);

    }

    public GUIRectangle(Vector2i position, Vector2i size, String textureName) {

        super(position);

        this.size = size;

        try {

            texture = ImageIO.read(new File("res/textures/" + textureName + ".png"));

        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }

    public GUIRectangle(Vector2i position, Vector2i size, Color color, boolean filled) {

        super(position);

        this.size = size;
        this.color = color;
        this.filled = filled;

    }

    public void render(Graphics graphics) {

        if (color != null) {

            graphics.setColor(color);

            if (filled) graphics.fillRect(position.x, position.y, size.x, size.y);

            else graphics.drawRect(position.x, position.y, size.x, size.y);

        } else {

            if (size == null) graphics.drawImage(texture, position.x, position.y, null);

            else graphics.drawImage(texture, position.x, position.y, size.x, size.y, null);

        }

    }

}