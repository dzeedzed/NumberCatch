package me.dzed.numbercatch;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class PlayActivity extends Activity {

    private ImageButton stressFree;
    private ImageButton practice;
    private TextView stressFreeDesc;
    private TextView practiceDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        stressFree = (ImageButton) findViewById(R.id.stress_free_button);
        stressFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(PlayActivity.this, StressFreeActivity.class);
                startActivity(aboutIntent);
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });

        practice = (ImageButton) findViewById(R.id.practice_button);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(PlayActivity.this, PracticeActivity.class);
                startActivity(aboutIntent);
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });

        stressFreeDesc = (TextView) findViewById(R.id.stress_free_desc);
        practiceDesc = (TextView) findViewById(R.id.practice_desc);
        Util.setTextViewFont(getApplicationContext(), stressFreeDesc, Constants.FONT_PATH_LATO_THIN);
        Util.setTextViewFont(getApplicationContext(), practiceDesc, Constants.FONT_PATH_LATO_THIN);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }
}
