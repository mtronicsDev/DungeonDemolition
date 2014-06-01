package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Bullet;
import dungeonDemolition.util.Vector2f;

public class MachineGun extends Weapon {

    public MachineGun() {

        super("8", 0.1f, 3);

        automaticallyShooting = true;
        maxRemainingAmmoCount = 500;
        remainingAmmoCount = 500;
        maxCurrentAmmoCount = 50;
        currentAmmoCount = 50;

    }

    public void shoot() {

        super.shoot();

        ObjectController.projectiles.add(new Bullet(
                new Vector2f(ObjectController.entities.get("player").position),
                ObjectController.entities.get("player").percentRotation
        ));

    }

}
