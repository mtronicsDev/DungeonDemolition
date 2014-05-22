package dungeonDemolition.objects;

import dungeonDemolition.util.Input;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Timer;
import dungeonDemolition.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

    public Vector2f position;
    public Vector2f rotation;
    public boolean moving;
    public List<Float> animationFrameTimes = new ArrayList<Float>();
    public List<BufferedImage> frames = new ArrayList<BufferedImage>();
    public int currentFrame = 0;
    public Timer timer;

    public Player(Vector2f position, Vector2f rotation, String animationName) {

        this.position = position;
        this.rotation = rotation;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File("res/textures/animations/" + animationName + ".dda")));

            String line;

            while ((line = reader.readLine()) != null) {

                animationFrameTimes.add(Float.valueOf(line.split(" ")[1]));
                frames.add(ImageIO.read(new File("res/textures/" + line.split(" ")[0] + ".png")));

            }

            timer = new Timer(animationFrameTimes.get(0));

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            System.exit(1);

        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }

    public void update() {

        moving = false;

        if (Input.isKeyPressed(KeyEvent.VK_W)) {

            position.y -= 100 * TimeHelper.deltaTime;
            moving = true;

        }

        if (Input.isKeyPressed(KeyEvent.VK_S)) {

            position.y += 100 * TimeHelper.deltaTime;
            moving = true;

        }

        if (Input.isKeyPressed(KeyEvent.VK_A)) {

            position.x -= 100 * TimeHelper.deltaTime;
            moving = true;

        }

        if (Input.isKeyPressed(KeyEvent.VK_D)) {

            position.x += 100 * TimeHelper.deltaTime;
            moving = true;

        }

        if (moving) {

            timer.running = true;
            timer.update();

            if (timer.hasFinished()) {

                currentFrame++;

                if (currentFrame == frames.size()) currentFrame = 0;

                timer.endTime = animationFrameTimes.get(currentFrame);
                timer.restart();

            }

        } else {

            currentFrame = 0;

            timer.endTime = 0.01f;
            timer.restart();
            timer.running = false;

        }

    }

    public void render(Graphics graphics) {

        if (moving) graphics.drawImage(frames.get(currentFrame), ObjectController.display.size.x / 2 - 20, ObjectController.display.size.y / 2 - 20, 40, 40, null);

        else graphics.drawImage(frames.get(0), ObjectController.display.size.x / 2 - 20, ObjectController.display.size.y / 2 - 20, 40, 40, null);

    }

}
