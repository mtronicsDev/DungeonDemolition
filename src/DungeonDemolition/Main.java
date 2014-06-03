package dungeonDemolition;

import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonGenerator;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.*;
import dungeonDemolition.objects.weapons.*;
import dungeonDemolition.util.*;
import dungeonDemolition.util.InputInformation;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600);
        ObjectController.setDisplay(display);

        TimeHelper.initialize();

        ObjectController.addGUIPanel("inGame", new GUIPanel(
                new GUIElement[]{
                        new GUIText(new Vector2i(10, 55), Color.blue, 15, "This is the in-game gui panel,"),
                        new GUIText(new Vector2i(10, 75), Color.blue, 15, "where weapons and health points will be shown later."),
                },
                false
        ));

        ObjectController.addGUIPanel("menu", new GUIPanel(
                new GUIElement[]{
                        new GUIText(new Vector2i(10, 55), Color.blue, 15, "This is the menu gui panel,"),
                        new GUIText(new Vector2i(10, 75), Color.blue, 15, "where settings and other things will be shown later."),
                        new GUIButton(new Vector2i(435, 200), new Vector2i(200, 100), Color.orange, Color.black, 30, "Play"),
                        new GUIButton(new Vector2i(435, 350), new Vector2i(200, 100), Color.red, Color.black, 30, "Quit")
                },
                true
        ));

        ObjectController.addDungeonMap(DungeonGenerator.generateDungeonMap(512, 512));
        for (DungeonTile tile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
            if (tile.id == 10) {
                Player player = new Player();
                player.weaponContainer.addWeapon(new RocketLauncher())
                        .addWeapon(new MachineGun())
                        .addWeapon(new Shotgun())
                        .addWeapon(new Pistol());
                player.position = new Vector2f(tile.position);
                ObjectController.setPlayer(player);
            }

        for (DungeonTile tile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
            if (tile.id == 11) {
                Enemy enemy = new Enemy("alligatorMovement", "explosion", 50, 10, 1);
                ObjectController.guiPanels.get("inGame").guiElements.add(new GUIHealthBar(enemy));
                enemy.position = new Vector2f(tile.position);
                ObjectController.addEnemy(enemy);
            }

        while (true) {

            if (!ObjectController.running) {

                if (((GUIButton) ObjectController.guiPanels.get("menu").guiElements.get(2)).isPressed()) ObjectController.run();

                else if (((GUIButton) ObjectController.guiPanels.get("menu").guiElements.get(3)).isPressed()) System.exit(0);

            }

            if (InputInformation.isKeyDown(KeyEvent.VK_ESCAPE)) {

                if (ObjectController.running) ObjectController.pause();

                else ObjectController.run();

            }

            TimeHelper.update();
            InputInformation.update();
            ObjectController.updateAll();

        }

    }

}
