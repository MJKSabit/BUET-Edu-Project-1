package github.mjksabit.bueteduproject.Answer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnswerLayout extends LinearLayout {
    public static final int ANSWER_TEXT = 1;
    public static final int ANSWER_MCQ = 2;
    public static final int ANSWER_BOARD = 3;

    private int VIEW_ID;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


    private int answerType;
    private JSONObject options;

    public AnswerLayout(Context context) {
        super(context);
    }

    public AnswerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnswerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setAsText() {
        answerType = ANSWER_TEXT;
        VIEW_ID = View.generateViewId();
        EditText answerTextBox = new EditText(getContext());
        answerTextBox.setLayoutParams(params);
        answerTextBox.setHint("Answer Here");
        answerTextBox.setId(VIEW_ID);
        this.addView(answerTextBox, params);
    }

    public String getAsText() {
        if (answerType == ANSWER_TEXT) return ((EditText) findViewById(VIEW_ID)).getText().toString();
        if (answerType == ANSWER_MCQ) return ((RadioButton) findViewById(((RadioGroup) findViewById(VIEW_ID)).getCheckedRadioButtonId())).getText().toString();
        return null;
    }

    public void setAsMCQ(JSONArray options) throws JSONException {
        answerType = ANSWER_MCQ;
        VIEW_ID = View.generateViewId();
        RadioGroup group = new RadioGroup(getContext());
        group.setLayoutParams(params);
        group.setOrientation(RadioGroup.VERTICAL);
        group.setId(VIEW_ID);

        for (int i=0; i<options.length(); i++) {
            RadioButton button = new RadioButton(getContext());
            button.setText(options.getString(i));
            group.addView(button);
        }

        this.addView(group);
    }
}
