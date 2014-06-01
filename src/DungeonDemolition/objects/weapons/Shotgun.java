package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Shot;
import dungeonDemolition.util.Vector2f;

public class Shotgun extends Weapon {

    public Shotgun() {

        super("12", 1, 10);

        automaticallyShooting = false;
        maxRemainingAmmoCount = 36;
        remainingAmmoCount = 36;
        maxCurrentAmmoCount = 10;
        currentAmmoCount = 10;

    }

    public void shoot() {

        super.shoot();

        ObjectController.projectiles.add(new Shot(
                new Vector2f(ObjectController.entities.get("player").position),
                ObjectController.entities.get("player").percentRotation
        ));

    }

}
