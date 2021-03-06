package github.mjksabit.bueteduproject.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import github.mjksabit.bueteduproject.Answer.AnswerDialog;
import github.mjksabit.bueteduproject.Answer.AnswerLayout;
import github.mjksabit.bueteduproject.Answer.BoardMatcher;
import github.mjksabit.bueteduproject.Graph.GraphView;
import github.mjksabit.bueteduproject.R;
import github.mjksabit.bueteduproject.Utils.Constant;

import static github.mjksabit.bueteduproject.Utils.Constant.ANSWER_BOARD;
import static github.mjksabit.bueteduproject.Utils.Constant.ANSWER_MCQ;
import static github.mjksabit.bueteduproject.Utils.Constant.ANSWER_TEXT;

public class MainActivity extends AppCompatActivity {

    // Add Options
    private FloatingActionButton addToBoard;
    private boolean fabIsOpen = false;
    private ImageView addCoinToBoard;
    private ImageView addStickToBoard;

    private GraphView graph;

    private ConstraintLayout graphHolder;
    private ScrollView scrollQuestion;

    private AnswerLayout answerLayout;
    private ChipGroup questionTags;

    private Animation fabOpen;
    private Animation fabClose;
    private Animation addPaneOpen;
    private Animation addPaneClose;

    private int answerType;
    private String answer; // Store Ans Here in case of MCQ or Text

    private TextView statementText;
    private TextView restrictionText;

    private JSONObject question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(); // Assign id from Resources

        addToBoard.hide(); // Hide FAB by Default, No adding

