package dungeonDemolition.objects.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIPanel {

    public List<GUIElement> guiElements = new ArrayList<GUIElement>();
    public boolean active;

    public GUIPanel(GUIElement[] elements, boolean active) {

        for (GUIElement element : elements)
            guiElements.add(element);

        this.active = active;

    }

    public void render(Graphics graphics) {

        if (active)
            for (GUIElement element : guiElements)
                element.render(graphics);

    }

}
