package dungeonDemolition.objects.gui;

import dungeonDemolition.util.Vector2i;

import java.awt.*;

public class GUIText extends GUIElement {

    public Color color;
    public String text;
    public int size;

    public GUIText(Vector2i position, Color color, int size, String text) {

        super(position);

        this.color = color;
        this.text = text;
        this.size = size;

    }

    public void render(Graphics graphics) {

        graphics.setFont(new Font("Arial", Font.PLAIN, size));
        graphics.setColor(color);

        graphics.drawString(text, position.x, position.y);

    }

}
