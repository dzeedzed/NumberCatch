package me.dzed.numbercatch;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LanguageSelectActivity extends AppCompatActivity {

    private ImageButton english_us;
    private ImageButton english_uk;
    private ImageButton norwegian;
    private ImageButton danish;
    private ImageButton swedish;
    private ImageButton finnish;
    private ImageButton japanese;
    private ImageButton french;
    private ImageButton chinese;
    private ImageButton dutch;
    private ImageButton russian;
    private ImageButton german;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        context = getApplicationContext();

        norwegian = initLanguageButton(R.id.norwegian, R.drawable.norwegian,
                Constants.NORWEGIAN_NAME, Constants.NORWEGIAN_LOCALE);
        danish = initLanguageButton(R.id.danish, R.drawable.danish,
                Constants.DANISH_NAME, Constants.DANISH_LOCALE);
        swedish = initLanguageButton(R.id.swedish, R.drawable.swedish,
                Constants.SWEDISH_NAME, Constants.SWEDISH_LOCALE);
        english_uk = initLanguageButton(R.id.english_uk, R.drawable.english_uk,
                Constants.ENGLISH_UK_NAME, Constants.ENGLISH_UK_LOCALE);
        english_us = initLanguageButton(R.id.english_us, R.drawable.english_us,
                Constants.ENGLISH_US_NAME, Constants.ENGLISH_US_LOCALE);
        japanese = initLanguageButton(R.id.japanese, R.drawable.japanese,
                Constants.JAPANESE_NAME, Constants.JAPANESE_LOCALE);
        finnish = initLanguageButton(R.id.finnish, R.drawable.finnish,
                Constants.FINNISH_NAME, Constants.FINNISH_LOCALE);
        french = initLanguageButton(R.id.french, R.drawable.french,
                Constants.FRENCH_NAME, Constants.FRENCH_LOCALE);
        chinese = initLanguageButton(R.id.chinese, R.drawable.chinese,
                Constants.CHINESE_NAME, Constants.CHINESE_LOCALE);
        dutch = initLanguageButton(R.id.dutch, R.drawable.dutch,
                Constants.DUTCH_NAME, Constants.DUTCH_LOCALE);
        russian = initLanguageButton(R.id.russian, R.drawable.russian,
                Constants.RUSSIAN_NAME, Constants.RUSSIAN_LOCALE);
        german = initLanguageButton(R.id.german, R.drawable.german,
                Constants.GERMAN_NAME, Constants.GERMAN_LOCALE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }

    public ImageButton initLanguageButton(int buttonID, final int imageID, final String languageName,
                                          final String languageLocale) {
        ImageButton result = (ImageButton) findViewById(buttonID);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(LanguageSelectActivity.this, PromptLanguageActivity.class);
            Bundle b = null;

            // Zoom in animation when clicked
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                        Bitmap.Config.ARGB_8888);
                bitmap.eraseColor(Color.parseColor(Constants.COLOR_HEX_GREY));
                b = ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, 0, 0).toBundle();
            }

            // Send image, name, and locale data to PromptLanguageActivity
            i.putExtra(Constants.IMAGE_ID_STRING, imageID);
            i.putExtra(Constants.LANGUAGE_NAME, languageName);
            i.putExtra(Constants.LANGUAGE_LOCALE, languageLocale);
            startActivity(i, b);
            }
        });

        return result;
    }
}
