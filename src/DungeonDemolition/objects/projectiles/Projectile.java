package dungeonDemolition.objects.projectiles;

import dungeonDemolition.graphics.Animation;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Projectile {

    public Vector2f position;
    public Vector2f speed;
    public float damage; //min 0, max 100
    public float radius;
    public BufferedImage texture;
    public Animation destructiveAnimation;
    public boolean alive = true;

    public Projectile(Vector2f position, Vector2f speed, float damage, float radius, Animation destructiveAnimation, String textureName) {

        this.position = position;
        this.speed = speed;
        this.damage = damage;
        this.radius = radius;
        this.destructiveAnimation = destructiveAnimation;

        try {

            texture = ImageIO.read(new File("res/textures/" + textureName + "png"));

        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }

    public void update() {

        if (alive) {

            position = VectorHelper.sumVectors(new Vector2f[]{position, VectorHelper.multiplyVectorByFloat(speed, TimeHelper.deltaTime)});

            Object[] collisionData = Collider.getCollisionData(this);

            if ((Boolean) collisionData[0]) {

                for (Entity entity : (List<Entity>) collisionData[1])
                    entity.health -= damage;

                if (radius != 0) {

                    for (Entity entity : ObjectController.entities.values()) {

                        boolean alreadyHit = false;

                        for (Entity alreadyHitEntity : (List<Entity>) collisionData[1]) {

                            if (entity == alreadyHitEntity) alreadyHit = true;

                        }

                        if (!alreadyHit) entity.health -= damage;

                    }

                }

                onDestroy();

            }

        } else {

            if (destructiveAnimation != null)
                if (destructiveAnimation.oneLoopPassed)
                    ObjectController.projectiles.remove(this);
                else destructiveAnimation.update(true);

            else ObjectController.projectiles.remove(this);

        }

    }

    public void onDestroy() {

        alive = false;

        if (destructiveAnimation != null) destructiveAnimation.update(true);

    }

}
