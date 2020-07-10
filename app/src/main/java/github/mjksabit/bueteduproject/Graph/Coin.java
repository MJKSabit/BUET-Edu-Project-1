package github.mjksabit.bueteduproject.Graph;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import org.json.JSONException;
import org.json.JSONObject;

public class Coin extends ConcreteGraphObject {

    // Coin Image
    private Bitmap image;

    // Coin Circle Center
    private Point2D location;

    // Coin Circle Colors in Case of No Skin is used
    private Paint outerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint innerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // Only Used For Matching
    private int coinSkin;

    public Coin(float unit, boolean isMovable, Point2D location, Bitmap image, int innerColor, int outerColor, int coinSkin) {
        super(isMovable, unit);
        this.location = location;
        this.image = image; // null if no skin
        outerCirclePaint.setColor(outerColor);
        innerCirclePaint.setColor(innerColor);
        this.coinSkin = coinSkin;
    }

    @Override
    public boolean clicked(Point2D touchLocation) {
        // if not movable, can not be clicked
        if (!isMovable) return false;

        // Search Area from location to 0.5 unit left-right-top-bottom
        Point2D topLeft = new Point2D(location.x - 0.5f, location.y - 0.5f);
        Point2D bottomRight = new Point2D(location.x + 0.5f, location.y + 0.5f);

        // Check if touchLocation is within the Search Area
        return Point2D.within(topLeft, touchLocation, bottomRight);
    }

    // Move the coin
    @Override
    public void move(Point2D from, Point2D to) {
        location.x += to.x - from.x;
        location.y += to.y - from.y;
    }

    // Coin will Stabilize in Grid Line Intersections
    @Override
    public void finishMoving() {
        location.x = Math.round(location.x);
        location.y = Math.round(location.y);
    }

    // Draw the Coin
    @Override
    public void drawSpecial(Canvas canvas, Point2D origin) {

        // Get Relative Grid Point to Screen Pixel Point
        Point2D coinRaw = super.rawPoint(location, origin);

        // If Skin is Used
        if (image != null)
            canvas.drawBitmap(image, null,
                new RectF(coinRaw.x-0.5f*unit, coinRaw.y-0.5f*unit, coinRaw.x+0.5f*unit, coinRaw.y+0.5f*unit),
                null);
        else {
            // If no Skin Image is used, Draw Filled Circle

            // Outer Circle Radius, 0.5 unit
            canvas.drawCircle(coinRaw.x, coinRaw.y, 0.5f*unit, outerCirclePaint);

            // Inner Circle Radius, 0.4 unit
            canvas.drawCircle(coinRaw.x, coinRaw.y, 0.4f*unit, innerCirclePaint);
        }
    }

    @Override
    public boolean match(JSONObject object) throws JSONException {
        // If type mismatch, not matched
        if (object.has("type") && !object.getString("type").equals("coin")) return false;

        // Matching Object Locations
        if (object.has("indX") && object.getInt("indX")!=location.x) return false;
        if (object.has("indY") && object.getInt("indY")!=location.y) return false;

        // If skin is Used
        if (object.has("useSkin") && object.getBoolean("useSkin")){
            // and the skin is not same as this one, not matched
            if (object.getInt("skin")!=coinSkin) return false;
        } else {
            // No skin is used, So matching Colors
            if (parseColor(object.getString("innerColor"))!= innerCirclePaint.getColor() || parseColor(object.getString("outerColor"))!= outerCirclePaint.getColor())
                return false;
        }

        return true;
    }
}
