package dungeonDemolition.objects;

import dungeonDemolition.dungeons.rooms.Room;
import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.gui.GUIElement;

import java.util.ArrayList;
import java.util.List;

public class ObjectController {

    public static List<GUIElement> guiElements = new ArrayList<GUIElement>();
    public static List<Room> rooms = new ArrayList<Room>();
    public static Player player;
    public static Display display;

    public static void addGUIElement(GUIElement element) {

        guiElements.add(element);

    }

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
