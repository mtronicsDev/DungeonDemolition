package dungeonDemolition.util;

import dungeonDemolition.objects.ObjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseWheelListener {

    public static Vector2f mousePosition = new Vector2f();
    private static boolean[] keyStats = new boolean[1024];
    private static boolean[] buttonStats = new boolean[1024];

    public static void update() {

        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePosition, ObjectController.display);

        Input.mousePosition.x = mousePosition.x;
        Input.mousePosition.y = mousePosition.y;

    }

    public static int getKeyCode(String key) {

        int keyCode = -1;

        for (int count = 0; count < keyStats.length; count++)
            if (key.equals(KeyEvent.getKeyText(count))) keyCode = count;

        return keyCode;

    }

    public static boolean isKeyPressed(int keyCode) {

        return keyStats[keyCode];

    }

    public static boolean isButtonPressed(int buttonCode) {

        return buttonStats[buttonCode];

    }

    public void keyPressed(KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();

        keyStats[keyCode] = true;

    }

    public void keyReleased(KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();

        keyStats[keyCode] = false;

    }

    public void mousePressed(MouseEvent mouseEvent) {

        int buttonCode = mouseEvent.getButton();

        buttonStats[buttonCode] = true;

    }

    public void mouseReleased(MouseEvent mouseEvent) {

        int buttonCode = mouseEvent.getButton();

        buttonStats[buttonCode] = false;

    }

    public void mouseWheelMoved(MouseWheelEvent e) {



    }

    public void mouseClicked(MouseEvent mouseEvent) {



    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    public void mouseEntered(MouseEvent mouseEvent) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }

}
