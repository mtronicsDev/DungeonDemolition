package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Enemy extends Entity {

    public Enemy(String movementAnimationName) {

        super(movementAnimationName);

        health = 80;

    }

    public Enemy(String movementAnimationName, String deathAnimationName) {

        super(movementAnimationName, deathAnimationName);

        health = 80;

    }

    public void update() {

        super.update();

        if (health > 0) {

            float speed = 150 * TimeHelper.deltaTime;

            Vector2f movementDirection = VectorHelper.normalizeVector(VectorHelper.subtractVectors(ObjectController.entities.get("player").position, position));

            boolean blocked = false;
            Object[] blockers = new Object[2];

            Vector2f idealMovedSpace = VectorHelper.multiplyVectorByFloat(movementDirection, speed);

            for (DungeonTile dungeonTile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles) {

                if (!dungeonTile.passable) {

                    boolean theoreticallyBlocking = false;

                    for (Vector2f normal : dungeonTile.normals)
                        if (VectorHelper.getAngle(idealMovedSpace, normal) < Math.toRadians(45)) theoreticallyBlocking = true;

                    if (theoreticallyBlocking) {

                        Vector2f middleTilePosition = VectorHelper.sumVectors(new Vector2f[] {dungeonTile.position, new Vector2f(20, 20)});
                        Vector2f middleEnemyPosition = VectorHelper.sumVectors(new Vector2f[] {position, new Vector2f(20, 20)});

                        Vector2f target = VectorHelper.sumVectors(new Vector2f[] {VectorHelper.sumVectors(new Vector2f[] {position, new Vector2f(20, 20)}), idealMovedSpace});

                        Vector2f difference = VectorHelper.subtractVectors(middleTilePosition, middleEnemyPosition);

                        if (VectorHelper.getLength(VectorHelper.subtractVectors(target, middleTilePosition)) <= 88
                                && VectorHelper.getAngle(idealMovedSpace, difference) <= Math.toRadians(45)) {

                            blocked = true;
                            blockers[0] = dungeonTile;

                        }

                    }

                }

            }

            if (!blocked)
                for (String key : ObjectController.entities.keySet()) {

                    if (key.startsWith("enemy")) {

                        Enemy enemy = (Enemy) ObjectController.entities.get(key);

                        if (enemy != this) {

                            Vector2f middleCollidingEnemyPosition = VectorHelper.sumVectors(new Vector2f[] {enemy.position, new Vector2f(20, 20)});
                            Vector2f middleEnemyPosition = VectorHelper.sumVectors(new Vector2f[] {position, new Vector2f(20, 20)});

                            Vector2f target = VectorHelper.sumVectors(new Vector2f[] {VectorHelper.sumVectors(new Vector2f[] {position, new Vector2f(20, 20)}), idealMovedSpace});

                            Vector2f difference = VectorHelper.subtractVectors(middleCollidingEnemyPosition, middleEnemyPosition);

                            if (VectorHelper.getLength(VectorHelper.subtractVectors(target, middleCollidingEnemyPosition)) <= 88
                                    && VectorHelper.getAngle(idealMovedSpace, difference) <= Math.toRadians(30)) {

                                blocked = true;
                                blockers[1] = enemy;

                            }

                        }

                    }

                }

            Vector2f movedSpace;

            if (!blocked) movedSpace = idealMovedSpace;

            else {

                //System.out.println("not able to move forward");

                movedSpace = idealMovedSpace;

            }

            if (!VectorHelper.areEqual(movedSpace, new Vector2f()))
                movedSpace = Collider.getMovedSpace(this, movedSpace);

            if (VectorHelper.areEqual(movedSpace, new Vector2f())) movementAnimation.update(false);

            else {

                movementAnimation.update(true);

                Vector2f direction = VectorHelper.normalizeVector(VectorHelper.negateVector(movedSpace));

                if (VectorHelper.getScalarProduct(direction, new Vector2f(1, 0)) <= 0)
                    rotation = VectorHelper.getAngle(direction, new Vector2f(0, 1));

                else rotation = -VectorHelper.getAngle(direction, new Vector2f(0, 1));

            }

            position = VectorHelper.sumVectors(new Vector2f[]{position, movedSpace});

        }

    }

    public void render(Graphics graphics) {

        if (!(((position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20 < -40)
                || ((position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20) >= ObjectController.display.size.x
                || ((position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20) < -40
                || ((position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 20) >= ObjectController.display.size.y)) {

            AffineTransform transform = new AffineTransform();
            transform.translate((int) (position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2,
                    (int) (position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2);
            transform.rotate(rotation);
            transform.translate(-20, -20);

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(movementAnimation.getCurrentFrame(), transform, null);

        }

    }

}
