package dungeonDemolition.util;

public class Matrix2f {

    public Vector2f firstLine;
    public Vector2f secondLine;

    public Matrix2f(Vector2f firstLine, Vector2f secondLine) {

        this.firstLine = firstLine;
        this.secondLine = secondLine;

    }

    public Vector2f multiplyByVector(Vector2f vector) {

        Vector2f result = new Vector2f();

        result.x = vector.x * firstLine.x + vector.y * firstLine.y;
        result.y = vector.x * secondLine.x + vector.y * secondLine.y;

        return result;

    }

}
