package DungeonDemolition.util;

import java.util.Random;

public class Randomizer {

    private static Random random = new Random();

    public static int getRandomInt(int min, int max) {

        return random.nextInt(max + 1 - min) + min;

    }

}
