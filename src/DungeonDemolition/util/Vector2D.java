package dungeonDemolition.util;

public class Vector2D {

    public float x;
    public float y;

    public Vector2D(float x, float y) {

        this.x = x;
        this.y = y;

    }

    public Vector2D sumWithVector(Vector2D vector) {

        x += vector.x;
        y += vector.y;

        return this;

    }

    public Vector2D subtractWithVector(Vector2D vector) {

        x -= vector.x;
        y -= vector.y;

        return this;

    }

    public Vector2D multiplyByVector(Vector2D vector) {

        x *= vector.x;
        y *= vector.y;

        return this;

    }

    public Vector2D divideByVector(Vector2D vector) {

        x /= vector.x;
        y /= vector.y;

        return this;

    }

    public Vector2D sumWithFloat(float addend) {

        sumWithVector(new Vector2D(addend, addend));

        return this;

    }

    public Vector2D subtractWithFloat(float subtrahend) {

        subtractWithVector(new Vector2D(subtrahend, subtrahend));

        return this;

    }

    public Vector2D multiplyByFloat(float multiplier) {

        multiplyByVector(new Vector2D(multiplier, multiplier));

        return this;

    }

    public Vector2D divideByFloat(float dividend) {

        divideByVector(new Vector2D(dividend, dividend));

        return this;

    }

    public float getScalarProduct(Vector2D vector) {

        return x * vector.x + y * vector.y;

    }

    public boolean equals(Vector2D vector) {

        return x == vector.x && y == vector.y;

    }

    public String toString() {

        return getClass().getName() + "[x=" + x + ",y=" + y + "]";

    }

}
