package dungeonDemolition.objects.entities;

import dungeonDemolition.graphics.Animation;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    public Vector2f position;
    public Vector2f percentRotation = new Vector2f();
    public float rotation = 0;
    public Animation movementAnimation;
    public float health;
    public Animation deathAnimation;

    public Entity(String movementAnimationName) {

        movementAnimation = new Animation(movementAnimationName, true);

    }

    public Entity(String movementAnimationName, String deathAnimationName) {

        movementAnimation = new Animation(movementAnimationName, true);
        deathAnimation = new Animation(deathAnimationName, false);

    }

    public void update() {

        if (health <= 0) {

            if (deathAnimation != null)
                if (deathAnimation.oneLoopPassed) ObjectController.entities.remove(this);
                else deathAnimation.update(true);

            else ObjectController.entities.remove(this);

        }

    }

    public abstract void render(Graphics graphics);

}
