package dungeonDemolition.objects.gui;

import dungeonDemolition.util.Input;
import dungeonDemolition.util.Vector2i;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIButton extends GUIElement{

    public GUIRectangle rectangle;
    public GUIText text;
    private boolean pressed = false;

    public GUIButton(Vector2i position, Vector2i size, Color color, Color textColor, String text) {

        super(position);

        this.rectangle = new GUIRectangle(position, size, color, true);
        this.text = new GUIText(new Vector2i(position.x, position.y + size.y / 2), textColor, 15, text);

    }

    public GUIButton(Vector2i position, Vector2i size, String textureName, Color textColor, String text) {

        super(position);

        this.rectangle = new GUIRectangle(position, size, textureName);
        this.text = new GUIText(new Vector2i(position.x, position.y + size.y / 2), textColor, 15, text);

    }

    @Override
    public void render(Graphics graphics) {

        rectangle.render(graphics);
        text.render(graphics);

        pressed = Input.mousePosition.x >= position.x &&
                Input.mousePosition.x <= position.x + rectangle.size.x &&
                Input.mousePosition.y >= position.y &&
                Input.mousePosition.y <= position.y + rectangle.size.y &&
                Input.isButtonPressed(MouseEvent.BUTTON1);


    }

    public boolean isPressed() { return pressed; }

}
