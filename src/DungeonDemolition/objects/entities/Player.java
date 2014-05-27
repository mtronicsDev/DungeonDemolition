package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Player extends Entity {

    public Player(String movementAnimationName) {

        super(movementAnimationName);

        health = 100;

    }

    public Player(String movementAnimationName, String deathAnimationName) {

        super(movementAnimationName, deathAnimationName);

        health = 100;

    }

    public void update() {

        super.update();

        if (health > 0) {

            float speed = 100;

            if (Input.isKeyPressed(KeyEvent.VK_SHIFT)) {

                speed = 200;
                movementAnimation.frameTimeModifier = 0.5f;

            } else movementAnimation.frameTimeModifier = 1;

            Vector2f movedSpace = new Vector2f();

            if (Input.isKeyPressed(KeyEvent.VK_W)) movedSpace.y -= speed * TimeHelper.deltaTime;

            if (Input.isKeyPressed(KeyEvent.VK_S)) movedSpace.y += speed * TimeHelper.deltaTime;

            if (Input.isKeyPressed(KeyEvent.VK_A)) movedSpace.x -= speed * TimeHelper.deltaTime;

            if (Input.isKeyPressed(KeyEvent.VK_D)) movedSpace.x += speed * TimeHelper.deltaTime;


            if (!VectorHelper.areEqual(movedSpace, new Vector2f())) movedSpace = Collider.getMovedSpace(this, movedSpace);

            if (VectorHelper.areEqual(movedSpace, new Vector2f())) movementAnimation.update(false);

            else {

                movementAnimation.update(true);

                Vector2f direction = VectorHelper.normalizeVector(VectorHelper.negateVector(movedSpace));

                if (VectorHelper.getScalarProduct(direction, new Vector2f(1, 0)) <= 0) rotation = VectorHelper.getAngle(direction, new Vector2f(0, 1));

                else rotation = -VectorHelper.getAngle(direction, new Vector2f(0, 1));

            }

            position = VectorHelper.sumVectors(new Vector2f[] {position, movedSpace});

            percentRotation = VectorHelper.normalizeVector(VectorHelper.subtractVectors(Input.mousePosition,
                    VectorHelper.divideVectorByFloat(new Vector2f(ObjectController.display.size), 2)));

        }

    }

    public void render(Graphics graphics) {

        AffineTransform transform = new AffineTransform();
        transform.translate(ObjectController.display.size.x / 2, ObjectController.display.size.y / 2);
        transform.rotate(rotation);
        transform.translate(-20, -20);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(movementAnimation.getCurrentFrame(), transform, null);

    }

}
