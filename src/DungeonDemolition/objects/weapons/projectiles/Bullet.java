package dungeonDemolition.objects.weapons.projectiles;

import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public class Bullet extends Projectile {

    public Bullet(Vector2f position, Vector2f direction) {

        super(position, VectorHelper.multiplyVectorByFloat(direction, -500), 4, 0, null, "10");

    }

}
