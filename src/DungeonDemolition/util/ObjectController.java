package DungeonDemolition.util;

import DungeonDemolition.gameObjects.DungeonMap;
import DungeonDemolition.gameObjects.players.Player;

import java.util.ArrayList;
import java.util.List;

public class ObjectController {

    public static List<DungeonMap> dungeons = new ArrayList<DungeonMap>();
    public static Player player;

    public static void addDungeon(DungeonMap dungeonMap) {

        dungeons.add(dungeonMap);

    }

    public void setPlayer(Player player) {

        ObjectController.player = player;

    }

}
