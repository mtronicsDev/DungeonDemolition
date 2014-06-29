package dungeonDemolition.graphics;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.objects.gui.GUIPanel;
import dungeonDemolition.objects.weapons.projectiles.Projectile;
import dungeonDemolition.util.InputListener;
import dungeonDemolition.util.TextureHelper;
import dungeonDemolition.util.Vector2i;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Display extends JFrame {

    public Vector2i size;
    public BufferStrategy bufferStrategy;

    public Display(String title, int width, int height) {

        super(title);

        size = new Vector2i(width, height);

        createDisplay();
        createBufferStrategy();

        addKeyListener(new InputListener());
        addMouseListener(new InputListener());

    }

    private void createDisplay() {

        setSize(size.x, size.y);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        java.util.List<BufferedImage> iconImages = new ArrayList<BufferedImage>();
        iconImages.add(TextureHelper.loadImage("icons/icon16x16"));
        iconImages.add(TextureHelper.loadImage("icons/icon20x20"));
        iconImages.add(TextureHelper.loadImage("icons/icon32x32"));
        iconImages.add(TextureHelper.loadImage("icons/icon64x64"));

        setIconImages(iconImages);
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

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, size.x, size.y);

        if (ObjectController.currentDungeonMap != -1)
            ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).render(graphics);

        for (Projectile projectile : ObjectController.projectiles)
            projectile.render(graphics);

        for (Entity entity : ObjectController.entities.values())
            if (entity instanceof Enemy) entity.render(graphics);

        ObjectController.entities.get("player").render(graphics);

        for (GUIPanel panel : ObjectController.guiPanels.values())
            panel.render(graphics);

        graphics.dispose();
        bufferStrategy.show();

    }

}
