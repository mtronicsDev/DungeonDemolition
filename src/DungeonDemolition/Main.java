package dungeonDemolition;

import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonGenerator;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.GUIElement;
import dungeonDemolition.objects.gui.GUIPanel;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.util.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600);
        ObjectController.setDisplay(display);

        ObjectController.setPlayer(new Player("player"));

        ObjectController.addGUIPanel("inGame", new GUIPanel(
                new GUIElement[]{
                        new GUIText(new Vector2i(10, 55), Color.blue, 15, "This is the in-game gui panel,"),
                        new GUIText(new Vector2i(10, 75), Color.blue, 15, "where weapons and health points will be shown later.")
                },
                true
        ));

        ObjectController.addGUIPanel("menu", new GUIPanel(
                new GUIElement[]{
                        new GUIText(new Vector2i(10, 55), Color.blue, 15, "This is the menu gui panel,"),
                        new GUIText(new Vector2i(10, 75), Color.blue, 15, "where settings and other things will be shown later.")
                },
                false
        ));

        ObjectController.addDungeonMap(DungeonGenerator.generateDungeonMap(512, 512));
        ObjectController.currentDungeonMap = 0;
        for (DungeonTile tile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
            if (tile.id == 10) {
                ObjectController.entities.get("player").position = new Vector2f(tile.position);
            }

        for (DungeonTile tile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
            if (tile.id == 11) {
                Enemy enemy = new Enemy("enemy");
                ObjectController.addEnemy(enemy);
                enemy.position = new Vector2f(tile.position);
            }

        TimeHelper.initialize();

        while (true) {

            if (Input.isKeyPressed(KeyEvent.VK_ESCAPE) && Input.isKeyPressed(KeyEvent.VK_1)) ObjectController.pause();

            else if (Input.isKeyPressed(KeyEvent.VK_ESCAPE) && Input.isKeyPressed(KeyEvent.VK_2))
                ObjectController.run();

            TimeHelper.update();
            Input.update();
            ObjectController.updateAll();

        }

    }

}
