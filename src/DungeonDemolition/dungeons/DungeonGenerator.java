package dungeonDemolition.dungeons;

import dungeonDemolition.dungeons.rooms.Room;
import dungeonDemolition.dungeons.rooms.RoomStart;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DungeonGenerator {

    public static void main(String[] args) {

        generateDungeonMap(500, 500, 7);

    }

    public static DungeonMap generateDungeonMap(int sizeX, int sizeY, int iterations) {

        byte[][] map = new byte[sizeX][sizeY];
        Room previousRoom = null;
        Room currentRoom;

        for (int i = 0; i < iterations; i++) {

            if (previousRoom == null) currentRoom = new RoomStart();
            else currentRoom = Room.getRoomFromType(Room.getRandomRoomType());

            if (previousRoom == null) currentRoom.generate(new Point(), new Point(), map);
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

        try {
            PrintWriter out = new PrintWriter("dungeon.map");
            for (int i = 0; i < map[0].length; i++) {
                for (int j = 0; j < map.length; j++) {
                    out.print(map[j][i]);
                }
                out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;

    }

}
