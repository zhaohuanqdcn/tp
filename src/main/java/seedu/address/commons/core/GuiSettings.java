package seedu.address.commons.core;

import java.io.Serializable;
import java.util.Objects;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import seedu.address.commons.core.point.Point;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 500;
    private static final double DEFAULT_WIDTH = 1000;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        double windowWidthTemp;
        double windowHeightTemp;
        Point windowCoordinatesTemp;
        try {
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            windowWidthTemp = bounds.getWidth();
            windowHeightTemp = bounds.getHeight();
            windowCoordinatesTemp = new Point(bounds.getMinX(), bounds.getMinY()); // null represent no coordinates
        } catch (Error e) {
            // if run in test environment, use default settings
            windowWidthTemp = DEFAULT_WIDTH;
            windowHeightTemp = DEFAULT_HEIGHT;
            windowCoordinatesTemp = new Point(300, 100);
        }
        windowHeight = windowHeightTemp;
        windowWidth = windowWidthTemp;
        windowCoordinates = windowCoordinatesTemp;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { //this handles null as well.
            return false;
        }

        GuiSettings o = (GuiSettings) other;

        return windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && Objects.equals(windowCoordinates, o.windowCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates);
        return sb.toString();
    }

}
