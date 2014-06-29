package dungeonDemolition.objects.dungeons;

import dungeonDemolition.util.PreferenceHelper;
import dungeonDemolition.util.Randomizer;

public class DungeonGenerator {

    static byte[][] map;
    private static int maxDungeonLevel =
            Randomizer.getRandomInt(
                    PreferenceHelper.getInteger("minLevels"),
                    PreferenceHelper.getInteger("maxLevels")
            );

    public static DungeonMap generateDungeonMap(int sizeX, int sizeY, byte level) {

        map = new byte[sizeX][sizeY];
        new Room().generate(level, maxDungeonLevel);

        return new DungeonMap(map, level);

    }

}
