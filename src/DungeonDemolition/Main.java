package dungeonDemolition;

import dungeonDemolition.objects.dungeons.DungeonGenerator;
import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.util.*;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600);
        ObjectController.setDisplay(display);

        ObjectController.setPlayer(new Player("player"));

        ObjectController.addEnemy(new Enemy("player"));

        ObjectController.addGUIElement(new GUIRectangle(new Vector2i(10, 40), new Vector2i(30, 30), Color.red, true));
        ObjectController.addGUIElement(new GUIText(new Vector2i(10, 85), Color.blue, "The thing above is a colored gui rectangle."));

        ObjectController.addDungeonMap(DungeonGenerator.generateDungeonMap(512, 512, 20));
        ObjectController.currentDungeonMap = 0;
        for (DungeonTile tile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
            if (tile.id == 10) {
                ObjectController.entities.get("player").position = new Vector2f(tile.position);
                ObjectController.entities.get("enemy0").position = VectorHelper.subtractVectors(tile.position, new Vector2f(500, 500));
            }

        TimeHelper.initialize();

        while (true) {

            TimeHelper.update();
            Input.update();
            ObjectController.updateAll();

        }

    }

}
