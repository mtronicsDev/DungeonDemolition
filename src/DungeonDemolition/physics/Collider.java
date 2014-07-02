package dungeonDemolition.physics;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.weapons.projectiles.Projectile;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Collider {

    public static boolean areBoxesColliding(Box boxA, Box boxB) {

        return new Rectangle((int) boxA.position.x, (int) boxA.position.y, (int) boxA.size.x, (int) boxA.size.y).intersects(
                new Rectangle((int) boxB.position.x, (int) boxB.position.y, (int) boxB.size.x, (int) boxB.size.y));

    }

    public static Box getBoundingBox(DungeonTile dungeonTile) {

        return new Box(dungeonTile.position, new Vector2f(40, 40));

    }

    public static Box getBoundingBox(Entity entity) {

        return new Box(entity.position, new Vector2f(40, 40));

    }

    public static Box getBoundingBox(Projectile projectile) {

        return new Box(projectile.position, new Vector2f(projectile.texture.getWidth(), projectile.texture.getHeight()));

    }

    public static Object[] getCollisionData(Projectile projectile) {

        boolean colliding = false;
        List<Entity> collidingEntities = new ArrayList<Entity>();

        for (Entity entity : ObjectController.entities.values()) {

            if (!(entity instanceof Player) && entity.level == ObjectController.currentDungeonMap)
                if (areBoxesColliding(new Box(entity.position, new Vector2f(40, 40)),
                        new Box(projectile.position, new Vector2f(projectile.texture.getWidth(), projectile.texture.getHeight())))) {

                    colliding = true;
                    collidingEntities.add(entity);

                }

        }

        if (!colliding)
            for (DungeonTile dungeonTile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
                if (!dungeonTile.passable && areBoxesColliding(getBoundingBox(projectile), getBoundingBox(dungeonTile)))
                    colliding = true;

        return new Object[]{colliding, collidingEntities};

    }

    public static Vector2f getMovedSpace(Entity entity, Vector2f velocity) {

        Vector2f movedSpace = new Vector2f(velocity);

        for (DungeonTile dungeonTile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles) {

            if (!dungeonTile.passable) {

                if (areBoxesColliding(new Box(entity.position, new Vector2f(40, 40)), new Box(dungeonTile.position, new Vector2f(40, 40)))) {

                    Vector2f[] entityBox = new Vector2f[]{
                            entity.position,
                            new Vector2f(entity.position.x + 40, entity.position.y),
                            new Vector2f(entity.position.x, entity.position.y + 40),
                            new Vector2f(entity.position.x + 40, entity.position.y + 40)
                    };

                    Vector2f[] tileBox = new Vector2f[]{
                            dungeonTile.position,
                            new Vector2f(dungeonTile.position.x + 40, dungeonTile.position.y),
                            new Vector2f(dungeonTile.position.x, dungeonTile.position.y + 40),
                            new Vector2f(dungeonTile.position.x + 40, dungeonTile.position.y + 40)
                    };

                    float[] differences = new float[]{
                            VectorHelper.getLength(VectorHelper.subtractVectors(tileBox[0], entityBox[2])) + VectorHelper.getLength(VectorHelper.subtractVectors(tileBox[1], entityBox[3])),
                            VectorHelper.getLength(VectorHelper.subtractVectors(tileBox[0], entityBox[1])) + VectorHelper.getLength(VectorHelper.subtractVectors(tileBox[2], entityBox[3])),
                            VectorHelper.getLength(VectorHelper.subtractVectors(tileBox[1], entityBox[0])) + VectorHelper.getLength(VectorHelper.subtractVectors(tileBox[3], entityBox[2])),
                            VectorHelper.getLength(VectorHelper.subtractVectors(tileBox[2], entityBox[0])) + VectorHelper.getLength(VectorHelper.subtractVectors(tileBox[3], entityBox[1])),
                    };

                    float smallestDifference = differences[0];
                    int smallestDifferenceIndex = 0;

                    for (int count = 0; count < 4; count++)
                        if (differences[count] < smallestDifference) {

                            smallestDifference = differences[count];
                            smallestDifferenceIndex = count;

                        }

                    Vector2f idealNormal;

                    switch (smallestDifferenceIndex) {

                        case 0:
                            idealNormal = new Vector2f(0, -1);
                            break;

                        case 1:
                            idealNormal = new Vector2f(-1, 0);
                            break;

                        case 2:
                            idealNormal = new Vector2f(1, 0);
                            break;

                        case 3:
                            idealNormal = new Vector2f(0, 1);
                            break;

                        default:
                            idealNormal = new Vector2f();

                    }

                    boolean ableToRespond = false;

                    for (Vector2f normal : dungeonTile.normals)
                        if (VectorHelper.getScalarProduct(normal, movedSpace) < 0 && VectorHelper.areEqual(normal, idealNormal))
                            ableToRespond = true;

                    if (ableToRespond) {

                        if (idealNormal.y == 1 || idealNormal.y == -1)
                            movedSpace.y = 0;

                        else if (idealNormal.x == 1 || idealNormal.x == -1)
                            movedSpace.x = 0;

                    }

                }

            }

            if (VectorHelper.areEqual(movedSpace, new Vector2f())) break;

        }

        if (!VectorHelper.areEqual(movedSpace, new Vector2f())) {

            for (Entity collisionEntity : ObjectController.entities.values()) {

                if (collisionEntity != entity && entity.level == ObjectController.currentDungeonMap) {

                    if (areBoxesColliding(new Box(entity.position, new Vector2f(40, 40)), new Box(collisionEntity.position, new Vector2f(40, 40)))) {

                        Vector2f[] entityBox = new Vector2f[]{
                                entity.position,
                                new Vector2f(entity.position.x + 40, entity.position.y),
                                new Vector2f(entity.position.x, entity.position.y + 40),
                                new Vector2f(entity.position.x + 40, entity.position.y + 40)
                        };

                        Vector2f[] collisionEntityBox = new Vector2f[]{
                                collisionEntity.position,
                                new Vector2f(collisionEntity.position.x + 40, collisionEntity.position.y),
                                new Vector2f(collisionEntity.position.x, collisionEntity.position.y + 40),
                                new Vector2f(collisionEntity.position.x + 40, collisionEntity.position.y + 40)
                        };

                        float[] differences = new float[]{
                                VectorHelper.getLength(VectorHelper.subtractVectors(collisionEntityBox[0], entityBox[2]))
                                        + VectorHelper.getLength(VectorHelper.subtractVectors(collisionEntityBox[1], entityBox[3])),
                                VectorHelper.getLength(VectorHelper.subtractVectors(collisionEntityBox[0], entityBox[1]))
                                        + VectorHelper.getLength(VectorHelper.subtractVectors(collisionEntityBox[2], entityBox[3])),
                                VectorHelper.getLength(VectorHelper.subtractVectors(collisionEntityBox[1], entityBox[0]))
                                        + VectorHelper.getLength(VectorHelper.subtractVectors(collisionEntityBox[3], entityBox[2])),
                                VectorHelper.getLength(VectorHelper.subtractVectors(collisionEntityBox[2], entityBox[0]))
                                        + VectorHelper.getLength(VectorHelper.subtractVectors(collisionEntityBox[3], entityBox[1])),
                        };

                        float smallestDifference = differences[0];
                        int smallestDifferenceIndex = 0;

                        for (int count = 0; count < 4; count++)
                            if (differences[count] < smallestDifference) {

                                smallestDifference = differences[count];
                                smallestDifferenceIndex = count;

                            }

                        Vector2f idealNormal;

                        switch (smallestDifferenceIndex) {

                            case 0:
                                idealNormal = new Vector2f(0, -1);
                                break;

                            case 1:
                                idealNormal = new Vector2f(-1, 0);
                                break;

                            case 2:
                                idealNormal = new Vector2f(1, 0);
                                break;

                            case 3:
                                idealNormal = new Vector2f(0, 1);
                                break;

                            default:
                                idealNormal = new Vector2f();

                        }

                        boolean ableToRespond = false;

                        Vector2f[] normals = new Vector2f[]{
                                new Vector2f(0, -1),
                                new Vector2f(-1, 0),
                                new Vector2f(1, 0),
                                new Vector2f(0, 1),
                        };

                        for (Vector2f normal : normals)
                            if (VectorHelper.getScalarProduct(normal, movedSpace) < 0 && VectorHelper.areEqual(normal, idealNormal))
                                ableToRespond = true;

                        if (ableToRespond) {

                            if (idealNormal.y == 1 || idealNormal.y == -1)
                                movedSpace.y = 0;

                            else if (idealNormal.x == 1 || idealNormal.x == -1)
                                movedSpace.x = 0;

                        }

                    }

                }

            }

        }

        return movedSpace;

    }

}
