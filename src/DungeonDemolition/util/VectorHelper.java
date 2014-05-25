package dungeonDemolition.util;

public class VectorHelper {

    public static float getAngle(Vector2f vectorA, Vector2f vectorB) {

        return (float) Math.acos(getScalarProduct(normalizeVector(vectorA), normalizeVector(vectorB)));

    }

    public static Vector2f normalizeVector(Vector2f vector) {

        return divideVectorByFloat(vector, getLength(vector));

    }

    public static float getLength(Vector2f vector) {

        return (float) Math.sqrt(getScalarProduct(vector, vector));

    }

    public static float getScalarProduct(Vector2f vectorA, Vector2f vectorB) {

        return vectorA.x * vectorB.x + vectorA.y * vectorB.y;

    }

    public static Vector2f sumVectors(Vector2f[] vectors) {

        Vector2f sum = new Vector2f();

        for (Vector2f vector : vectors) {

            sum.x += vector.x;
            sum.y += vector.y;

        }

        return sum;

    }

    public static Vector2i sumVectors(Vector2i[] vectors) {

        Vector2i sum = new Vector2i();

        for (Vector2i vector : vectors) {

            sum.x += vector.x;
            sum.y += vector.y;

        }

        return sum;

    }

    public static Vector2f subtractVectors(Vector2f vectorA, Vector2f vectorB) {

        Vector2f difference = new Vector2f();

        difference.x = vectorA.x - vectorB.x;
        difference.y = vectorA.y - vectorB.y;

        return difference;

    }

    public static Vector2i subtractVectors(Vector2i vectorA, Vector2i vectorB) {

        Vector2i difference = new Vector2i();

        difference.x = vectorA.x - vectorB.x;
        difference.y = vectorA.y - vectorB.y;

        return difference;

    }

    public static Vector2f multiplyVectors(Vector2f[] vectors) {

        Vector2f product = new Vector2f(1, 1);

        for (Vector2f vector : vectors) {

            product.x *= vector.x;
            product.y *= vector.y;

        }

        return product;

    }

    public static Vector2i multiplyVectors(Vector2i[] vectors) {

        Vector2i product = new Vector2i();

        for (Vector2i vector : vectors) {

            product.x *= vector.x;
            product.y *= vector.y;

        }

        return product;

    }

    public static Vector2f divideVectors(Vector2f vectorA, Vector2f vectorB) {

        Vector2f quotient = new Vector2f();

        quotient.x = vectorA.x / vectorB.x;
        quotient.y = vectorA.y / vectorB.y;

        return quotient;

    }

    public static Vector2i divideVectors(Vector2i vectorA, Vector2i vectorB) {

        Vector2i quotient = new Vector2i();

        quotient.x = vectorA.x / vectorB.x;
        quotient.y = vectorA.y / vectorB.y;

        return quotient;

    }

    public static Vector2f multiplyVectorByFloat(Vector2f vector, float multiplier) {

        return multiplyVectors(new Vector2f[] {vector, new Vector2f(multiplier, multiplier)});

    }

    public static Vector2f divideVectorByFloat(Vector2f vector, float dividend) {

        return divideVectors(vector, new Vector2f(dividend, dividend));

    }

    public static boolean areEqual(Vector2f vectorA, Vector2f vectorB) {

        return vectorA.x == vectorB.x && vectorA.y == vectorB.y;

    }

    public static boolean areEqual(Vector2i vectorA, Vector2i vectorB) {

        return vectorA.x == vectorB.x && vectorA.y == vectorB.y;

    }

}
