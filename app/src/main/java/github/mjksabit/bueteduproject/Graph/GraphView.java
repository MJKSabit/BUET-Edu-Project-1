package github.mjksabit.bueteduproject.Graph;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import github.mjksabit.bueteduproject.R;

public class GraphView extends View implements GraphObject {

    private static final int NUMBER_OF_GRID = 50;
    public static final String TAG = GraphView.class.getSimpleName();

    ArrayList<Coin> coins = new ArrayList<>();
    ArrayList<Stick> sticks = new ArrayList<>();

    private static final Map<Integer, Bitmap> coinImages = new HashMap<>();

    // In Raw Point == (0,0) @ Grid Point
    private Point2D originLocation;

    private float unit;

    private Paint gridPaint;
    private boolean gridOff = false;

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphView(Context context) {
        super(context);
        init();
    }

    private void init() {
        originLocation = new Point2D(0, 0);
        this.unit = getContext().getResources().getDisplayMetrics().widthPixels / 10.0f;

        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setColor(0x99FF0000);

        coinImages.put(14, BitmapFactory.decodeResource(getResources(), R.drawable.a));
        coinImages.put(15, BitmapFactory.decodeResource(getResources(), R.drawable.b));
        coinImages.put(16, BitmapFactory.decodeResource(getResources(), R.drawable.c));
        coinImages.put(17, BitmapFactory.decodeResource(getResources(), R.drawable.d));
        coinImages.put(18, BitmapFactory.decodeResource(getResources(), R.drawable.e));
        coinImages.put(19, BitmapFactory.decodeResource(getResources(), R.drawable.f));
        coinImages.put(20, BitmapFactory.decodeResource(getResources(), R.drawable.g));
        coinImages.put(21, BitmapFactory.decodeResource(getResources(), R.drawable.h));
        coinImages.put(22, BitmapFactory.decodeResource(getResources(), R.drawable.i));
        coinImages.put(23, BitmapFactory.decodeResource(getResources(), R.drawable.j));

        coinImages.put(24, BitmapFactory.decodeResource(getResources(), R.drawable.k));
        coinImages.put(25, BitmapFactory.decodeResource(getResources(), R.drawable.l));
        coinImages.put(26, BitmapFactory.decodeResource(getResources(), R.drawable.m));
        coinImages.put(27, BitmapFactory.decodeResource(getResources(), R.drawable.n));
        coinImages.put(28, BitmapFactory.decodeResource(getResources(), R.drawable.o));
        coinImages.put(29, BitmapFactory.decodeResource(getResources(), R.drawable.p));
        coinImages.put(30, BitmapFactory.decodeResource(getResources(), R.drawable.q));
        coinImages.put(31, BitmapFactory.decodeResource(getResources(), R.drawable.r));
        coinImages.put(32, BitmapFactory.decodeResource(getResources(), R.drawable.s));
        coinImages.put(33, BitmapFactory.decodeResource(getResources(), R.drawable.t));

        coinImages.put(34, BitmapFactory.decodeResource(getResources(), R.drawable.u));
        coinImages.put(35, BitmapFactory.decodeResource(getResources(), R.drawable.v));
        coinImages.put(36, BitmapFactory.decodeResource(getResources(), R.drawable.w));
        coinImages.put(37, BitmapFactory.decodeResource(getResources(), R.drawable.x));
        coinImages.put(38, BitmapFactory.decodeResource(getResources(), R.drawable.y));
        coinImages.put(39, BitmapFactory.decodeResource(getResources(), R.drawable.z));

        coinImages.put(0, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_1));
        coinImages.put(1, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_2));
        coinImages.put(2, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_3));
        coinImages.put(3, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_4));
        coinImages.put(4, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_5));
        coinImages.put(5, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_6));
        coinImages.put(6, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_7));
        coinImages.put(7, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_8));
        coinImages.put(8, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_9));
        coinImages.put(9, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_10));
        coinImages.put(10, BitmapFactory.decodeResource(getResources(), R.drawable.coin_skin_11));

