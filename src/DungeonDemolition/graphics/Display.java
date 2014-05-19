package DungeonDemolition.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    public int width;
    public int height;
    public JPanel contentPane;
    public BufferStrategy bufferStrategy;

    public Display(String title, int width, int height) {

        super(title);

        this.width = width;
        this.height = height;

        createDisplay();
        createBufferStrategy();

    }

    private void createDisplay() {

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentPane = new JPanel(true);
        setContentPane(contentPane);
        contentPane.setSize(width, height);

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

        Graphics graphics = bufferStrategy.getDrawGraphics();

        //render code here

        contentPane.setSize(width, height);
        contentPane.update(graphics);

        graphics.dispose();
        bufferStrategy.show();

    }

}
