package dungeonDemolition.objects.gui;

import dungeonDemolition.util.Vector2i;
import dungeonDemolition.util.InputInformation;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GUIButton extends GUIElement {

    public GUIRectangle rectangle;
    public GUIText text;
    private boolean pressed = false;

    public GUIButton(Vector2i position, Vector2i size, Color color, Color textColor, int textSize, String text) {

        super(position);

        this.rectangle = new GUIRectangle(position, size, color, true);

        Vector2i textPosition = new Vector2i(
                position.x + size.x / 2 - text.toCharArray().length * textSize / 4,
                position.y + size.y / 2 + textSize / 3
        );

        this.text = new GUIText(textPosition, textColor, textSize, text);

    }

    public GUIButton(Vector2i position, Vector2i size, String textureName, Color textColor, int textSize, String text) {

        super(position);

        this.rectangle = new GUIRectangle(position, size, textureName);

        Vector2i textPosition = new Vector2i(
                position.x + size.x / 2 - text.toCharArray().length * textSize / 4,
                position.y + size.y / 2 + textSize / 3
        );

        this.text = new GUIText(textPosition, textColor, textSize, text);

    }

    @Override
    public void render(Graphics graphics) {

        rectangle.render(graphics);
        text.render(graphics);

        pressed = InputInformation.mousePosition.x >= position.x &&
                InputInformation.mousePosition.x <= position.x + rectangle.size.x &&
                InputInformation.mousePosition.y >= position.y &&
                InputInformation.mousePosition.y <= position.y + rectangle.size.y &&
                InputInformation.isButtonPressed(MouseEvent.BUTTON1);


    }

    public boolean isPressed() { return pressed; }

}
