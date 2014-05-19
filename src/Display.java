import javax.swing.*;

public class Display extends JFrame {

    public int width;
    public int height;
    public JPanel contentPane;

    public Display(String title, int width, int height) {

        super(title);

        this.width = width;
        this.height = height;

        createDisplay();

    }

    private void createDisplay() {

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentPane = new JPanel(true);
        setContentPane(contentPane);

        setEnabled(true);
        setVisible(true);

    }

}