        // Sample Question JSON
        String perm_comb = "{\"difficulty\":6,\"answer\":\"৬৪৩৫\",\"series\":\"বিন্যাস  ও  সমাবেশ \",\"prob_schema\":{\"bgColor\":\"0xffffffff\",\"transX\":-2268.2893,\"transY\":-2267.6462,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":29,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":20,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":20,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":21,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":23,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":23,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":22,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":22,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":25,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":25,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":24,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":24,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":26,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":26,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":27,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":28,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":28,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":29,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":29,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":21,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":21,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":22,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":22,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":23,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":24,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":24,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":25,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":25,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":26,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":26,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":28,\"indHeadX\":20,\"type\":\"matchStick\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":9,\"indX\":20,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":10,\"indX\":27,\"useSkin\":true,\"indY\":21,\"type\":\"coin\"}],\"isIndicator\":true,\"lineColor\":\"0x00ff00ff\",\"zoom\":1400,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true},\"lineOpacity\":0.25},\"statement\":\"একটি  ১০ X  ৯  গ্রীডের (০,০) বিন্দুতে একটি  মৌমাছি  আছে  । একই  গ্রীডের  (৭,৮) বিন্দুতে একটি ফুল  রয়েছে। মৌমাছিটি  ফুল থেকে  মধু সংগ্রহ করতে চায়। মৌমাছিটির  গণিতের  প্রতি  আগ্রহ রয়েছে। তাই  সে  জানতে চায় গ্রীডের পথ অনুসরণ করে  কতভাবে  সে ফুলটির কাছে পৌঁছাতে পারবে ।কিন্তু কোনোভাবেই সে হিসেব মিলাতে পারছে না। তাই মৌমাছিটি তোমার সাহায্য চায়। তাই তোমাকে বলতে হবে কতভাবে মৌমাছিটি তার লক্ষ্যে  পৌঁছাতে পারবে?( মৌমাছিটি শুধু  গ্রীডের ডানে এবং উপরে চলাচল করতে পারে )\",\"description\":\"বিন্যাস ও সমাবেশ  মূলত গণনার  কাজ দ্রুত সম্পন্ন করতে ব্যবহার করা হয়। নির্দিষ্ট সংখ্যক জিনিস থেকে কয়েকটি বা সব কয়েকটি একেবারে নিয়ে যত প্রকারে সাজানো যায় তাদের প্রত্যেকটিকে একটি বিন্যাস বলা হয়। আর নির্দিষ্ট সংখ্যক জিনিস থেকে কয়েকটি বা সব কয়েকটি একেবারে নিয়ে যত প্রকারে নির্বাচন বা বর্জন করা যায় তাকে বলা হয় সমাবেশ। বিন্যাস বা সমাবেশের ধারণা ব্যবহার করে নিম্নোক্ত সমস্যাটি সমাধান করতে হবে।\",\"restrictions\":\"উত্তর অবশ্যই সংখ্যায় দিতে হবে যেমন : ১০০,২০০০ ইত্যাদি। কোনো অপারেটর যেমন ! ,P  ,C  ব্যবহার করা যাবে না ।\",\"sol_schema\":[{\"bgColor\":\"0xffffffff\",\"transX\":-2268.2893,\"transY\":-2267.6462,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":30,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":29,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":20,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":20,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":21,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":23,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":23,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":22,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":22,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":25,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":25,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":24,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":24,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":26,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":26,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":27,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":28,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":28,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":29,\"indTailX\":29,\"cantMove\":true,\"indHeadY\":20,\"indHeadX\":29,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":21,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":21,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":22,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":22,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":23,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":24,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":24,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":25,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":25,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":26,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":26,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":27,\"indHeadX\":20,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":30,\"cantMove\":true,\"indHeadY\":28,\"indHeadX\":20,\"type\":\"matchStick\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":9,\"indX\":20,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":10,\"indX\":27,\"useSkin\":true,\"indY\":21,\"type\":\"coin\"}],\"isIndicator\":true,\"lineColor\":\"0x00ff00ff\",\"zoom\":1400,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true},\"lineOpacity\":0.25}],\"title\":\"মৌমাছিকে পথ দেখাও \",\"explanation\":\"মৌমাছিটিকে  ফুলের কাছে  পৌঁছাতে  হলে  মোট  ১৫ টি ধাপ অতিক্ক্রম করতে হবে। যেভাবেই অতিক্ক্রম করা হউক না কেন এটিকে ৭টি অনুভূমিক ধাপ এবং ৮ টি উল্লম্ব ধাপ অতিক্ক্রম করতে হবে। তাই প্রত্যেক ক্ষেত্রে ৭টি অনুভূমিক ধাপ ও ৮টি উল্লম্ব ধাপ বার বার পুনরাবৃত্তি হচ্ছে। তাই এটিকে চিন্তা করা যায় এভাবে :\\n১৫টি জিনিসের মধ্যে ৭টি একধরণের এবং বাকি ৮টি একধরণের। এর বিন্যাস সংখ্যাই হবে সমাধান। \\nসুতরাং  ১৫ ! / (৭! * ৮ !) = ৬৪৩৫ ই হবে উত্তর। \",\"category\":3,\"ans_type\":1}";
        String brilliant = "{\"description\":\"In the problem figure, there are 2 squares of different sizes.\",\"restrictions\":\"->The squares may or may not be of different sizes.\\n\\n-> You can not add any new element to the board, you can just move existing elements.\\n\",\"sol_schema\":[{\"bgColor\":\"0xffffffff\",\"transX\":-2050.1387,\"transY\":-2277.6357,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"}],\"lineColor\":\"0x00ff00ff\",\"zoom\":500,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\"},\"lineOpacity\":0.25},{\"bgColor\":\"0xffffffff\",\"transX\":-2050.1387,\"transY\":-2277.6357,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"}],\"lineColor\":\"0x00ff00ff\",\"zoom\":500,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\"},\"lineOpacity\":0.25}],\"title\":\"Logical Reasoning\",\"explanation\":\"We can get 3 squares by moving the two matchsticks in the lower right corner.\\n\\nFrom the initial figure, when we remove 1 matchstick, we are \\\"destroying\\\" a square. Hence, in order for the answer to be 1, the matchstick that we add must result in 2 squares. While it's possible to create two rectangles with this type of move, as shown in the second figure, we are wanting squares, not just rectangles.\",\"difficulty\":4,\"answer\":1,\"series\":\"Joy of Problem Solving\",\"prob_schema\":{\"bgColor\":\"0xffffffff\",\"transX\":-2050.1387,\"transY\":-2277.6357,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"}],\"lineColor\":\"0x00ff00ff\",\"zoom\":500,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\"},\"lineOpacity\":0.25},\"statement\":\"What is the minimum number of matchsticks that we have to move in order to end up with exactly 3 squares?\",\"options\":[\"1\",\"2\",\"3\",\"4\"],\"category\":1,\"ans_type\":2}\n";
        String shobdo_banao = "{\"author\":\"Md. Mehrab Haque\",\"isReviewed\":true,\"restrictions\":\"কোন নতুন অক্ষর বাহিরে থেকে যোগ বা বাদ দিতে পারবেনা।\",\"description\":\"প্রবলেম ফিগারে ৪ টি অর্থবোধক ইংরেজি শব্দ আছে, কিন্তু প্রতিটি শব্দে একটি করে অক্ষর কম আছে , যেগুলো ডান পাশে রাখা আছে।\",\"sol_schema\":[{\"bgColor\":\"0xffffffff\",\"transX\":-2246.4276877526627,\"elements\":[{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":20,\"indX\":22,\"useSkin\":true,\"indY\":22,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":23,\"skin\":14,\"cantMove\":true,\"useSkin\":true,\"indY\":22,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":24,\"skin\":26,\"useSkin\":true,\"indY\":22,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":18,\"indX\":25,\"cantMove\":true,\"useSkin\":true,\"indY\":22,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":22,\"skin\":17,\"cantMove\":true,\"useSkin\":true,\"indY\":24,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":18,\"indX\":23,\"useSkin\":true,\"indY\":24,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":27,\"indX\":24,\"cantMove\":true,\"useSkin\":true,\"indY\":24,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":25,\"skin\":38,\"cantMove\":true,\"useSkin\":true,\"indY\":24,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":22,\"indX\":22,\"cantMove\":true,\"useSkin\":true,\"indY\":26,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":23,\"skin\":16,\"useSkin\":true,\"indY\":26,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":24,\"skin\":18,\"cantMove\":true,\"useSkin\":true,\"indY\":26,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":31,\"indX\":22,\"useSkin\":true,\"indY\":28,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":22,\"indX\":23,\"useSkin\":true,\"indY\":28,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"indX\":24,\"skin\":16,\"useSkin\":true,\"indY\":28,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"indX\":25,\"skin\":18,\"useSkin\":true,\"indY\":28,\"type\":\"coin\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":29,\"isMust\":true,\"indHeadY\":23,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":27,\"isMust\":true,\"indHeadY\":23,\"indHeadX\":27,\"type\":\"matchStick\"},{\"indTailY\":28,\"fillColor\":\"0x0090ffff\",\"indTailX\":29,\"isMust\":true,\"indHeadY\":28,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":29,\"isMust\":true,\"indHeadY\":23,\"indHeadX\":29,\"type\":\"matchStick\"},{\"indTailY\":29,\"fillColor\":\"0x808080ff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":21,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x808080ff\",\"indTailY\":21,\"indTailX\":26,\"isMust\":true,\"indHeadY\":21,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":21,\"indHeadX\":26,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":25,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x808080ff\",\"indTailY\":27,\"indTailX\":26,\"isMust\":true,\"indHeadY\":27,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x808080ff\",\"indTailY\":27,\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"indHeadX\":25,\"type\":\"matchStick\"},{\"indTailY\":29,\"fillColor\":\"0x808080ff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":29,\"indHeadX\":26,\"type\":\"matchStick\"},{\"indTailY\":29,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":27,\"indHeadX\":26,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":23,\"indHeadX\":21,\"type\":\"matchStick\"}],\"transY\":-2376.4483068572326,\"indicatorColor\":\"0x32cd32ff\",\"zoom\":800,\"lineColor\":\"0x00ff00ff\",\"defaultMatchStick\":{\"fillColor\":\"0x47ff00ff\",\"isMust\":true},\"lineOpacity\":0,\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true}}],\"explanation\":\"Explanation\",\"title\":\"শব্দ বানাও\",\"difficulty\":4,\"series\":\"English Words\",\"statement\":\"ডান পাশ থেকে অক্ষরগুলো নিয়ে শব্দগুলো সম্পূর্ণ কর\",\"prob_schema\":{\"bgColor\":\"0xffffffff\",\"transX\":-2253.857437415781,\"transY\":-2261.287187078898,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":20,\"cantMove\":true,\"indX\":22,\"useSkin\":true,\"indY\":22,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":14,\"indX\":23,\"useSkin\":true,\"indY\":22,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":28,\"skin\":26,\"useSkin\":true,\"indY\":26,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":25,\"cantMove\":true,\"skin\":18,\"useSkin\":true,\"indY\":22,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":17,\"indX\":22,\"useSkin\":true,\"indY\":24,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":18,\"indX\":28,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":27,\"indX\":24,\"cantMove\":true,\"useSkin\":true,\"indY\":24,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":38,\"cantMove\":true,\"indX\":25,\"useSkin\":true,\"indY\":24,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":22,\"indX\":22,\"useSkin\":true,\"indY\":26,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":16,\"indX\":28,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"indX\":24,\"skin\":18,\"useSkin\":true,\"indY\":26,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":31,\"cantMove\":true,\"indX\":22,\"useSkin\":true,\"indY\":28,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":28,\"skin\":22,\"useSkin\":true,\"indY\":24,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":16,\"indX\":24,\"useSkin\":true,\"indY\":28,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":18,\"cantMove\":true,\"indX\":25,\"useSkin\":true,\"indY\":28,\"type\":\"coin\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":29,\"isMust\":true,\"indHeadY\":23,\"indHeadX\":27,\"type\":\"matchStick\"},{\"indTailY\":28,\"fillColor\":\"0x0090ffff\",\"indTailX\":27,\"isMust\":true,\"indHeadY\":23,\"indHeadX\":27,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":28,\"indTailX\":29,\"isMust\":true,\"indHeadY\":28,\"indHeadX\":27,\"type\":\"matchStick\"},{\"indTailY\":28,\"fillColor\":\"0x0090ffff\",\"indTailX\":29,\"isMust\":true,\"indHeadY\":23,\"indHeadX\":29,\"type\":\"matchStick\"},{\"fillColor\":\"0x808080ff\",\"indTailY\":29,\"indTailX\":21,\"isMust\":true,\"indHeadY\":21,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":21,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":21,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":21,\"indHeadX\":26,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":25,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x808080ff\",\"indTailY\":27,\"indTailX\":26,\"isMust\":true,\"indHeadY\":27,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x808080ff\",\"indTailY\":27,\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"indHeadX\":25,\"type\":\"matchStick\"},{\"indTailY\":29,\"fillColor\":\"0x808080ff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":29,\"indHeadX\":26,\"type\":\"matchStick\"},{\"indTailY\":29,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":27,\"indHeadX\":26,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x808080ff\",\"indTailX\":26,\"isMust\":true,\"indHeadY\":23,\"indHeadX\":21,\"type\":\"matchStick\"}],\"zoom\":800,\"lineColor\":\"0x00ff00ff\",\"defaultMatchStick\":{\"fillColor\":\"0x47ff00ff\",\"isMust\":true},\"lineOpacity\":0,\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true}},\"category\":6,\"ans_type\":0,\"timestamp\":1593756719253}";
        String borger_khoj = "{\"author\":\"Md. Mehrab Haque\",\"isReviewed\":true,\"description\":\"প্রবলেমের ছবিতে ম্যাচের কাঠি দিয়ে মোট ২ টি বর্গক্ষেত্র তৈরী করা আছে।\\nএকটু মাথা খাটালেই দেখতে পাবে আর কোন ম্যাচের কাঠি যোগ না করে শুধুমাত্র যেগুলো আছে সেগুলো সরিয়েই এখানে আরেকটি (অর্থাৎ মোট ৩টি) বর্গক্ষেত্র তৈরী করা যাবে।\",\"restrictions\":\"-> নতুন কোন ম্যাচের কাঠি যোগ করতে পারবেনা।\\n\\n-> কোন ম্যাচের কাঠি বাদ দিতে পারবেনা\\n\\n-> লক্ষ্য কর প্রতিটি ম্যাচের কাঠির দৈর্ঘ্য ২ বর্গঘর, সবগুলো ম্যাচের কাঠির এ দৈর্ঘ্য একই (২ বর্গঘর) থাকতে হবে\",\"sol_schema\":[{\"bgColor\":\"0xffffffff\",\"transX\":-2050.138769192783,\"elements\":[{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"useSkin\":true,\"indHeadY\":25,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"useSkin\":true,\"indHeadY\":25,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":23,\"isMust\":true,\"indHeadY\":27,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":25,\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"}],\"indicatorColor\":\"0x32cd32ff\",\"transY\":-2277.6358217084357,\"lineColor\":\"0x00ff00ff\",\"zoom\":500,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"lineOpacity\":0.25,\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\"}}],\"title\":\"বর্গের খোঁজ\",\"explanation\":\"We can get 3 squares by moving the two matchsticks in the lower right corner.\\n\\nFrom the initial figure, when we remove 1 matchstick, we are \\\"destroying\\\" a square. Hence, in order for the answer to be 1, the matchstick that we add must result in 2 squares. While it's possible to create two rectangles with this type of move, as shown in the second figure, we are wanting squares, not just rectangles.\",\"difficulty\":4,\"series\":\"Joy of Problem Solving\",\"statement\":\"সর্বোচ্চ ২টি ম্যাচের কাঠি সরিয়ে ৩ টি বর্গক্ষেত্র তৈরী কর\",\"prob_schema\":{\"bgColor\":\"0xffffffff\",\"transX\":-2050.138769192783,\"transY\":-2277.6358217084357,\"indicatorColor\":\"0x32cd32ff\",\"elements\":[{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":23,\"indTailX\":23,\"isMust\":true,\"useSkin\":true,\"indHeadY\":23,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"useSkin\":true,\"indHeadY\":25,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":23,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"indHeadY\":23,\"useSkin\":true,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":21,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":21,\"type\":\"matchStick\"},{\"indTailY\":27,\"fillColor\":\"0x0090ffff\",\"indTailX\":23,\"isMust\":true,\"useSkin\":true,\"indHeadY\":27,\"indHeadX\":21,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":25,\"isMust\":true,\"useSkin\":true,\"indHeadY\":27,\"indHeadX\":23,\"type\":\"matchStick\"},{\"indTailY\":25,\"fillColor\":\"0x0090ffff\",\"indTailX\":25,\"isMust\":true,\"useSkin\":true,\"indHeadY\":23,\"indHeadX\":25,\"type\":\"matchStick\"},{\"fillColor\":\"0x0090ffff\",\"indTailY\":27,\"indTailX\":25,\"isMust\":true,\"indHeadY\":25,\"useSkin\":true,\"indHeadX\":25,\"type\":\"matchStick\"}],\"lineColor\":\"0x00ff00ff\",\"zoom\":500,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\"},\"lineOpacity\":0.25},\"category\":5,\"ans_type\":0,\"timestamp\":1593754037133}";
        String elomelo_shobdo = "{\"author\":\"Rabib\",\"isReviewed\":true,\"restrictions\":\"নতুন কোনো অক্ষর যুক্ত করা যাবে না বা বাদ দেয়া যাবে না \",\"description\":\"এলোমেলো শব্দ বা jumbled word এ একটি ইংরেজি শব্দে অক্ষরগুলো এলোমেলো অবস্থানে দেয়া থাকবে। অক্ষরগুলোকে সঠিক অবস্থানে বসাতে পারাই এই সমস্যার মূল লক্ষ্য  \",\"sol_schema\":[{\"bgColor\":\"0xffffffff\",\"transX\":-2415.0082476418283,\"transY\":-2453.466864051347,\"elements\":[{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":14,\"indX\":23,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":24,\"skin\":18,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":31,\"indX\":23,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":20,\"indX\":24,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":18,\"indX\":27,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":29,\"indX\":26,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":33,\"indX\":29,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":25,\"skin\":32,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":16,\"indX\":28,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":27,\"skin\":38,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":27,\"indX\":26,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":28,\"indX\":25,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":23,\"skin\":32,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":31,\"indX\":26,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":33,\"indX\":28,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":24,\"skin\":18,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":16,\"indX\":25,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":27,\"skin\":18,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":31,\"indX\":23,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":25,\"skin\":35,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":18,\"indX\":24,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":28,\"skin\":20,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":27,\"indX\":27,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":26,\"skin\":18,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":16,\"indX\":25,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":21,\"indX\":23,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":14,\"indX\":24,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":27,\"skin\":22,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":28,\"skin\":27,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":26,\"skin\":24,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":29,\"skin\":20,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"indX\":29,\"skin\":18,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"}],\"indicatorColor\":\"0x32cd32ff\",\"isIndicator\":true,\"zoom\":1600,\"lineColor\":\"0x00ff00ff\",\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"lineOpacity\":0.25,\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true}}],\"title\":\"এলোমেলো শব্দ \",\"explanation\":\"৫টি শব্দ হলো যথাক্ক্রমে :  RESPECT   AGONY SECRET REVENGE HACKING\",\"difficulty\":5,\"series\":\"ইংরেজি \",\"prob_schema\":{\"bgColor\":\"0xffffffff\",\"transX\":-2415.0082476418283,\"elements\":[{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":23,\"skin\":14,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":23,\"skin\":18,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":31,\"indX\":25,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":25,\"skin\":20,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":18,\"indX\":27,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":28,\"skin\":29,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":33,\"indX\":24,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":32,\"indX\":26,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":29,\"skin\":16,\"useSkin\":true,\"indY\":23,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":38,\"indX\":24,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":26,\"skin\":27,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":28,\"indX\":27,\"useSkin\":true,\"indY\":25,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":32,\"indX\":23,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":24,\"skin\":31,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":25,\"skin\":33,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":26,\"skin\":18,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":27,\"skin\":16,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":28,\"skin\":18,\"useSkin\":true,\"indY\":27,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":23,\"skin\":31,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":35,\"indX\":24,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":25,\"skin\":18,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":20,\"indX\":26,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":27,\"skin\":27,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":18,\"indX\":28,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":23,\"skin\":16,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":21,\"indX\":24,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":14,\"indX\":27,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":28,\"skin\":22,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"indX\":29,\"skin\":27,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":24,\"indX\":25,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":20,\"indX\":26,\"useSkin\":true,\"indY\":31,\"type\":\"coin\"},{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"cantMove\":true,\"skin\":18,\"indX\":29,\"useSkin\":true,\"indY\":29,\"type\":\"coin\"}],\"transY\":-2453.466864051347,\"indicatorColor\":\"0x32cd32ff\",\"isIndicator\":true,\"lineColor\":\"0x00ff00ff\",\"zoom\":1600,\"defaultMatchStick\":{\"fillColor\":\"0x0090ffff\",\"isMust\":true,\"useSkin\":true},\"defaultCoin\":{\"outerColor\":\"0x0090ffff\",\"isMust\":true,\"innerColor\":\"0x004588ff\",\"skin\":7,\"useSkin\":true},\"lineOpacity\":0.25},\"statement\":\"প্রদত্ত সমস্যায় ৫টি ইংরেজি শব্দ এলোমেলোভাবে  দেয়া আছে। ৫টি শব্দের অক্ষরগুলো ড্র্যাগ এন্ড ড্রপ করে সঠিক অবস্থানে বসাও \",\"category\":6,\"ans_type\":0,\"timestamp\":1593701198758}";

        try {
            question = new JSONObject(brilliant);

            // Title and other Text
            String title = question.getString("title");
            getSupportActionBar().setTitle(title);
            statementText.setText(question.getString("statement"));
            restrictionText.setText(question.getString("restrictions"));

            // Tags
            addTag(question.getString("series"));
            addTag("Difficulty: "+question.getInt("difficulty")+"/10");
            addTag("by "+question.optString("author"));
            if (question.has("category")) addTag(Constant.CATEGORIES[question.getInt("category")]);

            // Problem Board
            JSONObject probSchema = question.getJSONObject("prob_schema");
            graph.setBoardContent(probSchema);

            // Setting Default Stick and Default Coin for add Pane
            JSONObject defaultStick = probSchema.getJSONObject("defaultMatchStick");
            Drawable stick = graph.setDefaultStick(defaultStick.optBoolean("useSkin"), defaultStick.getString("fillColor"));
            addStickToBoard.setImageDrawable(stick);

            JSONObject defaultCoin = probSchema.getJSONObject("defaultCoin");
            Bitmap coin = graph.setDefaultCoin(
                    defaultCoin.has("useSkin") && defaultCoin.getBoolean("useSkin"), // If not defined, false (by default)
                    defaultCoin.has("skin") ? defaultCoin.getInt("skin") : -1 , // If not defined, -1 (by default)
                    defaultCoin.getString("innerColor"),
                    defaultCoin.getString("outerColor")
            );

            if (coin != null)
                addCoinToBoard.setImageBitmap(coin);

            // Solution Type Setting
            answerType = question.getInt("ans_type");

            if (answerType == ANSWER_TEXT) {
                // Set AnswerLayout to have a EditText
                answerLayout.setAsText();

                // Retrieve Answer from JSON
                answer = question.getString("answer");

            } else if (answerType == ANSWER_MCQ) {
                JSONArray options = question.getJSONArray("options");

                // Set AnswerLayout to have multiple radio buttons
                answerLayout.setAsMCQ(options);

                // Set Answer String from answerIndex
                answer = options.getString(question.getInt("answer"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        addToBoard = findViewById(R.id.add_button);
        addCoinToBoard = findViewById(R.id.default_coin);
        addStickToBoard = findViewById(R.id.default_stick);

        graph = findViewById(R.id.graph);
        graphHolder = findViewById(R.id.graph_holder);
        scrollQuestion = findViewById(R.id.scroll_ques);
        answerLayout = findViewById(R.id.answer_container);
        questionTags = findViewById(R.id.tags);

        statementText = findViewById(R.id.question_text);
        restrictionText = findViewById(R.id.answer_constrain);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.add_fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.add_fab_close);
        addPaneOpen = AnimationUtils.loadAnimation(this, R.anim.add_fab_drawer_open);
        addPaneClose = AnimationUtils.loadAnimation(this, R.anim.add_fab_drawer_close);

        fabOpen.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                addCoinToBoard.startAnimation(addPaneOpen);
                addStickToBoard.startAnimation(addPaneOpen);
            }

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fabClose.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                addCoinToBoard.startAnimation(addPaneClose);
                addStickToBoard.startAnimation(addPaneClose);
            }

            @Override
            public void onAnimationEnd(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        addPaneOpen.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                addCoinToBoard.setVisibility(View.VISIBLE);
                addStickToBoard.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        addPaneClose.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                addCoinToBoard.setVisibility(View.INVISIBLE);
                addStickToBoard.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        addCoinToBoard.setOnTouchListener((v, event) -> {
            // Sending (x, y) from Screen LeftTop (not relative to View)
            graph.addCoin(event.getRawX(), event.getRawY());

            // Close The FAB
            toggleAddFAB(null);

            // Touch not Handled, Should be Handled by GraphView
            return false;
        });

        addStickToBoard.setOnTouchListener((v, event) -> {
            // Sending (x, y) from Screen LeftTop (not relative to View)
            graph.addStick(event.getRawX(), event.getRawY());

            // Close the FAB
            toggleAddFAB(null);

            // Touch not Handled, Should be Handled by GraphView
            return false;
        });
    }

    public void toggleAddFAB(View view) {
        if (fabIsOpen) {
            // If FAB is Open, Close now
            addToBoard.startAnimation(fabClose);
            if (view != addToBoard) {
                addCoinToBoard.setVisibility(View.INVISIBLE);
                addStickToBoard.setVisibility(View.INVISIBLE);
            }
        } else {
            // FAB is Closed, Open now
            addToBoard.startAnimation(fabOpen);
        }

        // FAB open status is reversed
        fabIsOpen = !fabIsOpen;
    }

    private void addTag(String tagText) {
        Chip tag = new Chip(this);
        tag.setText(tagText);

        // Add Chips to Chip Holder
        questionTags.addView(tag);
    }

    public void showDetails(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            // Set Series As Title, Description as Message in AlertDialog
            builder.setTitle(question.getString("series"))
                    .setMessage(question.getString("description"))
                    .setCancelable(true)
                    .show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void checkAnswer(View v) {
        AnswerDialog dialog = new AnswerDialog(this, question.optString("explanation"));
        switch (answerType) {
            // Text and MCQ Checking Process is Same
            case ANSWER_TEXT:
            case ANSWER_MCQ: {
                dialog.showDialog(answer.equals(answerLayout.getAsText()));
                break;
            }
            case ANSWER_BOARD: {
                // Match Board in New AsyncTask Thread
                new BoardMatcher(dialog, graph, question.optJSONArray("sol_schema")).execute();
                break;
            }
        }
    }
}