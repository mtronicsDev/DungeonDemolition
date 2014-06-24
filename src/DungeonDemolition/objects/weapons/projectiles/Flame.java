package dungeonDemolition.objects.weapons.projectiles;

import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public class Flame extends Projectile {

    public Flame(Vector2f position, Vector2f direction, int level) {
        super(position, VectorHelper.multiplyVectorByFloat(direction, -100), 1, 10, null, "projectiles/flame", level);
    }

}
