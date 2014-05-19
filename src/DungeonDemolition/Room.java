package DungeonDemolition;

import java.awt.*;

public abstract class Room {

    protected Point position; //Upper left corner
    protected Point size;
    protected byte[][] tiles; //All tiles that this room consists of

    public static RoomType getRandomRoomType() {

        return RoomType.values()[Randomizer.getRandomInt(0, RoomType.values().length)]; //Gets a random room name

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

    public Point getPosition() { return position; }

    public Point getSize() { return  size; }

    public Point[] getNextStartingConditions() {

        int direction = Randomizer.getRandomInt(0, 3); //0: north / 1: east / 2: south / 3: west

        int randomXFactor = Randomizer.getRandomInt(position.x + 1, position.x + size.x - 1);
        int randomYFactor = Randomizer.getRandomInt(position.y + 1, position.y + size.y - 1);

        Point startingPosition;
        Point startingDirection;

        switch (direction) {

            case 0:
                startingPosition = new Point(randomXFactor, position.y);
                startingDirection = new Point(-1, 0);
                break;
            case 1:
                startingPosition = new Point(position.x + size.x, randomYFactor);
                startingDirection = new Point(0, 1);
                break;
            case 2:
                startingPosition = new Point(randomXFactor, position.y + size.y);
                startingDirection = new Point(1, 0);
                break;
            case 3:
                startingPosition = new Point(position.x, randomYFactor);
                startingDirection = new Point(0, -1);
                break;
            default:
                startingPosition = new Point(randomXFactor, position.y);
                startingDirection = new Point(-1, 0);
                break;

        }

        return new Point[]{startingPosition, startingDirection};

    }

    public abstract void generate(Point position, Point direction, byte[][] map);

}
