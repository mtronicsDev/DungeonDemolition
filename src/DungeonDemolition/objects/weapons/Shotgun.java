package dungeonDemolition.objects.weapons;

public class Shotgun extends Weapon {

    public Shotgun() {

        super("12", 0.5f, 7);

        automaticallyShooting = false;
        maxRemainingAmmoCount = 36;
        remainingAmmoCount = 36;
        maxCurrentAmmoCount = 10;
        currentAmmoCount = 10;

    }

    public void shoot() {

        super.shoot();

    }

}
