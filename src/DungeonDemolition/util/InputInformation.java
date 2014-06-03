package dungeonDemolition.util;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.util.Vector2f;

import javax.swing.*;
import java.awt.*;

public class InputInformation {

    public static Vector2f mousePosition = new Vector2f();
    private static boolean[] keyStats = new boolean[1024];
    private static boolean[] buttonStats = new boolean[1024];

    public static void update() {

        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePosition, ObjectController.display);

        InputInformation.mousePosition.x = mousePosition.x;
        InputInformation.mousePosition.y = mousePosition.y;


    }

    public static boolean isKeyPressed(int key) {

        return InputListener.isKeyPressed(key);

    }

    public static boolean isKeyDown(int key) {

        boolean isAlreadyActivated = keyStats[key];
        keyStats[key] = isKeyPressed(key);
        return keyStats[key] != isAlreadyActivated && !isAlreadyActivated;

    }

    public static boolean isButtonPressed(int button) {

        return InputListener.isButtonPressed(button);

    }

    public static boolean isButtonDown(int button) {

        boolean isAlreadyActivated = buttonStats[button];
        buttonStats[button] = isButtonPressed(button);
        return buttonStats[button] != isAlreadyActivated && !isAlreadyActivated;

    }

}
