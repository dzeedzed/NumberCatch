package me.dzed.numbercatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PracticeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        Intent aboutIntent = new Intent(PracticeActivity.this, GameActivity.class);
        startActivity(aboutIntent);
    }
}
