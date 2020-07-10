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

    JSONObject question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        String json = "{\"difficulty\":6,\"answer\":\"৬৪৩৫\",\"series\":\"বিন্যাস  ও  সমাবেশ \",\"prob_schema\":{\"bgColor\":\"0xffffffff\",\"transX\":-2268.2893,\"transY\":-2267.6462,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":29,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":20,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":20,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":21,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":23,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":23,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":22,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":22,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":25,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":25,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":24,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":24,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":26,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":26,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":27,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":28,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":28,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":29,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":29,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":21,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":21,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":22,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":22,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":23,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":24,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":24,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":25,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":25,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":26,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":26,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":28,\"indHeadX\":20,\"type\":\"matchStick\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":9,\"indX\":20,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":10,\"indX\":27,\"useSkin\":true,\"indY\":21,\"type\":\"coin\"}],\"isIndicator\":true,\"lineColor\":\"0x00ff00ff\",\"zoom\":1400,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true},\"lineOpacity\":0.25},\"statement\":\"একটি  ১০ X  ৯  গ্রীডের (০,০) বিন্দুতে একটি  মৌমাছি  আছে  । একই  গ্রীডের  (৭,৮) বিন্দুতে একটি ফুল  রয়েছে। মৌমাছিটি  ফুল থেকে  মধু সংগ্রহ করতে চায়। মৌমাছিটির  গণিতের  প্রতি  আগ্রহ রয়েছে। তাই  সে  জানতে চায় গ্রীডের পথ অনুসরণ করে  কতভাবে  সে ফুলটির কাছে পৌঁছাতে পারবে ।কিন্তু কোনোভাবেই সে হিসেব মিলাতে পারছে না। তাই মৌমাছিটি তোমার সাহায্য চায়। তাই তোমাকে বলতে হবে কতভাবে মৌমাছিটি তার লক্ষ্যে  পৌঁছাতে পারবে?( মৌমাছিটি শুধু  গ্রীডের ডানে এবং উপরে চলাচল করতে পারে )\",\"description\":\"বিন্যাস ও সমাবেশ  মূলত গণনার  কাজ দ্রুত সম্পন্ন করতে ব্যবহার করা হয়। নির্দিষ্ট সংখ্যক জিনিস থেকে কয়েকটি বা সব কয়েকটি একেবারে নিয়ে যত প্রকারে সাজানো যায় তাদের প্রত্যেকটিকে একটি বিন্যাস বলা হয়। আর নির্দিষ্ট সংখ্যক জিনিস থেকে কয়েকটি বা সব কয়েকটি একেবারে নিয়ে যত প্রকারে নির্বাচন বা বর্জন করা যায় তাকে বলা হয় সমাবেশ। বিন্যাস বা সমাবেশের ধারণা ব্যবহার করে নিম্নোক্ত সমস্যাটি সমাধান করতে হবে।\",\"restrictions\":\"উত্তর অবশ্যই সংখ্যায় দিতে হবে যেমন : ১০০,২০০০ ইত্যাদি। কোনো অপারেটর যেমন ! ,P  ,C  ব্যবহার করা যাবে না ।\",\"sol_schema\":[{\"bgColor\":\"0xffffffff\",\"transX\":-2268.2893,\"transY\":-2267.6462,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":29,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":20,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":20,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":21,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":23,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":23,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":22,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":22,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":25,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":25,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":24,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":24,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":26,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":26,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":27,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":28,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":28,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":29,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":29,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":21,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":21,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":22,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":22,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":23,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":24,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":24,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":25,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":25,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":26,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":26,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":28,\"indHeadX\":20,\"type\":\"matchStick\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":9,\"indX\":20,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":10,\"indX\":27,\"useSkin\":true,\"indY\":21,\"type\":\"coin\"}],\"isIndicator\":true,\"lineColor\":\"0x00ff00ff\",\"zoom\":1400,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true},\"lineOpacity\":0.25}],\"title\":\"মৌমাছিকে পথ দেখাও \",\"explanation\":\"মৌমাছিটিকে  ফুলের কাছে  পৌঁছাতে  হলে  মোট  ১৫ টি ধাপ অতিক্ক্রম করতে হবে। যেভাবেই অতিক্ক্রম করা হউক না কেন এটিকে ৭টি অনুভূমিক ধাপ এবং ৮ টি উল্লম্ব ধাপ অতিক্ক্রম করতে হবে। তাই প্রত্যেক ক্ষেত্রে ৭টি অনুভূমিক ধাপ ও ৮টি উল্লম্ব ধাপ বার বার পুনরাবৃত্তি হচ্ছে। তাই এটিকে চিন্তা করা যায় এভাবে :\\n১৫টি জিনিসের মধ্যে ৭টি একধরণের এবং বাকি ৮টি একধরণের। এর বিন্যাস সংখ্যাই হবে সমাধান। \\nসুতরাং  ১৫ ! / (৭! * ৮ !) = ৬৪৩৫ ই হবে উত্তর। \",\"category\":3,\"ans_type\":1}";
        try {
            question = new JSONObject(json);
            String title = question.getString("title");
            getSupportActionBar().setTitle(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        try {
//            JSONObject probSchema = new JSONObject(problemSchema);
//            graphView.setBoardContent(probSchema);
//
//            JSONObject defaultStick = probSchema.getJSONObject("defaultMatchStick");
//            Drawable stick = graphView.setDefaultStick(defaultStick.getBoolean("useSkin"), defaultStick.getString("fillColor"));
//            addStick.setImageDrawable(stick);
//
//            JSONObject defaultCoin = probSchema.getJSONObject("defaultCoin");
//            Bitmap coin = graphView.setDefaultCoin(defaultCoin.getBoolean("useSkin"), defaultCoin.getInt("skin"), defaultCoin.getString("innerColor"), defaultCoin.getString("outerColor"));
//            if (coin != null)
//                addCoin.setImageBitmap(coin);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        try {
//            answerLayout.setAsMCQ(new JSONArray("[\"1\", \"2\", \"3\", \"4\"]"));
//            answerType = AnswerLayout.ANSWER_MCQ;
//            answer = "2";
            answerLayout.setAsText();
            answerType = AnswerLayout.ANSWER_TEXT;
            answer = "12345";
        } catch (Exception e) {
            e.printStackTrace();
        }

        addTag("Combination-Permutation");
        addTag("by Rabib");
        addTag("Difficulty: 7/10");
        addTag("on 7/7/2020");

    }

    private void init() {
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