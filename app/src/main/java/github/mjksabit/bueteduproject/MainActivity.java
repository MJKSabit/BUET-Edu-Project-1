package github.mjksabit.bueteduproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import github.mjksabit.bueteduproject.Graph.GraphView;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addToBoard;
    boolean isOpen = false;
    ImageView addCoin;
    ImageView addStick;

    GraphView graphView;

    ConstraintLayout graphHolder;
    ScrollView scrollQuestion;

    Animation addOpenAnimation;
    Animation addCloseAnimation;
    Animation addOpenDrawer;
    Animation addCloseDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToBoard = findViewById(R.id.add_button);
        addCoin = findViewById(R.id.default_coin);
        addStick = findViewById(R.id.default_stick);

        graphView = findViewById(R.id.graph);
        graphHolder = findViewById(R.id.graph_holder);
        scrollQuestion = findViewById(R.id.scroll_ques);


        addOpenAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_fab_open);
        addCloseAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_fab_close);
        addOpenDrawer = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_fab_drawer_open);
        addCloseDrawer = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_fab_drawer_close);

        addCoin.setOnTouchListener((v, event) -> {
            graphView.addCoin(event.getRawX(), event.getRawY());
            toggleAddFAB(null);
            return false;
        });

        addStick.setOnTouchListener((v, event) -> {
            graphView.addStick(event.getRawX(), event.getRawY());
            toggleAddFAB(null);
            return false;
        });

        String title = getResources().getString(R.string.title_placeholder);
        getSupportActionBar().setTitle(title);

        String problemSchema = "{\n" +
                "    \"bgColor\": \"0xffffffff\",\n" +
                "    \"transX\": -2050.138769192783,\n" +
                "    \"transY\": -2277.6358217084357,\n" +
                "    \"indicatorColor\": \"0x32cd32ff\",\n" +
                "    \"elements\": [\n" +
                "      {\n" +
                "        \"indTailY\": 23,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 21,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 25,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 21,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 23,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 23,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 23,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 21,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 25,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 23,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 25,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 21,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 23,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 23,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 25,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 23,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 23,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 25,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 23,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 23,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 27,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 21,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 25,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 21,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 27,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 23,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 27,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 21,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 27,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 25,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 27,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 23,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 25,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 25,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 23,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 25,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"indTailY\": 27,\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailX\": 25,\n" +
                "        \"isMust\": true,\n" +
                "        \"indHeadY\": 25,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indHeadX\": 25,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"lineColor\": \"0x00ff00ff\",\n" +
                "    \"zoom\": 500,\n" +
                "    \"defaultMatchStick\": {\n" +
                "      \"fillColor\": \"0x0090ffff\",\n" +
                "      \"isMust\": true,\n" +
                "      \"useSkin\": true\n" +
                "    },\n" +
                "    \"defaultCoin\": {\n" +
                "      \"outerColor\": \"0x0090ffff\",\n" +
                "      \"isMust\": true,\n" +
                "      \"innerColor\": \"0x004588ff\"\n" +
                "    },\n" +
                "    \"lineOpacity\": 0.25\n" +
                "  }";

        try {
            graphView.setBoardContent(new JSONObject(problemSchema));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void toggleAddFAB(View view) {
        if (isOpen) {

            addToBoard.startAnimation(addCloseAnimation);
            if (view == addToBoard) {
                addCoin.startAnimation(addCloseDrawer);
                addStick.startAnimation(addCloseDrawer);
                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.schedule(() ->
                        runOnUiThread(() -> {
                            addCoin.setVisibility(View.INVISIBLE);
                            addStick.setVisibility(View.INVISIBLE);
                        })
                , 100, TimeUnit.MILLISECONDS);
            } else {
                addCoin.setVisibility(View.INVISIBLE);
                addStick.setVisibility(View.INVISIBLE);
            }
//            addToBoard.show();
        } else {
            addCoin.setVisibility(View.VISIBLE);
            addStick.setVisibility(View.VISIBLE);
            addCoin.startAnimation(addOpenDrawer);
            addStick.startAnimation(addOpenDrawer);
            addToBoard.startAnimation(addOpenAnimation);
//            addToBoard.hide();
        }
        isOpen = !isOpen;
    }

    public void showDetails(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.series_placeholder);
        builder.setMessage(R.string.description_placeholder);
        AlertDialog dialog = builder.create();

        dialog.setCancelable(true);
        dialog.show();
    }
}