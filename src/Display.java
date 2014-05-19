import javax.swing.*;

public class Display extends JFrame {

    public int width;
    public int height;

    public Display(String title, int width, int height) {

        super(title);

        this.width = width;
        this.height = height;

        createDisplay();

    }

    public void createDisplay() {

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setEnabled(true);
        setVisible(true);

    }

}
