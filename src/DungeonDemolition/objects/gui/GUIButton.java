package dungeonDemolition.objects.gui;

import dungeonDemolition.util.Input;
import dungeonDemolition.util.Vector2i;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIButton extends GUIElement{

    public Vector2i size;
    public int textSize;
    public BufferedImage texture;
    public Color color;
    public Color textColor;
    public String text;
    private boolean pressed = false;

    public GUIButton(Vector2i position, Vector2i size, Color color, Color textColor, String text) {

        super(position);
        this.size = size;
        this.color = color;
        this.textColor = textColor;
        this.text = text;
        textSize = size.y - 2;

    }

    public GUIButton(Vector2i position, Vector2i size, String textureName, Color textColor, String text) {

        super(position);
        this.size = size;
        this.textColor = textColor;
        this.text = text;
        textSize = 15;

        try {

            texture = ImageIO.read(new File("res/textures/" + textureName + ".png"));

        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }

    @Override
    public void render(Graphics graphics) {

        if (color != null) {

            graphics.setColor(color);
            graphics.fillRect(position.x, position.y, size.x, size.y);

        } else {

            if (size == null) graphics.drawImage(texture, position.x, position.y, null);
            else graphics.drawImage(texture, position.x, position.y, size.x, size.y, null);

        }

        graphics.setFont(new Font("Arial", Font.PLAIN, textSize));
        graphics.setColor(textColor);
        graphics.drawString(text, position.x, position.y);

        pressed = Input.mousePosition.x >= position.x &&
                Input.mousePosition.x <= position.x + size.x &&
                Input.mousePosition.y >= position.y &&
                Input.mousePosition.y <= position.y + size.y;


    }

    public boolean isPressed() { return pressed; }

}
