package dungeonDemolition.graphics;

import dungeonDemolition.util.TextureHelper;
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
    public float frameTimeModifier = 1;
    public boolean repeatAfterOneLoop;
    public boolean oneLoopPassed = false;

    public Animation(String animationName, boolean repeatAfterOneLoop) {

        this.repeatAfterOneLoop = repeatAfterOneLoop;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File("res/textures/animations/" + animationName + ".dda")));

            String line;

            while ((line = reader.readLine()) != null) {

                frames.add(TextureHelper.loadImage(line.split(" ")[0]));
                animationFrameTimes.add(Float.valueOf(line.split(" ")[1]));

            }

            timer = new Timer(animationFrameTimes.get(0) * frameTimeModifier);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            System.exit(1);

        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }

    public void update(boolean animationNeeded) {

        if (!oneLoopPassed || repeatAfterOneLoop) {

            if (animationNeeded) {

                timer.running = true;
                timer.update();

                if (timer.hasFinished()) {

                    oneLoopPassed = true;

                    currentFrame++;

                    if (currentFrame == frames.size()) currentFrame = 0;

                    timer.endTime = animationFrameTimes.get(currentFrame) * frameTimeModifier;
                    timer.restart();

                }

            } else {

                currentFrame = 0;

                timer.endTime = 0.01f;
                timer.restart();
                timer.running = false;

            }

        }

    }

    public BufferedImage getCurrentFrame() {

        return frames.get(currentFrame);

    }

}
