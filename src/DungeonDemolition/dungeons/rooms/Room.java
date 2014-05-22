package dungeonDemolition.dungeons.rooms;

import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.Vector2i;

public abstract class Room {

    public Vector2i position = new Vector2i(); //Upper left corner
    public Vector2i size = new Vector2i();
    public byte[][] tiles; //All tiles that this room consists of

    public static Room getRandomRoom() {

        int rand = Randomizer.getRandomInt(0, 1);

        switch (rand) {

            case 0:
                return new RoomGeneric();
            case 1:
                return new RoomCorridor();
            default:
                return null;

        }

    }

    public abstract void generate(Vector2i position, Vector2i direction, byte[][] map);

    public Vector2i[] getAvailableSpace(int direction, Vector2i start, byte[][] map) {

        switch (direction) {

            case 0: //North

                int minX = 0;
                int maxX = map.length;
                int minY = 0;

                for (int yCount = start.y; yCount >= 0; yCount--) {



                }

                for (int yCount = 0; yCount <= start.y; yCount++) {

                    for (int xMinCount = 0; xMinCount <= start.x; xMinCount++) {

                        if (map[xMinCount][yCount] != 0 && map[xMinCount][yCount] > minX) minX =  map[xMinCount][yCount];

                    }

                    for (int xMaxCount = map.length; xMaxCount >= start.x; xMaxCount++) {

                        if (map[xMaxCount][yCount] != 0 && map[xMaxCount][yCount] > maxX) maxX =  map[xMaxCount][yCount];

                    }

                }

                return new Vector2i[] {new Vector2i(minX, minY), new Vector2i(maxX, start.y)};

            case 1: //East

                break;

            case 2: //South

                break;

            case 3: //West

                break;


        }

        return null;

    }

}
