package dungeonDemolition.objects.gui;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.util.Timer;
import dungeonDemolition.util.Vector2i;

import java.awt.*;

public class GUITitle extends GUIElement {

    GUIText text;
    Timer timer;

    public GUITitle(Color color, String message, float duration) {

        super(new Vector2i());
        int textSize = ObjectController.display.getWidth() / 30;
        Vector2i position = new Vector2i(
                ObjectController.display.getWidth() / 2 - message.toCharArray().length * textSize / 4,
                ObjectController.display.getHeight() / 2 + textSize / 3
        );
        text = new GUIText(position, color, textSize, message);
        timer = new Timer(duration);

    }

    public GUITitle(Vector2i position, Color color, int textSize, String message, float duration) {

        super(position);

        text = new GUIText(position, color, textSize, message);
        timer = new Timer(duration);

    }

    @Override
    public void render(Graphics graphics) {

        text.render(graphics);
        timer.update();

        if (timer.hasFinished()) {

            ObjectController.guiPanels.get("inGame").scheduleDeletion(this);

            Player player = ObjectController.getPlayer();

            if (player.lootSlots.contains(this)) player.deleteTitle(this);

        }

    }
}
