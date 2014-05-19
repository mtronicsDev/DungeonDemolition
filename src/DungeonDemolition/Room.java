package DungeonDemolition;

import java.awt.*;

public abstract class Room {

    protected Point position; //Upper left corner
    protected byte[][] tiles; //All tiles that this room consists of

    public static RoomType getRandomRoomType() {

        return RoomType.values()[Randomizer.getRandomInt(0, RoomType.values().length)]; //Gets a random room name

    }

    public Point getPosition() { return position; }

    public abstract void generate(Point position, Point direction, Point maxSize);

}
