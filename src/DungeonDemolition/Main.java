package dungeonDemolition;

import dungeonDemolition.dungeons.rooms.Room;
import dungeonDemolition.dungeons.rooms.RoomBig;
import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.Player;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.Vector2i;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600);
        ObjectController.setDisplay(display);

        ObjectController.setPlayer(new Player(new Vector2f(100, 100), new Vector2f()));

        ObjectController.addGUIElement(new GUIRectangle(new Vector2i(10, 35), new Vector2i(30, 30), Color.blue, true));
        ObjectController.addGUIElement(new GUIText(new Vector2i(10, 85), Color.green, "The thing above is a blue gui rectangle."));

        Room roomBig = new RoomBig();
        roomBig.position = new Vector2i(50, 50);
        roomBig.size = new Vector2i(300, 240);
        ObjectController.addRoom(roomBig);

        TimeHelper.initialize();

        while (true) {

            TimeHelper.update();

            ObjectController.player.update();

            for (Room room : ObjectController.rooms) {

                room.update();

            }

            display.update();

        }

    }

}
