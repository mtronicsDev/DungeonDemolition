package dungeonDemolition.objects.weapons.projectiles;

import dungeonDemolition.graphics.Animation;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.TextureHelper;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class Projectile {

    public Vector2f position;
    public Vector2f speed;
    public float damage; //min 0, max 100
    public float radius;
    public BufferedImage texture;
    public Animation destructiveAnimation;
    public boolean textureFlippingFactors[] = new boolean[2];
    public boolean alive = true;

    public Projectile(Vector2f position, Vector2f speed, float damage, float radius, String animationName, String textureName) {

        this.position = position;
        this.speed = speed;
        this.damage = damage;
        this.radius = radius;

        if (animationName != null) destructiveAnimation = new Animation(animationName, false);

        texture = TextureHelper.loadImage(textureName);

        if (this instanceof Rocket) textureFlippingFactors[0] = true;

        else textureFlippingFactors[0] = false;

    }

    public void update() {

        if (textureFlippingFactors[0] && !textureFlippingFactors[1]) {

            float angle;

            if (VectorHelper.getScalarProduct(speed, new Vector2f(1, 0)) <= 0)
                angle = VectorHelper.getAngle(speed, new Vector2f(0, 1));

            else angle = -VectorHelper.getAngle(speed, new Vector2f(0, 1));

            AffineTransform transform = new AffineTransform();
            transform.translate(40, 40);
            transform.rotate(angle);

            AffineTransformOp affineTransformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

            texture = affineTransformOp.filter(texture, null);

            if (destructiveAnimation != null)
                for (int count = 0; count < destructiveAnimation.frames.size(); count++)
                    destructiveAnimation.frames.set(count, affineTransformOp.filter(destructiveAnimation.frames.get(count), null));

            textureFlippingFactors[1] = true;

        }

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

                        float difference = VectorHelper.getLength(VectorHelper.subtractVectors(entity.position, position));

                        if (!alreadyHit && difference <= radius) entity.health -= damage / difference * 80;

                    }

                }

                onDestroy();

            }

        } else {

            if (destructiveAnimation != null)
                if (destructiveAnimation.oneLoopPassed)
                    ObjectController.projectilesToRemove.add(this);
                else destructiveAnimation.update(true);

            else ObjectController.projectilesToRemove.add(this);

        }

    }

    public void onDestroy() {

        alive = false;

        if (destructiveAnimation != null) destructiveAnimation.update(true);

    }

    public void render(Graphics graphics) {

        if (!(((position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20 < -40)
                || ((position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20) >= ObjectController.display.size.x
                || ((position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20) < -40
                || ((position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20) >= ObjectController.display.size.y)) {

            if (alive) graphics.drawImage(texture,
                    (int) (position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - texture.getWidth(),
                    (int) (position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - texture.getHeight(),
                    null);

            else if (destructiveAnimation != null) {

                BufferedImage currentFrame = destructiveAnimation.getCurrentFrame();

                graphics.drawImage(currentFrame,
                        (int) (position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - currentFrame.getWidth(),
                        (int) (position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - currentFrame.getHeight(),
                        null);

            }

        }

    }

}
