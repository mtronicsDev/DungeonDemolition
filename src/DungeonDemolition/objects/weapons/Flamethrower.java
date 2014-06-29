package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Flame;
import dungeonDemolition.util.Matrix2f;
import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.Vector2f;

public class FlameThrower extends Weapon {

    public FlameThrower() {

        super("weapons/flamethrower", 0.01f, 4);

        automaticallyShooting = true;
        maxRemainingAmmoCount = 1000;
        remainingAmmoCount = 1000;
        maxCurrentAmmoCount = 200;
        currentAmmoCount = 200;

    }

    public void shoot() {

        super.shoot();

        int flameCount = Randomizer.getRandomInt(10, 15);

        for (int count = 0; count < flameCount; count++) {

            float angle = (float) Math.toRadians(Randomizer.getRandomInt(-20, 20));

            Matrix2f rotationMatrix = new Matrix2f(
                    new Vector2f((float) Math.cos(angle), (float) -Math.sin(angle)),
                    new Vector2f((float) Math.sin(angle), (float) Math.cos(angle))
            );

            Vector2f direction = rotationMatrix.multiplyByVector(ObjectController.entities.get("player").percentRotation);

            ObjectController.projectiles.add(new Flame(
                    new Vector2f(ObjectController.entities.get("player").position),
                    direction,
                    ObjectController.currentDungeonMap
            ));

        }

    }

}
