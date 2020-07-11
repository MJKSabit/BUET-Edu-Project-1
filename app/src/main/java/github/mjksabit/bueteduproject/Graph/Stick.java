package github.mjksabit.bueteduproject.Graph;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.json.JSONException;
import org.json.JSONObject;

import github.mjksabit.bueteduproject.Utils.Constant;

public class Stick extends ConcreteGraphObject {

    private Point2D start;
    private Point2D end;
    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint lineCornerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint topPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float lineWidth;
    private boolean useSkin;

    public Stick(float unit, Point2D start, Point2D end, int fillColor, boolean useSkin, boolean isMovable) {
        super(isMovable, unit);
        this.start = start;
        this.end = end;
        this.useSkin = useSkin;

        linePaint.setColor(fillColor);
        lineCornerPaint.setColor(fillColor);

        topPaint.setColor(Color.parseColor(Constant.MATCH_STICK_TOP_COLOR));
    }


    // Determine which Point(s) need to be moved
    private Point2D movePoint1, movePoint2;
    @Override
    public boolean clicked(Point2D touchLocation) {
        // if not movable, can not be clicked
        if (!isMovable) return false;

        // Only Start Point Moved
        if(touchLocation.closeTo(start)) {
            movePoint1 = movePoint2 = start;
            return true;
        }

        // Only End Point Moved
        if(touchLocation.closeTo(end)) {
            movePoint1 = movePoint2 = end;
            return true;
        }

        // Close to Line, Move Both Points
        if (touchLocation.closeTo(start, end)) {
            movePoint1 = start;
            movePoint2 = end;
            return true;
        }

        // No need to move any Point
        return false;
    }

    @Override
    public void move(Point2D from, Point2D to) {
        float dx = to.x - from.x;
        float dy = to.y - from.y;

        movePoint1.x += dx;
        movePoint1.y += dy;

        // Prevent Moving Twice
        if (movePoint1 == movePoint2) return;

        movePoint2.x += dx;
        movePoint2.y += dy;
    }

    @Override
    public void finishMoving() {
        // Points to GridLine Intersections

        movePoint1.x = Math.round(movePoint1.x);
        movePoint1.y = Math.round(movePoint1.y);

        movePoint2.x = Math.round(movePoint2.x);
        movePoint2.y = Math.round(movePoint2.y);
    }

    @Override
    public void drawSpecial(Canvas canvas, Point2D origin) {
        // Stick Line Width is 1/6 of Unit
        lineWidth = unit/5;
        linePaint.setStrokeWidth(lineWidth);

        Point2D rawStart = rawPoint(start, origin);
        Point2D rawEnd = rawPoint(end, origin);

        // Draw Stick Line
        canvas.drawLine(rawStart.x, rawStart.y, rawEnd.x, rawEnd.y, linePaint);

        // Draw Match Stick Top
        if (useSkin){
            canvas.drawCircle(rawStart.x, rawStart.y, unit/4, topPaint);

            // Draw Stick Corner
            canvas.drawCircle(rawEnd.x, rawEnd.y, lineWidth/2, lineCornerPaint);
        }
    }

    @Override
    public boolean match(JSONObject object) throws JSONException {
        // If type mismatch, not matched
        if (object.has("type") && !object.getString("type").equals("matchStick")) return false;

        // Matching Object Locations
        Point2D head = new Point2D(object.getInt("indHeadX"), object.getInt("indHeadY"));
        Point2D tail = new Point2D(object.getInt("indTailX"), object.getInt("indTailY"));
        if (!((start.equals(head) && end.equals(tail)) || (end.equals(head) && start.equals(tail)))) return false;

        // If skin is Used, check skin
        if (object.has("useSkin") && object.getBoolean("useSkin")!=useSkin) return false;

        // Match Stick Color
        if (parseColor(object.getString("fillColor"))!=linePaint.getColor()) return false;

        // Every Match Successful
        return true;
    }
}
