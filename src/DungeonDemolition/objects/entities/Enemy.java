package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Enemy extends Entity {

    public Enemy(String movementAnimationName) {

        super(movementAnimationName);

        health = 80;

    }

    public Enemy(String movementAnimationName, String deathAnimationName) {

        super(movementAnimationName, deathAnimationName);

        health = 80;

    }

    public void update() {

        super.update();

        if (health > 0) {

            float speed = 150 * TimeHelper.deltaTime;

            Vector2f movementDirection = VectorHelper.normalizeVector(VectorHelper.subtractVectors(ObjectController.entities.get("player").position, position));

            Vector2f movedSpace = VectorHelper.multiplyVectorByFloat(movementDirection, speed);

            if (!VectorHelper.areEqual(movedSpace, new Vector2f()))
                movedSpace = Collider.getMovedSpace(this, movedSpace);

            if (VectorHelper.areEqual(movedSpace, new Vector2f())) movementAnimation.update(false);

            else {

                movementAnimation.update(true);

                Vector2f direction = VectorHelper.normalizeVector(VectorHelper.negateVector(movedSpace));

                if (VectorHelper.getScalarProduct(direction, new Vector2f(1, 0)) <= 0)
                    rotation = VectorHelper.getAngle(direction, new Vector2f(0, 1));

                else rotation = -VectorHelper.getAngle(direction, new Vector2f(0, 1));

            }

            position = VectorHelper.sumVectors(new Vector2f[]{position, movedSpace});

        }

    }

    public void render(Graphics graphics) {

        if (!(((position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20 < -40)
                || ((position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20) >= ObjectController.display.size.x
                || ((position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20) < -40
                || ((position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20) >= ObjectController.display.size.y)) {

            AffineTransform transform = new AffineTransform();
            transform.translate((int) (position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2,
                    (int) (position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2);
            transform.rotate(rotation);
            transform.translate(-20, -20);

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(movementAnimation.getCurrentFrame(), transform, null);

        }

    }

}