//        coinImages.put("text", BitmapFactory.decodeResource(getResources(), R.drawable.text));
//        coinImages.put("fire", BitmapFactory.decodeResource(getResources(), R.drawable.fire));
//        coinImages.put("fish", BitmapFactory.decodeResource(getResources(), R.drawable.fish));
//        coinImages.put("bird", BitmapFactory.decodeResource(getResources(), R.drawable.bird));
    }

    public void setBoardContent(JSONObject problemSchema) throws JSONException {
        String backgroundColor = problemSchema.getString("bgColor");
        String lineColor = problemSchema.getString("lineColor");
        double lineOpacity = problemSchema.getDouble("lineOpacity");

        gridPaint.setColor(parseColor(lineColor));
        gridPaint.setAlpha((int)(lineOpacity*255));

        JSONArray elements = problemSchema.getJSONArray("elements");

        Point2D topLeft = new Point2D(50, 50);
        Point2D bottomRight = new Point2D(0, 0);

        for (int i=0; i<elements.length(); i++) {
            JSONObject element = elements.getJSONObject(i);

            if (element.getString("type").equals("matchStick")) {
                Point2D start = new Point2D(element.getInt("indHeadX"), element.getInt("indHeadY"));
                Point2D end = new Point2D(element.getInt("indTailX"), element.getInt("indTailY"));
                
                if (topLeft.x > start.x) topLeft.x = start.x;
                if (topLeft.x > end.x) topLeft.x = end.x;

                if (topLeft.y > start.y) topLeft.y = start.y;
                if (topLeft.y > end.y) topLeft.y = end.y;


                String fillColor = element.getString("fillColor");
                boolean useSkin = element.has("useSkin") && element.getBoolean("useSkin");
                boolean isMust = element.has("isMust") && element.getBoolean("isMust");

                sticks.add(new Stick(unit, start, end, parseColor(fillColor), useSkin, isMust));
            } else if (element.getString("type").equals("coin")) {
                Point2D position = new Point2D(element.getInt("indX"), element.getInt("indY"));

                if (topLeft.x > position.x) topLeft.x = position.x;
                if (topLeft.y > position.y) topLeft.y = position.y;

                String innerColor = element.getString("innerColor");
                String outerColor = element.getString("outerColor");

                boolean cantMove = element.has("cantMove") && element.getBoolean("cantMove");

                int skin = -1;
                if (element.getBoolean("useSkin"))
                    skin = element.getInt("skin");

                Bitmap image = coinImages.containsKey(skin) ?
                        coinImages.get(skin) : null;
                coins.add(new Coin(unit, !cantMove, position, image, parseColor(innerColor), parseColor(outerColor)));
            }
        }
        originLocation = rawPoint(new Point2D(-topLeft.x+1, -topLeft.y+1));

        invalidate();
    }

    private int parseColor(String color) {
        return Color.parseColor("#"+color.substring(2, 8));
    }

    public void setGridOff(boolean gridOff) {
        this.gridOff = gridOff;
    }

    private Point2D gridPoint(Point2D rawPoint) {
        float x = rawPoint.x - originLocation.x;
        float y = rawPoint.y - originLocation.y;

        return new Point2D(x/unit, y/unit);
    }

    private Point2D rawPoint(Point2D gridPoint) {
        float x = gridPoint.x*unit + originLocation.x;
        float y = gridPoint.y*unit + originLocation.y;

        return new Point2D(x, y);
    }

    private Point2D rawDelFromGrid(Point2D from, Point2D to) {
        float delX = (to.x - from.x)*unit;
        float delY = (to.y - from.y)*unit;

        return new Point2D(delX, delY);
    }

    public void addCoin(float x, float y) {
        int[] myLoc = new int[2];
        getLocationOnScreen(myLoc);

        lastLocationRaw = new Point2D( x - myLoc[0], y-myLoc[1]);
        Point2D point = gridPoint(lastLocationRaw);

        currentObject = new Coin(true, unit, coinImages.get("1"), point);
        coins.add((Coin) currentObject);
        invalidate();
//        Toast.makeText(getContext(), point.toString(), Toast.LENGTH_SHORT).show();
    }

    public void addCoin(Point2D gridPoint, String imageName, boolean isMoveable) {
        coins.add(new Coin(isMoveable, unit, coinImages.get(imageName), gridPoint));
    }

    public void addStick(float x, float y) {
        int[] myLoc = new int[2];
        getLocationOnScreen(myLoc);

        lastLocationRaw = new Point2D( x - myLoc[0], y-myLoc[1]);
        Point2D getGridPoint = gridPoint(lastLocationRaw);

        Point2D start = new Point2D( getGridPoint.x, getGridPoint.y-1);
        Point2D end = new Point2D(getGridPoint.x, getGridPoint.y+1);

        currentObject = new Stick(true, unit, start, end);
        sticks.add((Stick) currentObject);
        invalidate();
//        Toast.makeText(getContext(), getGridPoint.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean clicked(Point2D touchLocation) {
        // Comes Here after Checking Everything
        return true;
    }

    @Override
    public void move(Point2D from, Point2D to) {
        Point2D delRaw = rawDelFromGrid(from, to);

        originLocation.x += delRaw.x;
        originLocation.y += delRaw.y;
    }

    @Override
    public void finishMoving() {
        // Do nothing
        // Grid does not need to settle
    }

    @Override
    public void drawSpecial(Canvas canvas, Point2D origin) {
        if (gridOff) return;
        for (int i=0; i<=NUMBER_OF_GRID; i++) {
            canvas.drawLine(origin.x + i*unit, origin.y, origin.x + i*unit, origin.y + NUMBER_OF_GRID*unit, gridPaint);
            canvas.drawLine(origin.x, origin.y + i*unit, origin.x + NUMBER_OF_GRID*unit, origin.y + i*unit, gridPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.drawSpecial(canvas, originLocation);

        for (Stick stick : sticks)
            stick.drawSpecial(canvas, originLocation);

        for (Coin coin: coins)
            coin.drawSpecial(canvas, originLocation);

    }

    GraphObject currentObject;
    Point2D lastLocationRaw;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ViewParent parent = getParent();
        Log.d(TAG, "onTouchEvent: Touched "+ lastLocationRaw);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                lastLocationRaw = new Point2D(event.getX(), event.getY());
                boolean found = false;

                for (Coin coin : coins) {
                    if(coin.clicked(gridPoint(lastLocationRaw))) {
                        currentObject = coin;
                        found = true;
                    }
                }

                for (Stick stick : sticks) {
                    if (found) break;
                    if (stick.clicked(gridPoint(lastLocationRaw))) {
                        currentObject = stick;
                        found = true;
                    }
                }

                if (!found) currentObject = this;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                Point2D currentLocation = new Point2D(event.getX(), event.getY());
                currentObject.move(gridPoint(lastLocationRaw), gridPoint(currentLocation));
                lastLocationRaw = currentLocation;
                break;
            }
            case MotionEvent.ACTION_UP: {
                currentObject.finishMoving();
                currentObject = null;
                break;
            }
        }

        parent.requestDisallowInterceptTouchEvent(true);

        invalidate();

        return true;
    }
}
