package github.mjksabit.bueteduproject.Graph;

import android.graphics.Color;

public abstract class ConcreteGraphObject implements GraphObject{
    protected boolean isMovable;
    protected float unit;

    protected Point2D rawPoint(Point2D gridPoint, Point2D originLocation) {
        float x = gridPoint.x*unit + originLocation.x;
        float y = gridPoint.y*unit + originLocation.y;

        return new Point2D(x, y);
    }

    public ConcreteGraphObject(boolean isMovable, float unit) {
        this.isMovable = isMovable;
        this.unit = unit;
    }

    public void setUnit(float unit) {
        this.unit = unit;
    }

    protected int parseColor(String color) {
        return Color.parseColor("#"+color.substring(2, 8));
    }


}
