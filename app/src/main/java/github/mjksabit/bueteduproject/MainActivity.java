package github.mjksabit.bueteduproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import github.mjksabit.bueteduproject.Graph.GraphView;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addToBoard;
    boolean isOpen = false;
    ImageView addCoin;
    ImageView addStick;

    GraphView graphView;

    Animation addOpenAnimation;
    Animation addCloseAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToBoard = findViewById(R.id.add_button);
        addCoin = findViewById(R.id.default_coin);
        addStick = findViewById(R.id.default_stick);

        graphView = findViewById(R.id.graph);

        addOpenAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_fab_open);
        addCloseAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_fab_close);

        addCoin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                graphView.addCoin(event.getRawX(), event.getRawY());
                toggleAddFAB(null);
                return false;
            }
        });

        addStick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                graphView.addStick(event.getRawX(), event.getRawY());
                toggleAddFAB(null);
                return false;
            }
        });
    }

    public void toggleAddFAB(View view) {
        if (isOpen) {
            addCoin.setVisibility(View.INVISIBLE);
            addStick.setVisibility(View.INVISIBLE);
            addToBoard.startAnimation(addCloseAnimation);
//            addToBoard.show();
        } else {
            addCoin.setVisibility(View.VISIBLE);
            addStick.setVisibility(View.VISIBLE);
            addToBoard.startAnimation(addOpenAnimation);
//            addToBoard.hide();
        }
        isOpen = !isOpen;
    }
}