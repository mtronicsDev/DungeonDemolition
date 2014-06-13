package dungeonDemolition.objects.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIPanel {

    public List<GUIElement> guiElements = new ArrayList<GUIElement>();
    private List<GUIElement> scheduledDeletions = new ArrayList<GUIElement>();
    public boolean active;

    public GUIPanel(GUIElement[] elements, boolean active) {

        for (GUIElement element : elements)
            guiElements.add(element);

        this.active = active;

    }

    public void render(Graphics graphics) {

        for (GUIElement element : scheduledDeletions)
            guiElements.remove(element);

        if (active)
            for (GUIElement element : guiElements)
                element.render(graphics);

    }

    public void scheduleDeletion(GUIElement element) {

        scheduledDeletions.add(element);

    }

}
