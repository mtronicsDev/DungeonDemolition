package DungeonDemolition;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private static boolean[] keyStats = new boolean[1024];

    public static boolean isKeyPressed(int keyCode) {

        return keyStats[keyCode];

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();

        keyStats[keyCode] = true;

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();

        keyStats[keyCode] = false;

    }

    public void keyTyped(KeyEvent keyEvent) {
    }

}
