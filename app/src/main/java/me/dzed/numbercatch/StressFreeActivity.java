package me.dzed.numbercatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class StressFreeActivity extends Activity {

    private NumberGenerator numberGenerator;
    private TextToSpeech textToSpeech;
    private boolean ready;
    private Button button;
    private Button pause;
    private int delay = 4000; //milliseconds
    private Handler handler;
    private Runnable runnable = new Runnable(){
        public void run(){
            //do something
            speakOut("Hello world! Donald Trump is our glorious leader!");
            handler.postDelayed(this, delay);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_free);

        numberGenerator = new NumberGenerator();
        textToSpeech = MainActivity.textToSpeech;
        ready = MainActivity.ready;

        button = (Button) findViewById(R.id.button1231312);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler = new Handler();




                handler.postDelayed(runnable, delay);
            }
        });

        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String utteranceId = UUID.randomUUID().toString();
                textToSpeech.speak("", TextToSpeech.QUEUE_FLUSH, null, utteranceId);
                handler.removeCallbacks(runnable);
                textToSpeech.shutdown();
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

//    private void startSpeak(final String data, final float rate) {
//
//        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int initStatus) {
//
//                if (initStatus == TextToSpeech.SUCCESS) {
//                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
//                    if(textToSpeech.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE) {
//                        textToSpeech.setLanguage(Locale.US);
//                    }
//
//                    textToSpeech.setPitch(1.3f);
//                    textToSpeech.setSpeechRate(rate);
//
//
//
//                    // start speaking
//                    synchronized (this) {
////                        textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
//                        if (result == TextToSpeech.LANG_MISSING_DATA
//                                || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                        {
//                            Toast.makeText(getApplicationContext(),
//                                    "Please Set your Language to English US.", Toast.LENGTH_LONG ).show();
//                        }
//                        else
//                        {
//                            textToSpeech.setLanguage(Locale.ENGLISH);
//                            textToSpeech.speak(data, TextToSpeech.QUEUE_ADD, null, "A");
//                        }
//                    }
//                }
//                else if (initStatus == TextToSpeech.ERROR) {
//                    Toast.makeText(getApplicationContext(), "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }

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
            return;
        } else if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
            this.ready = false;
            Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
            return;
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
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
    }
}
