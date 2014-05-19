package DungeonDemolition;

public class DungeonGenerator {

    public static DungeonMap generateDungeonMap(int sizeX, int sizeY, int iterations) {

        byte[][] map = new byte[sizeX][sizeY];
        Room previousRoom = null;

        for (int i = 0; i < iterations; i++) {

            if(previousRoom != null) return null;

        }
        return null;

    }

}
