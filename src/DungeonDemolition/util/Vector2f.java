package dungeonDemolition.util;

public class Vector2f {

    public float x;
    public float y;

    public Vector2f(float x, float y) {

        this.x = x;
        this.y = y;

    }

    public Vector2f() {

        this(0, 0);

    }

    public String toString() {

        return getClass().getName() + "[x=" + x + ",y=" + y + "]";

    }

}
