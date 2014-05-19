package dungeonDemolition;

import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.Player;
import dungeonDemolition.util.TimeHelper;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600);
        display.setBackground(Color.cyan);
        ObjectController.setDisplay(display);

        ObjectController.setPlayer(new Player(new Point(100, 100), new Point()));

        TimeHelper.initialize();

        while (true) {

            TimeHelper.update();

            ObjectController.player.update();

            display.update();

        }

    }
  
}
