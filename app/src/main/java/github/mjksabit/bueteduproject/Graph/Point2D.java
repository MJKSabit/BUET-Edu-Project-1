package github.mjksabit.bueteduproject.Graph;

import androidx.annotation.NonNull;

public class Point2D {
    private static float DELTA = 0.5f;

    public float x, y;

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private static boolean within(float lo, float value, float hi) {
        return (lo<=value) && (value<=hi);
    }

    public static boolean within(Point2D topLeft, Point2D value, Point2D bottomRight) {
        return within(topLeft.x, value.x, bottomRight.x) &&
                within(topLeft.y, value.y, bottomRight.y);
    }

    public boolean closeTo(Point2D other) {
        float delX = x - other.x;
        float delY = y - other.y;

        return (delX*delX + delY*delY) <= DELTA;
    }

    public boolean closeTo(Point2D linePoint1, Point2D linePoint2) {
        float area = (x*linePoint1.y + linePoint1.x*linePoint2.y + linePoint2.x*y)
                - (y*linePoint1.x+linePoint1.y*linePoint2.x+linePoint2.y*x);
        return Math.abs(area) <= DELTA;
    }

    @NonNull
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }
}
