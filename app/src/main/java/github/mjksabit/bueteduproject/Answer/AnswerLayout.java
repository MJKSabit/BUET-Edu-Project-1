package github.mjksabit.bueteduproject.Answer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;

import static github.mjksabit.bueteduproject.Utils.Constant.ANSWER_MCQ;
import static github.mjksabit.bueteduproject.Utils.Constant.ANSWER_TEXT;

public class AnswerLayout extends LinearLayout {

    // View ID of EditText, Radio Button
    private int VIEW_ID;

    // Default Layout Parameters
    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    // Defined AnswerType
    private int answerType;

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

    // Set AnswerLayout to Have a TextEdit
    public void setAsText() {
        answerType = ANSWER_TEXT;

        // Generate View ID
        VIEW_ID = View.generateViewId();

        // Make Input TextField
        EditText answerTextBox = new EditText(getContext());
        answerTextBox.setLayoutParams(params);
        answerTextBox.setHint("Answer Here");
        answerTextBox.setId(VIEW_ID);

        // Add to Answer Layout
        this.addView(answerTextBox, params);
    }

    public String getAsText() {
        // Return TextField Text
        if (answerType == ANSWER_TEXT) return ((EditText) findViewById(VIEW_ID)).getText().toString();
        if (answerType == ANSWER_MCQ) {
            int selectedId = ((RadioGroup) findViewById(VIEW_ID)).getCheckedRadioButtonId();

            // Check if Any Radio Button is Selected
            if (selectedId != View.NO_ID)
                return ((RadioButton) findViewById(selectedId)).getText().toString();
        }
        return null;
    }

    public void setAsMCQ(JSONArray options) throws JSONException {
        answerType = ANSWER_MCQ;

        // Generate View ID
        VIEW_ID = View.generateViewId();

        // Create RadioGroup To Hold Radio Buttons
        RadioGroup group = new RadioGroup(getContext());
        group.setLayoutParams(params);
        group.setOrientation(RadioGroup.VERTICAL);
        group.setId(VIEW_ID);

        // Add Radio Buttons from Options
        for (int i=0; i<options.length(); i++) {
            RadioButton button = new RadioButton(getContext());
            button.setText(options.getString(i));
            group.addView(button);
        }

        this.addView(group);
    }
}
