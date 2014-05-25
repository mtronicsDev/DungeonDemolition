package dungeonDemolition.objects;

import dungeonDemolition.graphics.Animation;
import dungeonDemolition.util.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

    public Vector2f position;
    public Vector2f percentRotation = new Vector2f();
    public float rotation = 0;
    public Animation animation;

    public Player(String animationName) {

        animation = new Animation(animationName);

    }

    public void update() {

        Vector2f currentPosition = new Vector2f(position);

        if (Input.isKeyPressed(KeyEvent.VK_W)) position.y -= 100 * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_S)) position.y += 100 * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_A)) position.x -= 100 * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_D)) position.x += 100 * TimeHelper.deltaTime;


        if (VectorHelper.areEqual(currentPosition, position)) animation.update(false);

        else {

            animation.update(true);

            Vector2f direction = VectorHelper.normalizeVector(VectorHelper.subtractVectors(currentPosition, position));

            if (VectorHelper.getScalarProduct(direction, new Vector2f(1, 0)) >= 0) rotation = VectorHelper.getAngle(direction, new Vector2f(0, 1));

            else rotation = -VectorHelper.getAngle(direction, new Vector2f(0, 1));

        }

    }

    public void render(Graphics graphics) {

        AffineTransform transform = new AffineTransform();
        transform.translate(ObjectController.display.size.x / 2, ObjectController.display.size.y / 2);
        transform.rotate(-rotation);
        transform.translate(-20, -20);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(animation.getCurrentFrame(), transform, null);

    }

}
