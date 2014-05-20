package dungeonDemolition.dungeons.rooms;

import dungeonDemolition.dungeons.RoomType;
import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.Vector2i;
import dungeonDemolition.util.Vector2i;

import java.awt.*;

public abstract class Room {

    public Vector2i position = new Vector2i(); //Upper left corner
    public Vector2i size = new Vector2i();
    public byte[][] tiles; //All tiles that this room consists of

    public static RoomType getRandomRoomType() {

        return RoomType.values()[Randomizer.getRandomInt(0, RoomType.values().length - 1)]; //Gets a random room name

    }

    public static Room getRoomFromType(RoomType type) {

        switch (type) {

            case ROOM_BIG:
                return new RoomBig();
            case ROOM_CORRIDOR:
                return new RoomCorridor();
            case ROOM_SMALL:
                return new RoomSmall();
            default:
                return getRoomFromType(getRandomRoomType());

        }

    }

    public Vector2i[] getNextStartingConditions() {

        int direction = Randomizer.getRandomInt(0, 3); //0: north / 1: east / 2: south / 3: west

        int randomXFactor = Randomizer.getRandomInt(position.x + 1, position.x + size.x - 1);
        int randomYFactor = Randomizer.getRandomInt(position.y + 1, position.y + size.y - 1);

        Vector2i startingPosition;
        Vector2i startingDirection;

        switch (direction) {

            case 0:
                startingPosition = new Vector2i(randomXFactor, position.y);
                startingDirection = new Vector2i(-1, 0);
                break;
            case 1:
                startingPosition = new Vector2i(position.x + size.x, randomYFactor);
                startingDirection = new Vector2i(0, 1);
                break;
            case 2:
                startingPosition = new Vector2i(randomXFactor, position.y + size.y);
                startingDirection = new Vector2i(1, 0);
                break;
            case 3:
                startingPosition = new Vector2i(position.x, randomYFactor);
                startingDirection = new Vector2i(0, -1);
                break;
            default:
                startingPosition = new Vector2i(randomXFactor, position.y);
                startingDirection = new Vector2i(-1, 0);
                break;

        }

        return new Vector2i[]{startingPosition, startingDirection};

    }

    public abstract void generate(Vector2i position, Vector2i direction, byte[][] map);

}
