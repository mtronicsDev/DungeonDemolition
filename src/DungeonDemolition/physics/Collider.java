package dungeonDemolition.physics;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.objects.projectiles.Projectile;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

import java.awt.*;

public class Collider {

    public static boolean areBoxesColliding(Box boxA, Box boxB) {

        return new Rectangle((int) boxA.position.x, (int) boxA.position.y, (int) boxA.size.x, (int) boxA.size.y).intersects(
                new Rectangle((int) boxB.position.x, (int) boxB.position.y, (int) boxB.size.x, (int) boxB.size.y));

    }

    public static boolean collidingWithSomething(Projectile projectile) {

        return false;

    }

    public static Vector2f getMovedSpace(Entity entity, Vector2f velocity) {

        Vector2f movedSpace = new Vector2f(velocity);

        for (DungeonTile dungeonTile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles) {

            if (!dungeonTile.passable) {

                if (areBoxesColliding(new Box(entity.position, new Vector2f(40, 40)), new Box(dungeonTile.position, new Vector2f(40, 40)))) {

                    for (Vector2f normal : dungeonTile.normals) {

                        if (VectorHelper.getScalarProduct(normal, movedSpace) < 0) {

                            if (VectorHelper.areEqual(normal, new Vector2f(0, -1)) || VectorHelper.areEqual(normal, new Vector2f(0, 1)))  movedSpace.y = 0;

                            else if (VectorHelper.areEqual(normal, new Vector2f(1, 0)) || VectorHelper.areEqual(normal, new Vector2f(-1, 0))) movedSpace.x = 0;

                        }

                    }

                }

            }

            if (VectorHelper.areEqual(movedSpace, new Vector2f())) break;

        }

        return  movedSpace;

    }

}
