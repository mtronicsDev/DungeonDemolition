package dungeonDemolition;

import dungeonDemolition.dungeons.DungeonGenerator;
import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.DungeonTile;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.Player;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.Vector2i;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600);
        ObjectController.setDisplay(display);

        ObjectController.setPlayer(new Player(new Vector2f(100, 100), new Vector2f(), "player"));

        ObjectController.addGUIElement(new GUIText(new Vector2i(10, 85), Color.blue, "The thing above is a colored gui rectangle."));

        ObjectController.addDungeonMap(DungeonGenerator.generateDungeonMap(512, 512, 20));
        ObjectController.currentDungeonMap = 0;
        for (DungeonTile tile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
            if (tile.id == 10) {
                ObjectController.player.position.x = tile.position.x;
                ObjectController.player.position.y = tile.position.y;
            }

        TimeHelper.initialize();

        while (true) {

            TimeHelper.update();
            ObjectController.player.update();
            display.update();

        }

    }

}
