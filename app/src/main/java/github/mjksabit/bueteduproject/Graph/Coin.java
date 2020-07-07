package github.mjksabit.bueteduproject.Graph;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Coin extends ConcreteGraphObject {
    private Bitmap image;
    Point2D location;
    Paint outerPaint, innerPaint;

    public Coin(boolean isMovable, float unit, Bitmap image, Point2D location) {
        super(isMovable, unit);
        this.image = image;
        this.location = location;
        init();
    }

    private void init() {
        outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public Coin(float unit, boolean isMovable, Point2D location, Bitmap image, int innerColor, int outerColor) {
        super(isMovable, unit);
        init();
        this.location = location;
        this.image = image; // null if no skin
        outerPaint.setColor(outerColor);
        innerPaint.setColor(innerColor);
    }

    @Override
    public boolean clicked(Point2D touchLocation) {
        if (!isMovable) return false;

        Point2D topLeft = new Point2D(location.x - 0.5f, location.y - 0.5f);
        Point2D bottomRight = new Point2D(location.x + 0.5f, location.y + 0.5f);

        return Point2D.within(topLeft, touchLocation, bottomRight);
    }

    @Override
    public void move(Point2D from, Point2D to) {
        location.x += to.x - from.x;
        location.y += to.y - from.y;
    }

    @Override
    public void finishMoving() {
        location.x = Math.round(location.x);
        location.y = Math.round(location.y);
    }

    @Override
    public void drawSpecial(Canvas canvas, Point2D origin) {
        Point2D coinRaw = super.rawPoint(location, origin);

        if (image != null)
            canvas.drawBitmap(image, null,
                new RectF(coinRaw.x-0.5f*unit, coinRaw.y-0.5f*unit, coinRaw.x+0.5f*unit, coinRaw.y+0.5f*unit),
                null);
        else {
            canvas.drawCircle(coinRaw.x, coinRaw.y, 0.5f*unit, outerPaint);
            canvas.drawCircle(coinRaw.x, coinRaw.y, 0.4f*unit, innerPaint);
        }
    }
}
