package dungeonDemolition.objects;

import dungeonDemolition.dungeons.DungeonMap;
import dungeonDemolition.graphics.Display;

import java.util.ArrayList;
import java.util.List;

public class ObjectController {

    public static List<DungeonMap> dungeons = new ArrayList<DungeonMap>();
    public static Player player;
    public static Display display;

    public static void addDungeon(DungeonMap dungeonMap) {

        dungeons.add(dungeonMap);

    }

    public static void setPlayer(Player player) {

        ObjectController.player = player;

    }

    public static void setDisplay(Display display) {

        ObjectController.display = display;

    }

}
