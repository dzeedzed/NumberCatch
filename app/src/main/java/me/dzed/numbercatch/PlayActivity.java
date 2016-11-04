package me.dzed.numbercatch;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
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
