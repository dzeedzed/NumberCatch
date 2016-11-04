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
    private ImageButton japanese;
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
        norwegian = (ImageButton) findViewById(R.id.norwegian);


        norwegian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LanguageSelectActivity.this,  PromptLanguageActivity.class);

                Bundle b = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                    bitmap.eraseColor(Color.parseColor("#242424"));
                    b = ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, 0, 0).toBundle();
                }
                i.putExtra("imageID", R.id.norwegian);
                startActivity(i, b);

            }
        });

        english_uk = (ImageButton) findViewById(R.id.english_uk);
        english_uk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent settingsIntent = new Intent(LanguageSelectActivity.this, PromptLanguageActivity.class);
//                startActivity(settingsIntent);
//                overridePendingTransition(R.transition.ayylmao, R.transition.feridun);
                Intent i = new Intent(LanguageSelectActivity.this,  PromptLanguageActivity.class);

                Bundle b = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    //b = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(),
                    //                                         view.getHeight()).toBundle();
                    Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                    bitmap.eraseColor(Color.parseColor("#242424"));

                    b = ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, 0, 0).toBundle();
                }
                startActivity(i, b);
            }
        });

        japanese = (ImageButton) findViewById(R.id.japanese);
        japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent settingsIntent = new Intent(LanguageSelectActivity.this, PromptLanguageActivity.class);
//                startActivity(settingsIntent);
//                overridePendingTransition(R.transition.ayylmao, R.transition.feridun);
                Intent i = new Intent(LanguageSelectActivity.this,  PromptLanguageActivity.class);

                Bundle b = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    //b = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(),
                    //                                         view.getHeight()).toBundle();
                    Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                    bitmap.eraseColor(Color.parseColor("#242424"));

                    b = ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, 0, 0).toBundle();
                }
                i.putExtra("imageID", R.drawable.japanese);
                i.putExtra("languageName", "Japanese");
                startActivity(i, b);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }

}
