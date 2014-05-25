package dungeonDemolition.objects;

import dungeonDemolition.graphics.Animation;
import dungeonDemolition.util.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

    public Vector2f position;
    public Vector2f rotation;
    public Animation animation;

    public Player(Vector2f position, Vector2f rotation, String animationName) {

        this.position = position;
        this.rotation = rotation;

        animation = new Animation(animationName);

    }

    public void update() {

        Vector2f currentPosition = new Vector2f(position);

        if (Input.isKeyPressed(KeyEvent.VK_W)) position.y -= 100 * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_S)) position.y += 100 * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_A)) position.x -= 100 * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_D)) position.x += 100 * TimeHelper.deltaTime;

        animation.update(!VectorHelper.areEqual(currentPosition, position));

    }

    public void render(Graphics graphics) {

        graphics.drawImage(animation.getCurrentFrame(), ObjectController.display.size.x / 2 - 20, ObjectController.display.size.y / 2 - 20, 40, 40, null);

    }

}
