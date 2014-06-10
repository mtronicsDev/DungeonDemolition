package dungeonDemolition.objects.entities;

import dungeonDemolition.graphics.Animation;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    public Vector2f position = new Vector2f();
    public int level;
    public float speed;
    public Vector2f percentRotation = new Vector2f();
    public float rotation = 0;
    public Animation movementAnimation;
    public float maxHealth = 10;
    public float health;
    public Animation deathAnimation;

    public Entity(String movementAnimationName, float speed, int level) {

        movementAnimation = new Animation(movementAnimationName, true);

        this.speed = speed;
        this.level = level;

    }

    public Entity(String movementAnimationName, String deathAnimationName, float speed, int level) {

        movementAnimation = new Animation(movementAnimationName, true);
        deathAnimation = new Animation(deathAnimationName, false);

        this.speed = speed;
        this.level = level;

    }

    public void update() {

        if (level == ObjectController.currentDungeonMap) {

            if (health <= 0) {

                if (deathAnimation != null)
                    if (deathAnimation.oneLoopPassed) ObjectController.entitiesToRemove.add(this);
                    else deathAnimation.update(true);

                else ObjectController.entitiesToRemove.add(this);

            }

        }

    }

    public abstract void render(Graphics graphics);

}
