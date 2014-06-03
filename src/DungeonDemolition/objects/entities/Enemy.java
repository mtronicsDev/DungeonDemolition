package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Enemy extends Entity {

    public int damage;
    public Timer damageBreakTimer;
    private Vector2f playerLessMovement;

    public Enemy(String movementAnimationName, float speed, int damage, float damageBreakTime) {

        super(movementAnimationName, speed);

        this.damage = damage;
        this.damageBreakTimer = new Timer(damageBreakTime);

        maxHealth = 80;
        health = 80;

    }

    public Enemy(String movementAnimationName, String deathAnimationName, float speed, int damage, float damageBreakTime) {

        super(movementAnimationName, deathAnimationName, speed);

        this.damage = damage;
        this.damageBreakTimer = new Timer(damageBreakTime);

        maxHealth = 80;
        health = 80;

    }

    public void update() {

        super.update();

        Vector2f differenceToPlayer = VectorHelper.subtractVectors(position, ObjectController.entities.get("player").position);

        if (health > 0
                && Math.abs(differenceToPlayer.x) <= ObjectController.display.size.x / 2 + 100
                && Math.abs(differenceToPlayer.y) <= ObjectController.display.size.y / 2 + 100) {

            float speed = super.speed * TimeHelper.deltaTime;

            Vector2f movementDirection;

            if (ObjectController.entities.get("player").health > 0)
                movementDirection = VectorHelper.normalizeVector(VectorHelper.subtractVectors(ObjectController.entities.get("player").position, position));

            else {

                if (playerLessMovement == null) playerLessMovement = new Vector2f((float) Randomizer.getRandomInt(-1000, 1000) / 1000f, (float) Randomizer.getRandomInt(-1000, 1000) / 1000f);

                movementDirection = playerLessMovement;

            }

            boolean blocked = false;
            List<Vector2f> blockerPositions = new ArrayList<Vector2f>();

            Vector2f idealMovedSpace;

            idealMovedSpace = VectorHelper.multiplyVectorByFloat(movementDirection, speed);

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
                            blockerPositions.add(dungeonTile.position);

                        }

                    }

                }

            }

            if (!blocked)
                for (Entity entity : ObjectController.entities.values()) {

                    if (entity instanceof Enemy) {

                        Enemy enemy = (Enemy) entity;

                        if (enemy != this) {

                            Vector2f middleCollidingEnemyPosition = VectorHelper.sumVectors(new Vector2f[] {enemy.position, new Vector2f(20, 20)});
                            Vector2f middleEnemyPosition = VectorHelper.sumVectors(new Vector2f[] {position, new Vector2f(20, 20)});

                            Vector2f target = VectorHelper.sumVectors(new Vector2f[] {VectorHelper.sumVectors(new Vector2f[] {position, new Vector2f(20, 20)}), idealMovedSpace});

                            Vector2f difference = VectorHelper.subtractVectors(middleCollidingEnemyPosition, middleEnemyPosition);

                            if (VectorHelper.getLength(VectorHelper.subtractVectors(target, middleCollidingEnemyPosition)) <= 88
                                    && VectorHelper.getAngle(idealMovedSpace, difference) <= Math.toRadians(30)) {

                                blocked = true;
                                blockerPositions.add(enemy.position);

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

            if (!VectorHelper.areEqual(movedSpace, new Vector2f())) {

                Vector2f direction = VectorHelper.normalizeVector(VectorHelper.negateVector(movedSpace));

                if (VectorHelper.getScalarProduct(direction, new Vector2f(1, 0)) <= 0)
                    rotation = VectorHelper.getAngle(direction, new Vector2f(0, 1));

                else rotation = -VectorHelper.getAngle(direction, new Vector2f(0, 1));

                movedSpace = Collider.getMovedSpace(this, movedSpace);

            }

            movementAnimation.update(!VectorHelper.areEqual(movedSpace, new Vector2f()));

            position = VectorHelper.sumVectors(new Vector2f[]{position, movedSpace});

            damageBreakTimer.update();

            if (damageBreakTimer.hasFinished())
                if (Collider.areBoxesColliding(Collider.getBoundingBox(this), Collider.getBoundingBox(ObjectController.entities.get("player")))) {

                    ObjectController.entities.get("player").health -= damage;
                    damageBreakTimer.restart();

                }

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
            if (health > 0) graphics2D.drawImage(movementAnimation.getCurrentFrame(), transform, null);
            else if (deathAnimation != null) graphics2D.drawImage(deathAnimation.getCurrentFrame(), transform, null);


        }

    }

}
