package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.util.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Player extends Entity {

    public Player(String animationName) {

        super(animationName);

    }

    public void update() {

        float speed = 100;

        if (Input.isKeyPressed(KeyEvent.VK_SHIFT)) {

            speed = 200;
            animation.frameTimeModifier = 0.5f;

        } else animation.frameTimeModifier = 1;

        Vector2f currentPosition = new Vector2f(position);

        if (Input.isKeyPressed(KeyEvent.VK_W)) position.y -= speed * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_S)) position.y += speed * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_A)) position.x -= speed * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_D)) position.x += speed * TimeHelper.deltaTime;


        if (VectorHelper.areEqual(currentPosition, position)) animation.update(false);

        else {

            animation.update(true);

            Vector2f direction = VectorHelper.normalizeVector(VectorHelper.subtractVectors(currentPosition, position));

            if (VectorHelper.getScalarProduct(direction, new Vector2f(1, 0)) <= 0) rotation = VectorHelper.getAngle(direction, new Vector2f(0, 1));

            else rotation = -VectorHelper.getAngle(direction, new Vector2f(0, 1));

        }

        percentRotation = VectorHelper.normalizeVector(VectorHelper.subtractVectors(Input.mousePosition,
                VectorHelper.divideVectorByFloat(new Vector2f(ObjectController.display.size), 2)));

    }

    public void render(Graphics graphics) {

        AffineTransform transform = new AffineTransform();
        transform.translate(ObjectController.display.size.x / 2, ObjectController.display.size.y / 2);
        transform.rotate(rotation);
        transform.translate(-20, -20);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(animation.getCurrentFrame(), transform, null);

    }

}
