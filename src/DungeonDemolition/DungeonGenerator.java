package DungeonDemolition;

import java.awt.*;

public class DungeonGenerator {

    public static DungeonMap generateDungeonMap(int sizeX, int sizeY, int iterations) {

        byte[][] map = new byte[sizeX][sizeY];
        Room previousRoom = null;
        Room currentRoom;

        for (int i = 0; i < iterations; i++) {

            if(previousRoom == null) currentRoom = new RoomStart();
            else currentRoom = Room.getRoomFromType(Room.getRandomRoomType());

            if(previousRoom == null) currentRoom.generate(new Point(), new Point(), map);
            else currentRoom.generate(
                    previousRoom.getNextStartingConditions()[0],
                    previousRoom.getNextStartingConditions()[1],
                    map);

            for (int x = 0; x < currentRoom.size.x; x++) {
                for (int y = 0; y < currentRoom.size.y; y++) {

                    map[currentRoom.position.x + x][currentRoom.position.y + y] = currentRoom.tiles[x][y]; //Transferring the room data to the map

                }
            }
            previousRoom = currentRoom;

        }
        return null;

    }

}
