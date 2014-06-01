package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Rocket;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public class RocketLauncher extends Weapon {

    public RocketLauncher() {

        super("11", 0, 3);

        automaticallyShooting = false;
        maxRemainingAmmoCount = 3;
        remainingAmmoCount = 3;
        maxCurrentAmmoCount = 1;
        currentAmmoCount = 1;

    }

    public void shoot() {

        super.shoot();

        ObjectController.projectiles.add(new Rocket(
                new Vector2f(ObjectController.entities.get("player").position),
                ObjectController.entities.get("player").percentRotation
        ));

    }

}