package DungeonDemolition.util;

import DungeonDemolition.gameObjects.rooms.Room;
import DungeonDemolition.gameObjects.rooms.RoomType;
import DungeonDemolition.gameObjects.DungeonMap;

public class DungeonGenerator {

    public static DungeonMap generateDungeonMap(int sizeX, int sizeY, int iterations) {

        byte[][] map = new byte[sizeX][sizeY];

        for (int i = 0; i < iterations; i++) {

            RoomType desiredRoomType = Room.getRandomRoomType();



        }
        return null;

    }

}
