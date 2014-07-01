package dungeonDemolition.objects.weapons.projectiles;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.util.TextureHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

import java.awt.image.BufferedImage;

public class Flame {

    public Vector2f position;
    public Vector2f direction;
    public BufferedImage texture;
    public boolean active = false;
    public boolean currentlyShooting = false;
    public int damage;

    public Flame(Vector2f position, Vector2f direction, String textureName, int damage) {

        this.position = position;
        this.direction = direction;
        this.damage = damage;
        texture = TextureHelper.loadImage(textureName);

    }

    public void update() {

        if (active) {

            Entity player = ObjectController.entities.get("player");

            for (Entity enemy : ObjectController.entities.values()) {

                if (enemy instanceof Enemy) {
                    Vector2f difference = VectorHelper.subtractVectors(player.position, enemy.position);

                    if (VectorHelper.getLength(difference) <= 150 && VectorHelper.getAngle(difference, player.percentRotation) <= Math.toRadians(35))
                        enemy.health -= damage;

                }

            }

        }

    }

}
