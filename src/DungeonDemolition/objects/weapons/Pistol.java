package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.weapons.projectiles.Bullet;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

public class Pistol extends Weapon {

    public Pistol() {

        super("pistol", 0.1f, 0.5f);

    }

    public void shoot() {

        ObjectController.projectiles.add(new Bullet(
                new Vector2f(ObjectController.entities.get("player").position),
                VectorHelper.multiplyVectorByFloat(ObjectController.entities.get("player").percentRotation, 100)
        ));

    }

}
