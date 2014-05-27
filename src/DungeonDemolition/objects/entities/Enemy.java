package dungeonDemolition.objects.entities;

import java.awt.*;

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



        }

    }

    public void render(Graphics graphics) {



    }

}
