package dungeonDemolition.objects.entities;

import dungeonDemolition.graphics.Animation;
import dungeonDemolition.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    public Vector2f position;
    public Vector2f percentRotation = new Vector2f();
    public float rotation = 0;
    public Animation animation;
    public float health;

    public Entity(String animationName) {

        animation = new Animation(animationName, true);

    }

    public abstract void update();

    public abstract void render(Graphics graphics);

}
