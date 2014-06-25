package dungeonDemolition.objects;

import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.dungeons.DungeonGenerator;
import dungeonDemolition.objects.dungeons.DungeonMap;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.GUIElement;
import dungeonDemolition.objects.gui.GUIHealthBar;
import dungeonDemolition.objects.gui.GUIPanel;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.objects.weapons.Shotgun;
import dungeonDemolition.objects.weapons.projectiles.Projectile;
import dungeonDemolition.util.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectController {

    public static List<Projectile> projectiles = new ArrayList<Projectile>();
    public static List<Projectile> projectilesToRemove = new ArrayList<Projectile>();
    public static Map<String, GUIPanel> guiPanels = new HashMap<String, GUIPanel>();
    public static List<DungeonMap> dungeonMaps = new ArrayList<DungeonMap>();
    public static int currentDungeonMap = -1;
    public static Map<String, Entity> entities = new HashMap<String, Entity>();
    public static List<Entity> entitiesToRemove = new ArrayList<Entity>();
    public static int enemyCounter = 0;
    public static Display display;

    public static boolean running = false;

    public static void pause() {

        running = false;

        guiPanels.get("inGame").active = false;
        guiPanels.get("menu").active = true;

    }

    public static void run() {

        running = true;

        guiPanels.get("inGame").active = true;
        guiPanels.get("menu").active = false;

    }

    public static void generateNewLevel(boolean start) {

        if (start) {

            addDungeonMap(DungeonGenerator.generateDungeonMap(512, 512));
            for (DungeonTile tile : dungeonMaps.get(currentDungeonMap).dungeonTiles)
                if (tile.id == 10) {
                    Player player = new Player();
                    player.position = new Vector2f(tile.position);
                    setPlayer(player);
                }

            for (DungeonTile tile : dungeonMaps.get(currentDungeonMap).dungeonTiles)
                if (tile.id == 11) {
                    Enemy enemy = new Enemy("alligatorMovement", "explosion", 50, 10, 1, currentDungeonMap);
                    guiPanels.get("inGame").guiElements.add(new GUIHealthBar(enemy));
                    enemy.position = new Vector2f(tile.position);
                    addEnemy(enemy);
                }

        } else addDungeonMap(DungeonGenerator.generateDungeonMap(512, 512));

    }

    public static void updateAll() {

        if (running) {

            projectilesToRemove.clear();
            entitiesToRemove.clear();

            for (Projectile projectile : projectiles) projectile.update();
            for (Entity entity : entities.values()) entity.update();

            for (Projectile projectile : projectilesToRemove)
                projectiles.remove(projectile);

            for (Entity entityToRemove : entitiesToRemove) {

                if (entityToRemove instanceof Enemy) {

                    String removeKey = "";

                    for (String key : entities.keySet())
                        if (entities.get(key) == entityToRemove) removeKey = key;

                    entities.remove(removeKey);

                    GUIElement healthBar = null;

                    for (GUIElement guiElement : guiPanels.get("inGame").guiElements)
                        if (guiElement instanceof GUIHealthBar)
                            if (((GUIHealthBar) guiElement).entity == entityToRemove) healthBar = guiElement;

                    guiPanels.get("inGame").guiElements.remove(healthBar);

                }

            }

        }

        display.update();

    }

    public static void removeAll() {

        for (Entity entity : entities.values()) {

            if (entity instanceof Player) {

                for (GUIRectangle heart : ((Player) entity).heartBar)
                    guiPanels.get("inGame").guiElements.remove(heart);

            } else {

                GUIElement healthBar = null;

                for (GUIElement guiElement : guiPanels.get("inGame").guiElements)
                    if (guiElement instanceof GUIHealthBar)
                        if (((GUIHealthBar) guiElement).entity == entity) healthBar = guiElement;

                guiPanels.get("inGame").guiElements.remove(healthBar);

            }

        }

        entities.clear();
        enemyCounter = 0;
        projectiles.clear();
        dungeonMaps.clear();
        currentDungeonMap = -1;

    }

    public static void goToDungeonMap(int index) {

        if (dungeonMaps.size() > index) {
            if (dungeonMaps.get(index) != null)
                currentDungeonMap = index;
            else
                addDungeonMap(DungeonGenerator.generateDungeonMap(512, 512));
        }

    }

    public static void addProjectile(Projectile projectile) {

        projectiles.add(projectile);

    }

    public static void addGUIPanel(String key, GUIPanel panel) {

        guiPanels.put(key, panel);

    }

    public static void addDungeonMap(DungeonMap dungeonMap) {

        dungeonMaps.add(dungeonMap);

        currentDungeonMap = dungeonMaps.size() - 1;

    }

    public static void addEnemy(Enemy enemy) {

        entities.put("enemy" + enemyCounter, enemy);

        enemyCounter++;

    }

    public static void setPlayer(Player player) {

        entities.put("player", player);

    }

    public static Player getPlayer() {

        return (Player)entities.get("player");

    }

    public static void setDisplay(Display display) {

        ObjectController.display = display;

    }

}