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

    TextView questionText;
    TextView constrainText;

    JSONObject question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        String perm_comb = "{\"difficulty\":6,\"answer\":\"৬৪৩৫\",\"series\":\"বিন্যাস  ও  সমাবেশ \",\"prob_schema\":{\"bgColor\":\"0xffffffff\",\"transX\":-2268.2893,\"transY\":-2267.6462,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":29,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":20,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":20,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":21,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":23,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":23,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":22,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":22,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":25,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":25,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":24,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":24,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":26,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":26,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":27,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":28,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":28,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":29,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":29,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":21,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":21,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":22,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":22,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":23,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":24,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":24,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":25,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":25,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":26,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":26,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":28,\"indHeadX\":20,\"type\":\"matchStick\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":9,\"indX\":20,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":10,\"indX\":27,\"useSkin\":true,\"indY\":21,\"type\":\"coin\"}],\"isIndicator\":true,\"lineColor\":\"0x00ff00ff\",\"zoom\":1400,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true},\"lineOpacity\":0.25},\"statement\":\"একটি  ১০ X  ৯  গ্রীডের (০,০) বিন্দুতে একটি  মৌমাছি  আছে  । একই  গ্রীডের  (৭,৮) বিন্দুতে একটি ফুল  রয়েছে। মৌমাছিটি  ফুল থেকে  মধু সংগ্রহ করতে চায়। মৌমাছিটির  গণিতের  প্রতি  আগ্রহ রয়েছে। তাই  সে  জানতে চায় গ্রীডের পথ অনুসরণ করে  কতভাবে  সে ফুলটির কাছে পৌঁছাতে পারবে ।কিন্তু কোনোভাবেই সে হিসেব মিলাতে পারছে না। তাই মৌমাছিটি তোমার সাহায্য চায়। তাই তোমাকে বলতে হবে কতভাবে মৌমাছিটি তার লক্ষ্যে  পৌঁছাতে পারবে?( মৌমাছিটি শুধু  গ্রীডের ডানে এবং উপরে চলাচল করতে পারে )\",\"description\":\"বিন্যাস ও সমাবেশ  মূলত গণনার  কাজ দ্রুত সম্পন্ন করতে ব্যবহার করা হয়। নির্দিষ্ট সংখ্যক জিনিস থেকে কয়েকটি বা সব কয়েকটি একেবারে নিয়ে যত প্রকারে সাজানো যায় তাদের প্রত্যেকটিকে একটি বিন্যাস বলা হয়। আর নির্দিষ্ট সংখ্যক জিনিস থেকে কয়েকটি বা সব কয়েকটি একেবারে নিয়ে যত প্রকারে নির্বাচন বা বর্জন করা যায় তাকে বলা হয় সমাবেশ। বিন্যাস বা সমাবেশের ধারণা ব্যবহার করে নিম্নোক্ত সমস্যাটি সমাধান করতে হবে।\",\"restrictions\":\"উত্তর অবশ্যই সংখ্যায় দিতে হবে যেমন : ১০০,২০০০ ইত্যাদি। কোনো অপারেটর যেমন ! ,P  ,C  ব্যবহার করা যাবে না ।\",\"sol_schema\":[{\"bgColor\":\"0xffffffff\",\"transX\":-2268.2893,\"transY\":-2267.6462,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":29,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":20,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":20,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":21,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":23,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":23,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":22,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":22,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":25,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":25,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":24,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":24,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":26,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":26,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":27,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":28,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":28,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":29,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":29,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":21,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":21,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":22,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":22,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":23,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":24,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":24,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":25,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":25,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":26,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":26,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":28,\"indHeadX\":20,\"type\":\"matchStick\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":9,\"indX\":20,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":10,\"indX\":27,\"useSkin\":true,\"indY\":21,\"type\":\"coin\"}],\"isIndicator\":true,\"lineColor\":\"0x00ff00ff\",\"zoom\":1400,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true},\"lineOpacity\":0.25}],\"title\":\"মৌমাছিকে পথ দেখাও \",\"explanation\":\"মৌমাছিটিকে  ফুলের কাছে  পৌঁছাতে  হলে  মোট  ১৫ টি ধাপ অতিক্ক্রম করতে হবে। যেভাবেই অতিক্ক্রম করা হউক না কেন এটিকে ৭টি অনুভূমিক ধাপ এবং ৮ টি উল্লম্ব ধাপ অতিক্ক্রম করতে হবে। তাই প্রত্যেক ক্ষেত্রে ৭টি অনুভূমিক ধাপ ও ৮টি উল্লম্ব ধাপ বার বার পুনরাবৃত্তি হচ্ছে। তাই এটিকে চিন্তা করা যায় এভাবে :\\n১৫টি জিনিসের মধ্যে ৭টি একধরণের এবং বাকি ৮টি একধরণের। এর বিন্যাস সংখ্যাই হবে সমাধান। \\nসুতরাং  ১৫ ! / (৭! * ৮ !) = ৬৪৩৫ ই হবে উত্তর। \",\"category\":3,\"ans_type\":1}";
        String brilliant = "{\"description\":\"In the problem figure, there are 2 squares of different sizes.\",\"restrictions\":\"->The squares may or may not be of different sizes.\\n\\n-> You can not add any new element to the board, you can just move existing elements.\\n\",\"sol_schema\":[{\"bgColor\":\"0xffffffff\",\"transX\":-2050.1387,\"transY\":-2277.6357,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"}],\"lineColor\":\"0x00ff00ff\",\"zoom\":500,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\"},\"lineOpacity\":0.25},{\"bgColor\":\"0xffffffff\",\"transX\":-2050.1387,\"transY\":-2277.6357,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"}],\"lineColor\":\"0x00ff00ff\",\"zoom\":500,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\"},\"lineOpacity\":0.25}],\"title\":\"Logical Reasoning\",\"explanation\":\"We can get 3 squares by moving the two matchsticks in the lower right corner.\\n\\nFrom the initial figure, when we remove 1 matchstick, we are \\\"destroying\\\" a square. Hence, in order for the answer to be 1, the matchstick that we add must result in 2 squares. While it's possible to create two rectangles with this type of move, as shown in the second figure, we are wanting squares, not just rectangles.\",\"difficulty\":4,\"answer\":1,\"series\":\"Joy of Problem Solving\",\"prob_schema\":{\"bgColor\":\"0xffffffff\",\"transX\":-2050.1387,\"transY\":-2277.6357,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"}],\"lineColor\":\"0x00ff00ff\",\"zoom\":500,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\"},\"lineOpacity\":0.25},\"statement\":\"What is the minimum number of matchsticks that we have to move in order to end up with exactly 3 squares?\",\"options\":[\"1\",\"2\",\"3\",\"4\"],\"category\":1,\"ans_type\":2}\n";
        try {
            question = new JSONObject(brilliant);

            // Title and other Text
            String title = question.getString("title");
            getSupportActionBar().setTitle(title);
            questionText.setText(question.getString("statement"));
            constrainText.setText(question.getString("restrictions"));

            // Tags
            addTag(question.getString("series"));
            addTag("Difficulty: "+question.getInt("difficulty")+"/10");
            if (question.has("author")) addTag("by "+question.getString("author"));

            // Problem Board
            JSONObject probSchema = question.getJSONObject("prob_schema");
            graphView.setBoardContent(probSchema);

            JSONObject defaultStick = probSchema.getJSONObject("defaultMatchStick");
            Drawable stick = graphView.setDefaultStick(defaultStick.getBoolean("useSkin"), defaultStick.getString("fillColor"));
            addStick.setImageDrawable(stick);

            JSONObject defaultCoin = probSchema.getJSONObject("defaultCoin");
            Bitmap coin = graphView.setDefaultCoin(
                    defaultCoin.has("useSkin") ? defaultCoin.getBoolean("useSkin") : false,
                    defaultCoin.has("skin") ? defaultCoin.getInt("skin") : -1 ,
                    defaultCoin.getString("innerColor"),
                    defaultCoin.getString("outerColor"));
            if (coin != null)
                addCoin.setImageBitmap(coin);

            // Solution
            answerType = question.getInt("ans_type");
            System.out.println(answerType);

            if (answerType == AnswerLayout.ANSWER_TEXT) {
                answerLayout.setAsText();
                answer = question.getString("answer");
            } else if (answerType == AnswerLayout.ANSWER_MCQ) {
                JSONArray options = question.getJSONArray("options");
                answerLayout.setAsMCQ(options);
                answer = options.getString(question.getInt("answer"));
            }
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

        questionText = findViewById(R.id.question_text);
        constrainText = findViewById(R.id.answer_constrain);

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
        try {
            builder.setTitle(question.getString("series"));
            builder.setMessage(question.getString("description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AlertDialog dialog = builder.create();

        dialog.setCancelable(true);
        dialog.show();
    }

    private void explainAnswer(boolean correct) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (correct)
            builder.setTitle("Correct Answer");
        else
            builder.setTitle("Wrong Answer");

        try {
            builder.setMessage(question.getString("explanation"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        builder.create().show();
    }

    public void checkAnswer(View v) {
        switch (answerType) {
            case AnswerLayout.ANSWER_TEXT:
            case AnswerLayout.ANSWER_MCQ: {
                explainAnswer(answer.equals(answerLayout.getAsText()));
                break;
            }
            case AnswerLayout.ANSWER_BOARD: {
                Toast.makeText(this, "Check Board", Toast.LENGTH_SHORT).show();
            }
        }
    }
}