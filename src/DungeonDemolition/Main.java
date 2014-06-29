package dungeonDemolition;

import dungeonDemolition.graphics.Display;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.gui.ButtonPressingMethod;
import dungeonDemolition.objects.gui.GUIButton;
import dungeonDemolition.objects.gui.GUIElement;
import dungeonDemolition.objects.gui.GUIPanel;
import dungeonDemolition.util.InputInformation;
import dungeonDemolition.util.PreferenceHelper;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2i;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {

        PreferenceHelper.loadPreferences();
        Display display = new Display("Dungeon Demolition", 1070, 600);
        ObjectController.setDisplay(display);

        TimeHelper.initialize();

        ObjectController.addGUIPanel("inGame", new GUIPanel(
                new GUIElement[]{},
                false
        ));

        ObjectController.addGUIPanel("menu", new GUIPanel(
                new GUIElement[]{
                        new GUIButton(new Vector2i(435, 100), new Vector2i(200, 100), Color.yellow, Color.black, 30, "Play", new ButtonPressingMethod() {
                            @Override
                            public void press() {
                                ObjectController.run();
                            }
                        }),
                        new GUIButton(new Vector2i(435, 250), new Vector2i(200, 100), Color.orange, Color.black, 20, "Generate New Level", new ButtonPressingMethod() {
                            @Override
                            public void press() {
                                ObjectController.removeAll();
                                ObjectController.generateNewLevel(true);
                            }
                        }),
                        new GUIButton(new Vector2i(435, 400), new Vector2i(200, 100), Color.red, Color.black, 30, "Quit", new ButtonPressingMethod() {
                            @Override
                            public void press() {
                                System.exit(0);
                            }
                        })
                },
                true
        ));

        ObjectController.generateNewLevel(true);

        while (true) {

            if (InputInformation.isKeyDown(KeyEvent.VK_ESCAPE)) {

                if (ObjectController.running) ObjectController.pause();

                else ObjectController.run();

            }

            TimeHelper.update();
            InputInformation.update();
            ObjectController.updateAll();

        }

    }

}
