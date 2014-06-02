package dungeonDemolition.objects.weapons.projectiles;

import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public class Shot extends Projectile {

    public Shot(Vector2f position, Vector2f direction) {

        super(position, VectorHelper.multiplyVectorByFloat(direction, -400), 10, 0, null, "projectiles/shot");

    }

}
