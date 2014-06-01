package dungeonDemolition;

import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonGenerator;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.*;
import dungeonDemolition.objects.weapons.Flamethrower;
import dungeonDemolition.objects.weapons.Pistol;
import dungeonDemolition.objects.weapons.RocketLauncher;
import dungeonDemolition.objects.weapons.Shotgun;
import dungeonDemolition.objects.weapons.projectiles.Bullet;
import dungeonDemolition.util.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
                        new GUIButton(new Vector2i(10, 95), new Vector2i(400, 150), Color.orange, Color.black, "Start the game!")
                },
                true
        ));

        boolean isGameStarted;
        GUIButton button = (GUIButton)ObjectController.guiPanels.get("menu").guiElements.get(2);

        do {

            Input.update();
            TimeHelper.update();
            display.update();

            isGameStarted = button.isPressed();

        }
        while (!isGameStarted);

        ObjectController.run();

        ObjectController.addDungeonMap(DungeonGenerator.generateDungeonMap(512, 512));
        for (DungeonTile tile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
            if (tile.id == 10) {
                Player player = new Player();
                player.weaponContainer.addWeapon(new RocketLauncher())
                        .addWeapon(new Shotgun())
                        .addWeapon(new Flamethrower())
                        .addWeapon(new Pistol());
                ObjectController.guiPanels.get("inGame").guiElements.add(new GUIHealthBar(player));
                ObjectController.setPlayer(player);
                player.position = new Vector2f(tile.position);
            }

        for (DungeonTile tile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
            if (tile.id == 11) {
                Enemy enemy = new Enemy("alligatorMovement", 50);
                ObjectController.guiPanels.get("inGame").guiElements.add(new GUIHealthBar(enemy));
                ObjectController.addEnemy(enemy);
                enemy.position = new Vector2f(tile.position);
            }

        while (true) {

            TimeHelper.update();
            Input.update();
            ObjectController.updateAll();

        }

    }

}
