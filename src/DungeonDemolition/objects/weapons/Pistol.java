package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Bullet;
import dungeonDemolition.util.Vector2f;

public class Pistol extends Weapon {

    public Pistol() {

        super("weapons/gun", 0.1f, 0.5f);

        automaticallyShooting = false;
        maxRemainingAmmoCount = 100;
        remainingAmmoCount = 100;
        maxCurrentAmmoCount = 10;
        currentAmmoCount = 10;

    }

    public void shoot() {

        super.shoot();

        ObjectController.projectiles.add(new Bullet(
                new Vector2f(ObjectController.entities.get("player").position),
                ObjectController.entities.get("player").percentRotation
        ));

    }

}
