package dungeonDemolition;

import dungeonDemolition.dungeons.rooms.Room;
import dungeonDemolition.dungeons.rooms.RoomBig;
import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.Player;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.Vector2i;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600);
        display.setBackground(Color.cyan);
        ObjectController.setDisplay(display);

        ObjectController.setPlayer(new Player(new Vector2f(100, 100), new Vector2f()));

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
