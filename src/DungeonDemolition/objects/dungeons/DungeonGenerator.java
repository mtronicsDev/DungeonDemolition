package dungeonDemolition.objects.dungeons;

public class DungeonGenerator {

    public static byte[][] map;

    public static DungeonMap generateDungeonMap(int sizeX, int sizeY) {

        map = new byte[sizeX][sizeY];
        new Room().generate();

        return new DungeonMap(map);

    }

}
