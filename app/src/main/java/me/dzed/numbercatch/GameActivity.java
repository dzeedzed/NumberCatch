package me.dzed.numbercatch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends Activity {

    private TextView score;
    private TextView answer;
    private NumberGenerator numberGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numberGenerator = new NumberGenerator();

        score = (TextView) findViewById(R.id.score);
        answer = (TextView) findViewById(R.id.answer);
        Util.setTextViewFont(getApplicationContext(), score, Constants.FONT_PATH_JOSEFIN_REGULAR);
        Util.setTextViewFont(getApplicationContext(), answer, Constants.FONT_PATH_JOSEFIN_REGULAR);
    }
}
