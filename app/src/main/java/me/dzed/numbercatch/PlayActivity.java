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
    private TextView stressFreeDesc;

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

        stressFreeDesc = (TextView) findViewById(R.id.stress_free_desc);
        AssetManager am = getApplicationContext().getAssets();

        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Lato-Thin.ttf"));

        stressFreeDesc.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }
}
