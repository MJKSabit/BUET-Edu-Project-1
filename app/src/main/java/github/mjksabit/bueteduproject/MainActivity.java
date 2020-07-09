package github.mjksabit.bueteduproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import github.mjksabit.bueteduproject.Answer.AnswerLayout;
import github.mjksabit.bueteduproject.Graph.GraphView;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addToBoard;
    boolean isOpen = false;
    ImageView addCoin;
    ImageView addStick;

    GraphView graphView;

    ConstraintLayout graphHolder;
    ScrollView scrollQuestion;

    AnswerLayout answerLayout;
    ChipGroup tagHolder;

    Animation addOpenAnimation;
    Animation addCloseAnimation;
    Animation addOpenDrawer;
    Animation addCloseDrawer;

    private int answerType;
    private String answer;

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
        answerLayout = findViewById(R.id.answer_container);
        tagHolder = findViewById(R.id.tags);

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
                "    \"transX\": -2268.2892489893543,\n" +
                "    \"transY\": -2267.64617092752,\n" +
                "    \"indicatorColor\": \"0x32cd32ff\",\n" +
                "    \"elements\": [\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 27,\n" +
                "        \"indHeadX\": 30,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 27,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 30,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 29,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 20,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 20,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 21,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 21,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 23,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 23,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 22,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 22,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 25,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 25,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 24,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 24,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 26,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 26,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 27,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 27,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 28,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 28,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 29,\n" +
                "        \"indTailX\": 29,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 20,\n" +
                "        \"indHeadX\": 29,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 21,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 21,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 22,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 22,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 23,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 23,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 24,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 24,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 25,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 25,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 26,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 26,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 27,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 27,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"fillColor\": \"0x0090ffff\",\n" +
                "        \"indTailY\": 28,\n" +
                "        \"indTailX\": 30,\n" +
                "        \"cantMove\": true,\n" +
                "        \"indHeadY\": 28,\n" +
                "        \"indHeadX\": 20,\n" +
                "        \"type\": \"matchStick\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"outerColor\": \"0x0090ffff\",\n" +
                "        \"innerColor\": \"0x004588ff\",\n" +
                "        \"cantMove\": true,\n" +
                "        \"skin\": 9,\n" +
                "        \"indX\": 20,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indY\": 29,\n" +
                "        \"type\": \"coin\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"outerColor\": \"0x0090ffff\",\n" +
                "        \"innerColor\": \"0x004588ff\",\n" +
                "        \"cantMove\": true,\n" +
                "        \"skin\": 10,\n" +
                "        \"indX\": 27,\n" +
                "        \"useSkin\": true,\n" +
                "        \"indY\": 21,\n" +
                "        \"type\": \"coin\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"isIndicator\": true,\n" +
                "    \"lineColor\": \"0x00ff00ff\",\n" +
                "    \"zoom\": 1400,\n" +
                "    \"defaultMatchStick\": {\n" +
                "      \"fillColor\": \"0x0090ffff\",\n" +
                "      \"isMust\": true,\n" +
                "      \"useSkin\": false\n" +
                "    },\n" +
                "    \"defaultCoin\": {\n" +
                "      \"outerColor\": \"0x0090ffff\",\n" +
                "      \"isMust\": true,\n" +
                "      \"innerColor\": \"0x004588ff\",\n" +
                "      \"skin\": 7,\n" +
                "      \"useSkin\": true\n" +
                "    },\n" +
                "    \"lineOpacity\": 0.25\n" +
                "  }";

        try {
            JSONObject probSchema = new JSONObject(problemSchema);
            graphView.setBoardContent(probSchema);

            JSONObject defaultStick = probSchema.getJSONObject("defaultMatchStick");
            Drawable stick = graphView.setDefaultStick(defaultStick.getBoolean("useSkin"), defaultStick.getString("fillColor"));
            addStick.setImageDrawable(stick);

            JSONObject defaultCoin = probSchema.getJSONObject("defaultCoin");
            Bitmap coin = graphView.setDefaultCoin(defaultCoin.getBoolean("useSkin"), defaultCoin.getInt("skin"), defaultCoin.getString("innerColor"), defaultCoin.getString("outerColor"));
            if (coin != null)
                addCoin.setImageBitmap(coin);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            answerLayout.setAsMCQ(new JSONArray("[\"1\", \"2\", \"3\", \"4\"]"));
            answerType = AnswerLayout.ANSWER_MCQ;
            answer = "2";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        addTag("Okay");

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
        } else {
            addCoin.setVisibility(View.VISIBLE);
            addStick.setVisibility(View.VISIBLE);
            addCoin.startAnimation(addOpenDrawer);
            addStick.startAnimation(addOpenDrawer);
            addToBoard.startAnimation(addOpenAnimation);
        }
        isOpen = !isOpen;
    }

    private void addTag(String tagText) {
        Chip tag = new Chip(this);
        tag.setText(tagText);
        tagHolder.addView(tag);
    }

    public void showDetails(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.series_placeholder);
        builder.setMessage(R.string.description_placeholder);
        AlertDialog dialog = builder.create();

        dialog.setCancelable(true);
        dialog.show();
    }

    public void checkAnswer(View v) {
        switch (answerType) {
            case AnswerLayout.ANSWER_TEXT:
            case AnswerLayout.ANSWER_MCQ: {
                if (answer.equals(answerLayout.getAsText()))
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                break;
            }
            case AnswerLayout.ANSWER_BOARD: {
                Toast.makeText(this, "Check Board", Toast.LENGTH_SHORT).show();
            }
        }
    }
}