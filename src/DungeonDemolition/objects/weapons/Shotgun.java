package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Shot;
import dungeonDemolition.util.Matrix2f;
import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.Vector2f;

public class Shotgun extends Weapon {

    public Shotgun() {

        super("weapons/shotgun", 1, 10);

        automaticallyShooting = false;
        maxRemainingAmmoCount = 36;
        remainingAmmoCount = 36;
        maxCurrentAmmoCount = 10;
        currentAmmoCount = 10;

    }

    public void shoot() {

        super.shoot();

        int shotCount = Randomizer.getRandomInt(3, 8);

        for (int count = 0; count < shotCount; count++) {

            float angle = (float) Math.toRadians(Randomizer.getRandomInt(-15, 15));

            Matrix2f rotationMatrix = new Matrix2f(
                    new Vector2f((float) Math.cos(angle), (float) -Math.sin(angle)),
                    new Vector2f((float) Math.sin(angle), (float) Math.cos(angle))
            );

            Vector2f direction = rotationMatrix.multiplyByVector(ObjectController.entities.get("player").percentRotation);

            ObjectController.projectiles.add(new Shot(
                    new Vector2f(ObjectController.entities.get("player").position),
                    direction
            ));

        }

    }

}
