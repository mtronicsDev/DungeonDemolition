package DungeonDemolition;

import DungeonDemolition.Point;

public class Rectangle {

    public Point a;
    public Point b;

    public Rectangle(Point a, Point b) {

        this.a = a;
        this.b = b;

    }

    public Rectangle(int xA, int yA, int xB, int yB) {

        this(new Point(xA, yA), new Point(xB, yB));

    }

}
