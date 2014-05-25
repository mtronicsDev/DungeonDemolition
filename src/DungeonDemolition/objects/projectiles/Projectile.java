package dungeonDemolition.objects.projectiles;

import dungeonDemolition.graphics.Animation;
import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public abstract class Projectile {

    public Vector2f position;
    public Vector2f speed;
    public float damage; //min 0, max 100
    public float radius;
    public Animation destructiveAnimation;
    public boolean alive = true;

    public Projectile(Vector2f position, Vector2f speed, float damage, float radius, Animation destructiveAnimation) {

        this.position = position;
        this.speed = speed;
        this.damage = damage;
        this.radius = radius;
        this.destructiveAnimation = destructiveAnimation;

    }

    public void update() {

        if (alive) {

            position = VectorHelper.sumVectors(new Vector2f[] {position, VectorHelper.multiplyVectorByFloat(speed, TimeHelper.deltaTime)});

            /*if (collidingWithSomething) {

                boolean hitEntity = collidingObject instanceof Entity;

                if (hitEntity) ((Entity) collidingObject).health -= damage;

                if (radius != 0)
                    for (Entity entity : ObjectController.entities.values())
                        if (VectorHelper.getLength(VectorHelper.subtractVectors(entity.position, position)) <= radius)
                            if (!hitEntity) entity.health -= damage;
                            else if (entity != (Entity) collidingObject) entity.health -= damage;

                onDestroy();

            }*/

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
