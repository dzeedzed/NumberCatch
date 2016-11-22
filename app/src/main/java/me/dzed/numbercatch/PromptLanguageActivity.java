package me.dzed.numbercatch;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class PromptLanguageActivity extends Activity {

    private TextToSpeech textToSpeech;
    private PrefManager prefManager;
    private ImageView flag;
    private TextView confirmMessage;
    private ImageButton yes;
    private ImageButton no;
    private boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        textToSpeech = MainActivity.textToSpeech;
        ready = MainActivity.ready;

        // Retrieve data from ImageButton
        Intent intent = getIntent();
        int imageID = intent.getIntExtra(Constants.IMAGE_ID_STRING, 0);
        String languageName = intent.getStringExtra(Constants.LANGUAGE_NAME);
        final String languageLocale = intent.getStringExtra(Constants.LANGUAGE_LOCALE);

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

        yes = (ImageButton) findViewById(R.id.yes_button);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setLanguageLocale(languageLocale);
                // Restart app
                Intent i = getBaseContext().getPackageManager().
                        getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });

        no = (ImageButton) findViewById(R.id.no_button);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }

}
