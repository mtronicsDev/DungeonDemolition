package dungeonDemolition.objects;

import dungeonDemolition.objects.dungeons.DungeonMap;
import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.GUIElement;
import dungeonDemolition.objects.gui.GUIPanel;
import dungeonDemolition.objects.projectiles.Projectile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectController {

    public static List<Projectile> projectiles = new ArrayList<Projectile>();
    public static Map<String, GUIPanel> guiPanels = new HashMap<String, GUIPanel>();
    public static List<DungeonMap> dungeonMaps = new ArrayList<DungeonMap>();
    public static int currentDungeonMap = -1;
    public static Map<String, Entity> entities = new HashMap<String, Entity>();
    public static int enemyCounter = 0;
    public static Display display;

    public static boolean running = true;

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

            for (Projectile projectile : projectiles) projectile.update();

            for (Entity entity : entities.values()) entity.update();

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
