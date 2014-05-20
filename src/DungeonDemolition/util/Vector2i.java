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

    public Vector2i sumWithVector(Vector2i vector) {

        x += vector.x;
        y += vector.y;

        return this;

    }

    public Vector2i subtractWithVector(Vector2i vector) {

        x -= vector.x;
        y -= vector.y;

        return this;

    }

    public Vector2i multiplyByVector(Vector2i vector) {

        x *= vector.x;
        y *= vector.y;

        return this;

    }

    public Vector2i divideByVector(Vector2i vector) {

        x /= vector.x;
        y /= vector.y;

        return this;

    }

    public Vector2i sumWithInt(int addend) {

        sumWithVector(new Vector2i(addend, addend));

        return this;

    }

    public Vector2i subtractWithInt(int subtrahend) {

        subtractWithVector(new Vector2i(subtrahend, subtrahend));

        return this;

    }

    public Vector2i multiplyByInt(int multiplier) {

        multiplyByVector(new Vector2i(multiplier, multiplier));

        return this;

    }

    public Vector2i divideByInt(int dividend) {

        divideByVector(new Vector2i(dividend, dividend));

        return this;

    }

    public int getScalarProduct(Vector2i vector) {

        return x * vector.x + y * vector.y;

    }

    public boolean equals(Vector2i vector) {

        return x == vector.x && y == vector.y;

    }

    public String toString() {

        return getClass().getName() + "[x=" + x + ",y=" + y + "]";

    }

}
