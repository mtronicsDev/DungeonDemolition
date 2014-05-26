package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.physics.Collider;
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

        Vector2f movedSpace = new Vector2f();

        if (Input.isKeyPressed(KeyEvent.VK_W)) movedSpace.y -= speed * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_S)) movedSpace.y += speed * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_A)) movedSpace.x -= speed * TimeHelper.deltaTime;

        if (Input.isKeyPressed(KeyEvent.VK_D)) movedSpace.x += speed * TimeHelper.deltaTime;


        movedSpace = Collider.getMovedSpace(this, movedSpace);

        if (VectorHelper.areEqual(movedSpace, new Vector2f())) animation.update(false);

        else {

            animation.update(true);

            Vector2f direction = VectorHelper.normalizeVector(VectorHelper.negateVector(movedSpace));

            if (VectorHelper.getScalarProduct(direction, new Vector2f(1, 0)) <= 0) rotation = VectorHelper.getAngle(direction, new Vector2f(0, 1));

            else rotation = -VectorHelper.getAngle(direction, new Vector2f(0, 1));

        }

        position = VectorHelper.sumVectors(new Vector2f[] {position, movedSpace});

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
