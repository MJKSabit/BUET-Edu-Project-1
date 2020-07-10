package github.mjksabit.bueteduproject.Answer;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class AnswerDialog {
    private final static String RIGHT_ANS = "CORRECT ANSWER";
    private final static String WRONG_ANS = "WRONG ANSWER";

    private final Context context;
    private final String explanation;

    public AnswerDialog(Context context, String explanation) {
        this.context = context;
        this.explanation = explanation;
    }

    public void showDialog(boolean correctAns) {
        String title = correctAns ? RIGHT_ANS : WRONG_ANS;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title)
                .setMessage(explanation)
                .setCancelable(true);

        new Handler(Looper.getMainLooper()).post(builder::show);
    }
}
