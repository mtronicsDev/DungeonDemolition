package dungeonDemolition.objects.dungeons;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DungeonGenerator {

    public static byte[][] map;

    public static DungeonMap generateDungeonMap(int sizeX, int sizeY) {

        map = new byte[sizeX][sizeY];
        new Room().generate();

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
