package dungeonDemolition.objects.dungeons;

import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.Vector2i;

public class Room {

    private static Vector2i minSize = new Vector2i(5, 5);
    private static Vector2i maxSize = new Vector2i(26, 26);

    public void generate() {

        generate(null, null, null);

    }

    public void generate(Vector2i position, Vector2i size, Direction direction) {

        boolean isSpawn = false;
        if (position == null || size == null || direction == null) {
            size = new Vector2i(
                    Randomizer.getRandomInt(minSize.x, maxSize.x),
                    Randomizer.getRandomInt(minSize.y, maxSize.y)
            );

            position = new Vector2i(
                    Randomizer.getRandomInt(0, DungeonGenerator.map.length - 1 - size.x),
                    Randomizer.getRandomInt(0, DungeonGenerator.map[0].length - 1 - size.y)
            );

            direction = Direction.values()[Randomizer.getRandomInt(0, 3)];
            isSpawn = true;
        }

        byte[][] localMap = new byte[size.x][size.y];

        for (int x = 0; x < localMap.length; x++) {
            for (int y = 0; y < localMap[0].length; y++) {

                //Corners
                if (x == 0 && y == 0) localMap[x][y] = 6;
                else if (x == localMap.length - 1 && y == 0) localMap[x][y] = 7;
                else if (x == localMap.length - 1 && y == localMap[0].length - 1) localMap[x][y] = 8;
                else if (x == 0 && y == localMap[0].length - 1) localMap[x][y] = 9;

                    //Walls
                else if (y == 0) localMap[x][y] = 2;
                else if (x == localMap.length - 1) localMap[x][y] = 3;
                else if (y == localMap[0].length - 1) localMap[x][y] = 4;
                else if (x == 0) localMap[x][y] = 5;

                    //Floor
                else localMap[x][y] = 1;

            }
        }

        //Loot & stuff
        int iterations = Randomizer.getRandomInt(size.x * size.y / 200, size.x * size.y / 75);
        for (int i = 0; i < iterations; i++) {
            Vector2i featurePoint = new Vector2i(
                    Randomizer.getRandomInt(1, localMap.length - 2),
                    Randomizer.getRandomInt(1, localMap[0].length - 2)
            );

            localMap[featurePoint.x][featurePoint.y] = 12;
        }

        //Monster spawners
        int spawners = Randomizer.getRandomInt(size.x * size.y / 200, size.x * size.y / 75);
        for (int i = 0; i < iterations; i++) {
            Vector2i featurePoint = new Vector2i(
                    Randomizer.getRandomInt(1, localMap.length - 2),
                    Randomizer.getRandomInt(1, localMap[0].length - 2)
            );

            localMap[featurePoint.x][featurePoint.y] = 11;
        }

        //Generate spawn point if necessary
        if (isSpawn) {

            Vector2i spawnPoint = new Vector2i(
                    Randomizer.getRandomInt(1, localMap.length - 2),
                    Randomizer.getRandomInt(1, localMap[0].length - 2)
            );

            localMap[spawnPoint.x][spawnPoint.y] = 10;

        }

        //Generate a new room
        Direction nextDirection = Direction.values()[Randomizer.getRandomInt(0, 3)];
        Vector2i nextSize = new Vector2i(
                Randomizer.getRandomInt(minSize.x, maxSize.x),
                Randomizer.getRandomInt(minSize.y, maxSize.y)
        );
        boolean isDirectionAvailable;

        switch (nextDirection) {

            case NOTRH:
                int offset = Randomizer.getRandomInt(1, size.x - 2);


        }

        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                DungeonGenerator.map[position.x + x][position.y + y] = localMap[x][y];
            }
        }

    }

}
