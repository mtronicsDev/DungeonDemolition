package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Flame;

public class FlameThrower extends Weapon {

    public Flame flame = new Flame(ObjectController.entities.get("player").position, ObjectController.entities.get("player").percentRotation, "tiles/0", 2);

    public FlameThrower() {

        super("weapons/flamethrower", 0.01f, 4);

        automaticallyShooting = true;
        maxRemainingAmmoCount = 1000;
        remainingAmmoCount = 1000;
        maxCurrentAmmoCount = 200;
        currentAmmoCount = 200;

    }

    public void update() {

        super.update();

        flame.active = flame.currentlyShooting;

        flame.currentlyShooting = false;

        flame.update();

    }

    public void shoot() {

        super.shoot();

        flame.currentlyShooting = true;

    }

}