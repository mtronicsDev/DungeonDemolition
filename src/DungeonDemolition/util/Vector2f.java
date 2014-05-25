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

    public Vector2f(Vector2f source) {

        x = source.x;
        y = source.y;

    }

    public String toString() {

        return "Vector2f(x = " + x + ", y = " + y + ")";

    }

}
