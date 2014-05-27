package dungeonDemolition.util;

import dungeonDemolition.objects.ObjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener {

    public static Vector2f mousePosition = new Vector2f();
    private static boolean[] keyStats = new boolean[1024];
    private static boolean[] buttonStats = new boolean[1024];

    public static void update() {

        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePosition, ObjectController.display);

        Input.mousePosition.x = mousePosition.x;
        Input.mousePosition.y = mousePosition.y;

    }

    public static boolean isKeyPressed(int keyCode) {

        return keyStats[keyCode];

    }

    public static boolean isButtonPressed(int buttonCode) {

        return buttonStats[buttonCode];

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

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

        int buttonCode = mouseEvent.getButton();

        buttonStats[buttonCode] = true;

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

        int buttonCode = mouseEvent.getButton();

        buttonStats[buttonCode] = false;

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
