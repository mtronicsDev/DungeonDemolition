package dungeonDemolition.util;

public class MathHelper {

    public static int clamp(int in, int min, int max) {

        if (in < min) in = min;
        else if (in > max) in = max;

        return in;

    }

}
