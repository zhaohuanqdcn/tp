package seedu.address.commons.core.point;

/**
 * The type Point which stores a 2D double point.
 * Default Java/JavaFX points either don't store double
 * or are not serializable.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point. Default values of (0, 0).
     * Default constructor for serialization to and from JSON.
     */
    public Point() {
        x = 0;
        y = 0;
    }

    /**
     * Instantiates a new Point with the given x and y
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0
                && Double.compare(point.y, y) == 0;
    }
}
