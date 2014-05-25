package dungeonDemolition.graphics;

import dungeonDemolition.util.Timer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Animation {

    public List<Float> animationFrameTimes = new ArrayList<Float>();
    public List<BufferedImage> frames = new ArrayList<BufferedImage>();
    public int currentFrame = 0;
    public Timer timer;

    public Animation(String animationName) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File("res/textures/animations/" + animationName + ".dda")));

            String line;

            while ((line = reader.readLine()) != null) {

                frames.add(ImageIO.read(new File("res/textures/" + line.split(" ")[0] + ".png")));
                animationFrameTimes.add(Float.valueOf(line.split(" ")[1]));

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

    public void update(boolean animationNeeded) {

        if (animationNeeded) {

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

    public BufferedImage getCurrentFrame() {

        return frames.get(currentFrame);

    }

}
