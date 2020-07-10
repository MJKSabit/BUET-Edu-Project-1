package github.mjksabit.bueteduproject.Graph;

import android.graphics.Canvas;

import org.json.JSONException;
import org.json.JSONObject;

public interface GraphObject {

    // Check if this object is Tapped at Relative Touch Location
    boolean clicked(Point2D touchLocation);

    // Move Object from Last Known Location to this Location
    void move(Point2D from, Point2D to);

    // Place Coin to Grid Intersection
    void finishMoving();

    // Draw Object to Canvas
    void drawSpecial(Canvas canvas, Point2D origin);

    // Check if JSON Matches
    public boolean match(JSONObject object) throws JSONException;
}
