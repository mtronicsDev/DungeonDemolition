package DungeonDemolition;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    public Point size;
    public BufferStrategy bufferStrategy;

    public Display(String title, int width, int height) {

        super(title);

        size = new Point(width, height);

        createDisplay();
        createBufferStrategy();

        addKeyListener(new Input());

    }

    private void createDisplay() {

        setSize(size.x, size.y);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setEnabled(true);
        setVisible(true);

    }

    private void createBufferStrategy() {

        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();

    }

    public void update() {

        size.x = getWidth();
        size.y = getHeight();

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, size.x, size.y);

        for (DungeonMap dungeonMap : ObjectController.dungeons) {

            //dungeon render code

        }

        if (ObjectController.player != null) {

            graphics.setColor(Color.red);
            graphics.fillRect(ObjectController.player.position.x, ObjectController.player.position.y, 50, 50);

        }

        graphics.dispose();
        bufferStrategy.show();

    }

}
