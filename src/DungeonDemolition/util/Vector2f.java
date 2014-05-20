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

    public Vector2f sumWithVector(Vector2f vector) {

        x += vector.x;
        y += vector.y;

        return this;

    }

    public Vector2f subtractWithVector(Vector2f vector) {

        x -= vector.x;
        y -= vector.y;

        return this;

    }

    public Vector2f multiplyByVector(Vector2f vector) {

        x *= vector.x;
        y *= vector.y;

        return this;

    }

    public Vector2f divideByVector(Vector2f vector) {

        x /= vector.x;
        y /= vector.y;

        return this;

    }

    public Vector2f sumWithFloat(float addend) {

        sumWithVector(new Vector2f(addend, addend));

        return this;

    }

    public Vector2f subtractWithFloat(float subtrahend) {

        subtractWithVector(new Vector2f(subtrahend, subtrahend));

        return this;

    }

    public Vector2f multiplyByFloat(float multiplier) {

        multiplyByVector(new Vector2f(multiplier, multiplier));

        return this;

    }

    public Vector2f divideByFloat(float dividend) {

        divideByVector(new Vector2f(dividend, dividend));

        return this;

    }

    public float getScalarProduct(Vector2f vector) {

        return x * vector.x + y * vector.y;

    }

    public boolean equals(Vector2f vector) {

        return x == vector.x && y == vector.y;

    }

    public String toString() {

        return getClass().getName() + "[x=" + x + ",y=" + y + "]";

    }

}
