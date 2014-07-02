package dungeonDemolition.objects.weapons.projectiles;

import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public class Rocket extends Projectile {

    public Rocket(Vector2f position, Vector2f direction, int level) {

        super(position, VectorHelper.multiplyVectorByFloat(direction, -100), 100, 120, "bigexplosion", "projectiles/rocket", level);

    }

}
