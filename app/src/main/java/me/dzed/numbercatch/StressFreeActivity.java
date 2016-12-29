package me.dzed.numbercatch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class StressFreeActivity extends Activity {

    public Context context;

    private NumberGenerator numberGenerator;
    private TextToSpeech textToSpeech;

    private TextView type;
    private EditText delayInput;
    private EditText timerInput;
    private Button button;
    private Button pause;
    private long delay = 4000; // milliseconds
    private Handler handler;
    private boolean ready;

    private long timer = 30000; // milliseconds
    private boolean timerSet = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_free);

        context = getApplicationContext();

        numberGenerator = new NumberGenerator();
        textToSpeech = MainActivity.textToSpeech;
        ready = MainActivity.ready;

        type = (TextView) findViewById(R.id.stress_free_type);
        Util.setTextViewFont(context, type, Constants.FONT_PATH_LATO_THIN);


        delayInput = (EditText) findViewById(R.id.delayInput);






        final Runnable ayylmao = new Runnable() {
            @Override
            public void run() {
                Random rand = new Random();
                int ayy = rand.nextInt(100);
                speakOut(Integer.toString(ayy));
            }
        };



        button = (Button) findViewById(R.id.button1231312);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Retrieve all numbers/attributes
                double delayDouble = Double.parseDouble(delayInput.getText().toString()) * (double) 1000.0;
                delay = (long) delayDouble;
                System.out.println("FERIDUN " + delay);

                textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                    @Override
                    public void onStart(String s) {

                    }

                    @Override
                    public void onDone(String s) {
                        handler.postDelayed(ayylmao, delay);
                    }

                    @Override
                    public void onError(String s) {

                    }
                });

                handler = new Handler();
                handler.postDelayed(ayylmao, delay);

                if (timerSet) {
                    // TIMER
                    timerInput = (EditText) findViewById(R.id.timerInput);
                    double timerDouble = Double.parseDouble(timerInput.getText().toString()) * (double) 1000.0;
                    timer = (long) timerDouble;

                    Handler timerHandler = new Handler();
                    timerHandler.postDelayed(new Runnable() {

                        public void run() {
                            handler.removeCallbacks(ayylmao);
                            textToSpeech.stop();
                        }
                    }, timer);
                }
            }
        });

        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(ayylmao);
                textToSpeech.stop();
            }
        });
        
        printOutSupportedLanguages();
    }

    private void printOutSupportedLanguages()  {
        // Supported Languages
        Set<Locale> supportedLanguages = textToSpeech.getAvailableLanguages();
        if(supportedLanguages!= null) {
            for (Locale lang : supportedLanguages) {
                Log.e("textToSpeech", "Supported Language: " + lang);
            }
        }
    }

    private Locale getUserSelectedLanguage() {
        return Locale.CANADA;
    }

    private void setTextToSpeechLanguage() {
        Locale language = this.getUserSelectedLanguage();
        if (language == null) {
            this.ready = false;
            Toast.makeText(this, "Not language selected", Toast.LENGTH_SHORT).show();
            return;
        }
        int result = textToSpeech.setLanguage(language);
        if (result == TextToSpeech.LANG_MISSING_DATA) {
            this.ready = false;
            Toast.makeText(this, "Missing language data", Toast.LENGTH_SHORT).show();
        } else if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
            this.ready = false;
            Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
        } else {
            this.ready = true;
            Locale currentLanguage = textToSpeech.getVoice().getLocale();
            Toast.makeText(this, "Language " + currentLanguage, Toast.LENGTH_SHORT).show();
        }
    }

    private void speakOut(String toSpeak) {
        if (!ready) {
            Toast.makeText(this, "Text to Speech not ready", Toast.LENGTH_LONG).show();
            return;
        }
        // Text to Speak
        Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show();

        // A random String (Unique ID).
        String utteranceId = UUID.randomUUID().toString();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_ADD, null, utteranceId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (handler == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        textToSpeech.stop();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (textToSpeech != null) {
//            textToSpeech.shutdown();
//        }
//    }
}
