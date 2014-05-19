package DungeonDemolition;

public class TimeHelper {

    public static float deltaTime;
    private static long thisTime;
    private static long lastTime;

    public static void initialize() {

        thisTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();
        deltaTime = 0;

    }

    public static void update() {

        thisTime = System.currentTimeMillis();
        deltaTime = (thisTime - lastTime) / 1000f;
        lastTime = thisTime;

    }

}
