package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Enemy;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public class Knife extends Weapon {

    public Knife() {

        super("weapons/knife", 1, 0);

        automaticallyShooting = false;

    }

    public void shoot() {

        Entity player = ObjectController.entities.get("player");

        for (Entity entity : ObjectController.entities.values())
            if (entity instanceof Enemy) {

                Vector2f difference = VectorHelper.subtractVectors(VectorHelper.sumVectors(new Vector2f[] {player.position, new Vector2f(20, 20)}),
                        VectorHelper.sumVectors(new Vector2f[] {entity.position, new Vector2f(20, 20)}));

                if (VectorHelper.getLength(difference) <= 65 && VectorHelper.getAngle(difference, player.percentRotation) <= Math.toRadians(30))
                    entity.health -= 30;

            }

    }

}
