package me.dzed.numbercatch;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private PrefManager prefManager;
    private Button button;
    private ImageButton play;
    private ImageButton about;
    private ImageButton settings;
    private ImageButton selectLanguage;
    private ImageButton share;
    private ImageButton statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
        }

        setContentView(R.layout.activity_main);

//        button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
//                intent.putExtra("displaySlides", true);
//                startActivity(intent);
//                finish();
//                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
//            }
//        });

        play = (ImageButton) findViewById(R.id.play_button);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent languageSelect = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(languageSelect);
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });

        selectLanguage = (ImageButton) findViewById(R.id.language_button);
        selectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent languageSelect = new Intent(MainActivity.this, LanguageSelectActivity.class);
                startActivity(languageSelect);
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });

        settings = (ImageButton) findViewById(R.id.settings_button);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });

        share = (ImageButton) findViewById(R.id.share_button);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "PM ME YOUR DANK MEEMS";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Gemalto NV");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via..."));
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });



        about = (ImageButton) findViewById(R.id.about_button);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showAbout();
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
            }
        });
    }

    private void showAbout() {
        // Inflate the about message contents
        View messageView = getLayoutInflater().inflate(R.layout.about, null, false);

        setClickableHTMLTextView(messageView, R.id.website,
                "Visit my <a href=\"http://dzed.me\">website</a>!");
        setClickableHTMLTextView(messageView, R.id.google_play,
                "Check out my other <a href=\"https://play.google.com/store/apps/developer?id=Dadi+Zhang\">apps</a>!");
        setClickableHTMLTextView(messageView, R.id.icon_credits_1,
                "<a href=\"https://www.iconfinder.com/icons/80702/" +
                        "eight_gestureworks_number_stroke_icon\">Number 8 Icon</a>");
        setClickableHTMLTextView(messageView, R.id.icon_credits_2,
                "<a href=\"https://icons8.com/web-app/4427/Google-Translate\">Google Translate Icon</a>");


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_languages);
        builder.setTitle(R.string.app_name);
        builder.setView(messageView);
        builder.create();
        builder.show();
    }

    private void setClickableHTMLTextView(View messageView, int id, String linkText) {
        TextView temp = (TextView) messageView.findViewById(id);
        Spanned resultHtml;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            resultHtml = Html.fromHtml(linkText, Html.FROM_HTML_MODE_LEGACY);
        } else {
            resultHtml = Html.fromHtml(linkText);
        }
        temp.setText(resultHtml);
        temp.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
