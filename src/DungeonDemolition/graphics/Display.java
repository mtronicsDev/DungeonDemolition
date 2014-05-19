package DungeonDemolition.graphics;

import DungeonDemolition.gameObjects.DungeonMap;
import DungeonDemolition.util.ObjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    public Point size;
    public JPanel contentPane;
    public BufferStrategy bufferStrategy;

    public Display(String title, int width, int height) {

        super(title);

        size = new Point(width, height);

        createDisplay();
        createBufferStrategy();

    }

    private void createDisplay() {

        setSize(size.x, size.y);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentPane = new JPanel(true);
        setContentPane(contentPane);
        contentPane.setSize(size.x, size.y);

        setEnabled(true);
        setVisible(true);

    }

    private void createBufferStrategy() {

        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();

    }

    public Display setBackgroundColor(Color background) {

        contentPane.setBackground(background);

        return this;

    }

    public void update() {

        size.x = getWidth();
        size.y = getHeight();

        Graphics graphics = bufferStrategy.getDrawGraphics();

        for (DungeonMap dungeonMap : ObjectController.dungeons) {

            //dungeon render code

        }

        //player render code

        contentPane.setSize(size.x, size.y);
        contentPane.update(graphics);

        graphics.dispose();
        bufferStrategy.show();

    }

}
