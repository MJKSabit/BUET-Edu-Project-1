package github.mjksabit.bueteduproject.Graph;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

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

    // Coins and Sticks are stored in the ArrayLists
    ArrayList<Coin> coins = new ArrayList<>();
    ArrayList<Stick> sticks = new ArrayList<>();

    // CoinSkin int to BitMap Converter, used Map instead of Array/ArrayList
    private static final Map<Integer, Bitmap> coinImages = new HashMap<>();

    // In Raw Point == (0,0) @ Grid Point
    private Point2D originLocation = new Point2D(0, 0);

    // PixelsPerUnit
    private float unit;

    private Paint   background      = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint   gridLinePaint   = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean noGridLine      = false;

    // Defaults for Add, in Resource Id (int) ; If default is not set, use these
    private int         defaultCoinUseSkin      = -1; // -1 for no Skin
    private int         defaultCoinInnerColor   = Color.parseColor("#FFF176");
    private int         defaultCoinOuterColor   = Color.parseColor("#999176");
    private boolean     defaultStickUseSkin     = true;
    private int         defaultStickFillColor   = Color.parseColor("#FFF176");

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphView(Context context) {
        super(context);
        init();
    }

    private void init() {
        // 10 * 10 squares is shown in Screen At a Time
        this.unit = getContext().getResources().getDisplayMetrics().widthPixels / 10.0f;

        gridLinePaint.setColor(0x99FF0000);

        // Letters
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

        // Numbers
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

        background.setColor(parseColor(backgroundColor));
        gridLinePaint.setColor(parseColor(lineColor));
        gridLinePaint.setAlpha((int)(lineOpacity*255));

        JSONArray elements = problemSchema.getJSONArray("elements");

        // To Determine initial Pan Position
        Point2D topLeft = new Point2D(50, 50);
        Point2D bottomRight = new Point2D(0, 0);

        // Loop Through Elements
        for (int i=0; i<elements.length(); i++) {
            JSONObject element = elements.getJSONObject(i);

            if (element.getString("type").equals("matchStick")) {
                Point2D start = new Point2D(element.getInt("indHeadX"), element.getInt("indHeadY"));
                Point2D end = new Point2D(element.getInt("indTailX"), element.getInt("indTailY"));
                
                // In case of, Start Point is more TopLeft, then set it
                if (topLeft.x > start.x) topLeft.x = start.x;
                if (topLeft.y > start.y) topLeft.y = start.y;

                // In case of, End Point is more TopLeft, then set it
                if (topLeft.x > end.x) topLeft.x = end.x;
                if (topLeft.y > end.y) topLeft.y = end.y;

                // Stick Fill Color
                String fillColor = element.getString("fillColor");

                // If Stick has Match Like Top
                boolean useSkin = element.has("useSkin") && element.getBoolean("useSkin");

                // IDK its use
                boolean isMust = element.has("isMust") && element.getBoolean("isMust");

                // If the stick is Stable or Dynamic
                boolean cantMove = element.has("cantMove") && element.getBoolean("cantMove");

                // Add Stick to Sticks Collection
                sticks.add(new Stick(unit, start, end, parseColor(fillColor), useSkin, !cantMove));

            } else if (element.getString("type").equals("coin")) {
                // Determine Coin Location
                Point2D position = new Point2D(element.getInt("indX"), element.getInt("indY"));

                // In case of, Position is more TopLeft, then set it
                if (topLeft.x > position.x) topLeft.x = position.x;
                if (topLeft.y > position.y) topLeft.y = position.y;

                // Coin Inner and Outer Circle Color in case of NoSkin
                String innerColor = element.getString("innerColor");
                String outerColor = element.getString("outerColor");

                // Coin can be moved or not
                boolean cantMove = element.has("cantMove") && element.getBoolean("cantMove");

                int skin = -1; // Default Value, Indicates no Skin

                // Use Skin if defined in JSON
                if (element.getBoolean("useSkin"))
                    skin = element.getInt("skin");

                // Use Skin Image if the image for that skin is Available, else "null" (no skin)
                Bitmap image = coinImages.containsKey(skin) ?
                        coinImages.get(skin) : null;

                // Add new Coin to Collection
                coins.add(new Coin(unit, !cantMove, position, image, parseColor(innerColor), parseColor(outerColor), skin));

            }
        }

        // Pan to Contents TopLeft corner with 0.5 * unit Margin
        originLocation = rawPoint(new Point2D(-topLeft.x+0.5f, -topLeft.y+0.5f));

        // Draw all Contents with Panning
        invalidate();
    }

    public Drawable setDefaultStick(boolean useSkin, String skinColor) {
        defaultStickUseSkin = useSkin;
        defaultStickFillColor = parseColor(skinColor);

        Drawable image = useSkin ? getContext().getResources().getDrawable(R.drawable.match_stick) :
                getContext().getResources().getDrawable(R.drawable.match_stick_without_skin); // Use this Image in Case of No Skin is Used

        return image;
    }

    public Bitmap setDefaultCoin(boolean useSkin, int skinNo, String innerColor, String outerColor) {
        defaultCoinUseSkin = useSkin ? skinNo : -1; // If no Skin is used, set to -1

        defaultCoinInnerColor = parseColor(innerColor);
        defaultCoinOuterColor = parseColor(outerColor);

        // Skin is used and We have Image for that skin
        if (defaultCoinUseSkin != -1 && coinImages.containsKey(defaultCoinUseSkin))
            return coinImages.get(defaultCoinUseSkin);

        // No Skin Image Available
        return null;
    }

    // Color int Parser From JavaFX Color
    private int parseColor(String color) {
        return Color.parseColor("#"+color.substring(2, 8));
    }

    // Remove Grid Lines
    public void setNoGridLine(boolean noGridLine) {
        this.noGridLine = noGridLine;
    }

    // Get Relative Grid Point from View's Pixel Point
    private Point2D gridPoint(Point2D rawPoint) {
        float x = rawPoint.x - originLocation.x;
        float y = rawPoint.y - originLocation.y;

        return new Point2D(x/unit, y/unit);
    }

    // Get View's Pixel Point from Grid Relative Point
    private Point2D rawPoint(Point2D gridPoint) {
        float x = gridPoint.x*unit + originLocation.x;
        float y = gridPoint.y*unit + originLocation.y;

        return new Point2D(x, y);
    }

    // Get View's Pixel Point Difference from Grid Relative Point
    private Point2D rawDelFromGrid(Point2D from, Point2D to) {
        float delX = (to.x - from.x)*unit;
        float delY = (to.y - from.y)*unit;

        return new Point2D(delX, delY);
    }

    // Add New Coin :: (x,y) Relative to Screen in Pixels , Tap Location
    public void addCoin(float x, float y) {

        // GraphView Start Location Relative to Screen in Pixels
        int[] myLoc = new int[2];
        getLocationOnScreen(myLoc);

        // Last Location Set to Move Newly Added Coin
        lastLocationRaw = new Point2D( x - myLoc[0], y-myLoc[1]);
        Point2D point = gridPoint(lastLocationRaw);

        // Get BitMap if the Image Available
        Bitmap image = coinImages.containsKey(defaultCoinUseSkin) ?
                coinImages.get(defaultCoinUseSkin) : null;

        // Add New Object to Moving State
        currentObject = new Coin(unit, true, point, image, defaultCoinInnerColor, defaultCoinOuterColor, defaultCoinUseSkin);
        // Add New Coin
        coins.add((Coin) currentObject);

        // Draw Everything with Newly Added Coin
        invalidate();
    }

    public void addStick(float x, float y) {

        // GraphView Start Location Relative to Screen in Pixels
        int[] myLoc = new int[2];
        getLocationOnScreen(myLoc);

        // Last Location Set to Move Newly Added Coin
        lastLocationRaw = new Point2D( x - myLoc[0], y-myLoc[1]);
        Point2D getGridPoint = gridPoint(lastLocationRaw);

        // Newly Added Stick is Vertical
        Point2D start = new Point2D( getGridPoint.x, getGridPoint.y-1);
        Point2D end = new Point2D(getGridPoint.x, getGridPoint.y+1);

        // Add New Object to Moving State
        currentObject = new Stick(unit, start, end, defaultStickFillColor, defaultStickUseSkin, true);
        // Add New Stick
        sticks.add((Stick) currentObject);

        // Draw Everything with Newly Added Coin
        invalidate();
    }

    @Override
    public boolean clicked(Point2D touchLocation) {
        // Comes Here after Checking Everything, Pan
        return true;
    }

    // Graph Is Touched, Panning in Progress
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

    // In GraphView, Draw GridLines if NEEDED
    @Override
    public void drawSpecial(Canvas canvas, Point2D origin) {
        if (noGridLine) return;
        for (int i=0; i<=NUMBER_OF_GRID; i++) {
            canvas.drawLine(origin.x + i*unit, origin.y, origin.x + i*unit, origin.y + NUMBER_OF_GRID*unit, gridLinePaint);
            canvas.drawLine(origin.x, origin.y + i*unit, origin.x + NUMBER_OF_GRID*unit, origin.y + i*unit, gridLinePaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw Grid Lines
        this.drawSpecial(canvas, originLocation);

        // Draw Sticks
        for (Stick stick : sticks)
            stick.drawSpecial(canvas, originLocation);

        // Draw Coins
        for (Coin coin: coins)
            coin.drawSpecial(canvas, originLocation);

    }

    // Used for Currently Moving Object
    private GraphObject currentObject;

    // Used to Determine Position Change, in Raw Point
    private Point2D lastLocationRaw;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            // New Touch Detected
            case MotionEvent.ACTION_DOWN: {

                // Save Last Known Location
                lastLocationRaw = new Point2D(event.getX(), event.getY());

                // Flag to Check if GraphObject is Touched
                boolean found = false;

                // Check For Coins, If Touched
                for (Coin coin : coins) {
                    if(coin.clicked(gridPoint(lastLocationRaw))) {
                        currentObject = coin;
                        found = true;
                    }
                }
                if (found) break;

                // Check for Sticks, If touched
                for (Stick stick : sticks) {
                    if (stick.clicked(gridPoint(lastLocationRaw))) {
                        currentObject = stick;
                        found = true;
                    }
                }

                // No Coin, No Stick; So Move Object is Graph (Pan)
                if (!found) currentObject = this;

                break;
            }
            // Touch Moved
            case MotionEvent.ACTION_MOVE: {
                Point2D currentLocationRaw = new Point2D(event.getX(), event.getY());

                // Move Currently Selected Object from last Location to Current Location
                // Relative Grid Point is Provided using gridPoint()
                currentObject.move(gridPoint(lastLocationRaw), gridPoint(currentLocationRaw));

                lastLocationRaw = currentLocationRaw;

                break;
            }
            // Touch Finished
            case MotionEvent.ACTION_UP: {
                // Set Coin/Stick Point to GridLine Intersections
                currentObject.finishMoving();

                // No Object to move
                currentObject = null;

                break;
            }
        }

        // Preventing ScrollView to Work within GraphView
        ViewParent parent = getParent();
        parent.requestDisallowInterceptTouchEvent(true);

        // Draw Changes
        invalidate();

        // Touch Handled
        return true;
    }

    @Override
    public boolean match(JSONObject object) throws JSONException {
        JSONArray elements = object.getJSONArray("elements");

        int i;
        for (i=0; i<elements.length(); i++) {
            JSONObject element = elements.getJSONObject(i);

            // For Fixed Coin/Stick, No need to check
            if (element.has("cantMove") && element.getBoolean("cantMove")) continue;

            if (element.getString("type").equals("coin")) {
                int j=0;

                // Go through all the coins
                for (; j<coins.size(); j++) {
                    // Match Found, Stop Looking at Other Coins
                    if (coins.get(j).match(element)) break;
                }

                // Went through all the Coins, no match found which Means THE GRAPH DOES NOT MATCH
                if (j==coins.size()) break;
            }
            else {
                int j=0;

                // Go through all the sticks
                for (; j<sticks.size(); j++) {
                    // Match Found, Stop Looking at Other Sticks
                    if (sticks.get(j).match(element)) break;
                }

                // Went through all the Sticks, no match found which Means THE GRAPH DOES NOT MATCH
                if (j==sticks.size()) break;
            }
        }

        // Went through all the Elements, no one broke the loop, Match for Every Element Found
        return i==elements.length();
    }

}
