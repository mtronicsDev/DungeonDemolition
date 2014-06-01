package dungeonDemolition.objects.gui;

import dungeonDemolition.util.Vector2i;
import dungeonDemolition.util.InputInformation;

import java.awt.*;
import java.awt.event.MouseEvent;

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

        pressed = InputInformation.mousePosition.x >= position.x &&
                InputInformation.mousePosition.x <= position.x + rectangle.size.x &&
                InputInformation.mousePosition.y >= position.y &&
                InputInformation.mousePosition.y <= position.y + rectangle.size.y &&
                InputInformation.isButtonPressed(MouseEvent.BUTTON1);


    }

    public boolean isPressed() { return pressed; }

}
