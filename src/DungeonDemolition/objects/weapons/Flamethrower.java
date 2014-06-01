package dungeonDemolition.objects.weapons;

public class Flamethrower extends Weapon {

    public Flamethrower() {

        super("9", 0, 5);

        automaticallyShooting = true;
        maxRemainingAmmoCount = 10000;
        remainingAmmoCount = 10000;
        maxCurrentAmmoCount = 1000;
        currentAmmoCount = 1000;

    }

    public void shoot() {

        super.shoot();

    }

}
