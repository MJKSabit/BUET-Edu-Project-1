package github.mjksabit.bueteduproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
}