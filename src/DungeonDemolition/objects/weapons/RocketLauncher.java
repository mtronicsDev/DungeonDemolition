package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Rocket;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public class RocketLauncher extends Weapon {

    public RocketLauncher() {

        super("11", 1, 3);

    }

    public void shoot() {

        ObjectController.projectiles.add(new Rocket(
                new Vector2f(ObjectController.entities.get("player").position),
                VectorHelper.multiplyVectorByFloat(ObjectController.entities.get("player").percentRotation, 10)
        ));

    }

}
