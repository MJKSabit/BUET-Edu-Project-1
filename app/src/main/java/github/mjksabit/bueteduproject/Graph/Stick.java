package github.mjksabit.bueteduproject.Graph;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Stick extends ConcreteGraphObject {

    Point2D start, end;
    Paint linePaint, topPaint;

    public Stick(boolean isMovable, float unit, Point2D start, Point2D end) {
        super(isMovable, unit);
        this.start = start;
        this.end = end;

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.parseColor("#FFFFB300"));
        linePaint.setStrokeWidth(20);

        topPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        topPaint.setColor(Color.parseColor("#FF9E2A07"));
    }


    Point2D movePoint1, movePoint2;
    @Override
    public boolean clicked(Point2D touchLocation) {
        if (!isMovable) return false;

        if(touchLocation.closeTo(start)) {
            movePoint1 = movePoint2 = start;
            return true;
        }

        if(touchLocation.closeTo(end)) {
            movePoint1 = movePoint2 = end;
            return true;
        }

        if (touchLocation.closeTo(start, end)) {
            movePoint1 = start;
            movePoint2 = end;
            return true;
        }

        return false;
    }

    @Override
    public void move(Point2D from, Point2D to) {
        float dx = to.x - from.x;
        float dy = to.y - from.y;

        movePoint1.x += dx;
        movePoint1.y += dy;

        if (movePoint1 == movePoint2) return;

        movePoint2.x += dx;
        movePoint2.y += dy;
    }

    @Override
    public void finishMoving() {
        movePoint1.x = Math.round(movePoint1.x);
        movePoint1.y = Math.round(movePoint1.y);

        movePoint2.x = Math.round(movePoint2.x);
        movePoint2.y = Math.round(movePoint2.y);
    }

    @Override
    public void drawSpecial(Canvas canvas, Point2D origin) {
        Point2D rawStart = rawPoint(start, origin);
        Point2D rawEnd = rawPoint(end, origin);

        canvas.drawLine(rawStart.x, rawStart.y, rawEnd.x, rawEnd.y, linePaint);
        canvas.drawCircle(rawStart.x, rawStart.y, unit/4, topPaint);
    }
}
