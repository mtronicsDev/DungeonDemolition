package dungeonDemolition.util;

public class TimeHelper {

    public static float deltaTime;
    private static long thisTime;
    private static long lastTime;

    public static void initialize() {

        lastTime = System.currentTimeMillis();
        deltaTime = 0;

    }

    public static void update() {

        thisTime = System.currentTimeMillis();
        deltaTime = (thisTime - lastTime) / 1000f;
        lastTime = thisTime;

        try {

            Thread.sleep(16);

        } catch (InterruptedException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }

}
