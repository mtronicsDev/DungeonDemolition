package dungeonDemolition.objects;

import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.dungeons.DungeonMap;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.GUIElement;
import dungeonDemolition.objects.gui.GUIHealthBar;
import dungeonDemolition.objects.gui.GUIPanel;
import dungeonDemolition.objects.weapons.projectiles.Projectile;

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

    public static void setDisplay(Display display) {

        ObjectController.display = display;

    }

}