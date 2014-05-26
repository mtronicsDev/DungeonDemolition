package dungeonDemolition.objects.dungeons;

import dungeonDemolition.objects.dungeons.rooms.Room;
import dungeonDemolition.objects.dungeons.rooms.RoomStart;
import dungeonDemolition.util.Randomizer;

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
            else currentRoom = Room.getRandomRoom();

            if (previousRoom == null) currentRoom.generate(null, null, map);
            else {

                int randomDirection = Randomizer.getRandomInt(0, 3); //0:N / 1:E / 2:S / 3:W

            }

            for (int x = 0; x < currentRoom.size.x; x++) {
                for (int y = 0; y < currentRoom.size.y; y++) {

                    map[currentRoom.position.x + x][currentRoom.position.y + y] = currentRoom.tiles[x][y]; //Transferring the room data to the map

                }
            }
            previousRoom = currentRoom;

        }

        try {
            PrintWriter out = new PrintWriter("dungeon.map");
            char c;

            for (int y = 0; y < map[0].length; y++) {
                for (int x = 0; x < map.length; x++) {

                    switch (map[x][y]) {

                        case 0:
                            c = '█';
                            break;
                        case 1:
                            c = '░';
                            break;
                        case 2:
                            c = '═';
                            break;
                        case 3:
                            c = '║';
                            break;
                        case 4:
                            c = '═';
                            break;
                        case 5:
                            c = '║';
                            break;
                        case 6:
                            c = '╔';
                            break;
                        case 7:
                            c = '╗';
                            break;
                        case 8:
                            c = '╝';
                            break;
                        case 9:
                            c = '╚';
                            break;
                        case 10:
                            c = '☺';
                            break;
                        case 11:
                            c = '☠';
                            break;
                        case 12:
                            c = '☆';
                            break;
                        case 13:
                            c = '∏';
                            break;
                        case 14:
                            c = '▲';
                            break;
                        case 15:
                            c = '▼';
                            break;
                        default:
                            c = '#';
                            break;

                    }

                    out.print(c);

                }

                out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return new DungeonMap(map);

    }

}
