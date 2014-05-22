package dungeonDemolition.dungeons.rooms;

import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.Vector2i;

public abstract class Room {

    public Vector2i position = new Vector2i(); //Upper left corner
    public Vector2i size = new Vector2i();
    public byte[][] tiles; //All tiles that this room consists of

    public abstract void generate(Vector2i position, Vector2i direction, byte[][] map);

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

    public abstract Vector2i[] getAvailableSpace(int direction, Vector2i start, byte[][] map);

}
