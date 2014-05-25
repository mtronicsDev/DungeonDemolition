package dungeonDemolition.util;

public class Vector2i {

    public int x;
    public int y;

    public Vector2i(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public Vector2i() {

        this(0, 0);

    }

    public Vector2i(Vector2i source) {

        x = source.x;
        y = source.y;

    }

    public String toString() {

        return "Vector2i(x = " + x + ", y = " + y + ")";

    }

}
