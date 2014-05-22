package dungeonDemolition.objects;

import dungeonDemolition.dungeons.rooms.Room;
import dungeonDemolition.graphics.Display;

import java.util.ArrayList;
import java.util.List;

public class ObjectController {

    public static List<Room> rooms = new ArrayList<Room>();
    public static Player player;
    public static Display display;

    public static void addRoom(Room room) {

        rooms.add(room);

    }

    public static void setPlayer(Player player) {

        ObjectController.player = player;

    }

    public static void setDisplay(Display display) {

        ObjectController.display = display;

    }

}
