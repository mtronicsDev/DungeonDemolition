package dungeonDemolition.objects.gui;

import dungeonDemolition.util.Vector2i;

import java.awt.*;

public class GUIText extends GUIElement {

    public Color color;
    public String text;

    public GUIText(Vector2i position, Color color, String text) {

        super(position);

        this.color = color;
        this.text = text;

    }

    public void render(Graphics graphics) {

        graphics.setColor(color);
        graphics.drawString(text, position.x, position.y);

    }

}
