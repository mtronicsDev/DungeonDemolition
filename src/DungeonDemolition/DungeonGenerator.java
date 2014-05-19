package DungeonDemolition;

import java.util.ArrayList;
import java.util.Random;

public class DungeonGenerator {

    static Rectangle mapSize = new Rectangle(0, 0, 1024, 1024);
    static Rectangle maxRoomSize = new Rectangle(0, 0, 128, 128);
    static Random random = new Random();

    public static Dungeon generateDungeon() {

        return generateDungeon(new Random().nextLong());

    }

    public static Dungeon generateDungeon(long seed) {

        Random random = new Random(seed);
        ArrayList<Rectangle> rooms = generateRooms(random.nextInt(6) + 1, random.nextInt(4) + 1);
        return new Dungeon(rooms.toArray(new Rectangle[rooms.size()])); //Converting the list to an array with its size

    }

    private static ArrayList<Rectangle> generateRooms(int roomsPerIteration, int iterations) {

        ArrayList<Rectangle> rooms = new ArrayList<Rectangle>();

        for (int i = 0; i < iterations; i++) {
            for (int r = 0; r < roomsPerIteration; r++) {

                //Generates a room within the map boundaries
                rooms.add(new Rectangle(
                        random.nextInt(mapSize.b.x - maxRoomSize.b.x),
                        random.nextInt(mapSize.b.y - maxRoomSize.b.y),
                        random.nextInt(maxRoomSize.b.x),
                        random.nextInt(maxRoomSize.b.y)));

            }

        }

        return rooms;

    }

}
