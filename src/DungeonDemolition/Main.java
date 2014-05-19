package DungeonDemolition;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Display display = new Display("Dungeon Demolition", 1070, 600)
            .setBackgroundColor(Color.cyan);

        while (true) {

            display.update();

        }

    }
  
}
