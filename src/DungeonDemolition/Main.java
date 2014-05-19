package DungeonDemolition;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600);
        display.setBackground(Color.cyan);

        ObjectController.setPlayer(new Player(new Point(100, 100), new Point()));

        while (true) {

            display.update();

        }

    }
  
}
