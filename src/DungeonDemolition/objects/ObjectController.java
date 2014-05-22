package dungeonDemolition.objects;

import dungeonDemolition.dungeons.DungeonMap;
import dungeonDemolition.dungeons.rooms.Room;
import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.gui.GUIElement;

import java.util.ArrayList;
import java.util.List;

public class ObjectController {

    public static List<GUIElement> guiElements = new ArrayList<GUIElement>();
    public static List<DungeonMap> dungeonMaps = new ArrayList<DungeonMap>();
    public static int currentDungeonMap = -1;
    public static Player player;
    public static Display display;

    public static void addGUIElement(GUIElement element) {

        guiElements.add(element);

    }

    public static void addDungeonMap(DungeonMap dungeonMap) {

        dungeonMaps.add(dungeonMap);

    }

    public static void setPlayer(Player player) {

        ObjectController.player = player;

    }

    public static void setDisplay(Display display) {

        ObjectController.display = display;

    }

}
