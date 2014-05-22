package dungeonDemolition.objects.gui;

import dungeonDemolition.util.Vector2i;

import java.awt.*;

public abstract class GUIElement {

    public Vector2i position;

    public GUIElement(Vector2i position) {

        this.position = position;

    }

    public abstract void render(Graphics graphics);

}
