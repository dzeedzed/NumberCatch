package me.dzed.numbercatch;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class PromptLanguageActivity extends Activity {

    private ImageView flag;
    private TextView confirmMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int imageID = intent.getIntExtra("imageID", 0);
        String languageName = intent.getStringExtra("languageName");

        setContentView(R.layout.activity_prompt_language);
        flag = (ImageView) findViewById(R.id.flag);
        flag.setImageResource(imageID);

        confirmMessage = (TextView) findViewById(R.id.confirm_message);
        String newText = String.format(confirmMessage.getText().toString(),
                languageName);
        confirmMessage.setText(newText);

        AssetManager am = getApplicationContext().getAssets();

        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Lato-Thin.ttf"));

        confirmMessage.setTypeface(typeface);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }
}
