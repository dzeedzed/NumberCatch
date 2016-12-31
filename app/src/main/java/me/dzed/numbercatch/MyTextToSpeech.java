package me.dzed.numbercatch;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by dzed on 30/12/16.
 */
public class MyTextToSpeech {
    private TextToSpeech textToSpeech;
    private boolean ready = false;

    public MyTextToSpeech(Context context, final Locale languageLocale) {
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                final Locale locale = languageLocale;
                Log.e("textToSpeech", "TextToSpeech.OnInitListener.onInit...");
                setTextToSpeechLanguage(locale);
            }
        });
    }

    public void setTextToSpeechLanguage(Locale language) {

        System.out.println(language);

        if (language == null) {
            ready = false;
            return;
        }
        int result = textToSpeech.setLanguage(language);
        if (result == TextToSpeech.LANG_MISSING_DATA) {
            ready = false;
        } else if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
            ready = false;
        } else {
            ready = true;
        }
    }

    public void speakOut(String toSpeak) {
        if (!ready) {
            return;
        }

        // A random String (Unique ID).
        String utteranceId = UUID.randomUUID().toString();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_ADD, null, utteranceId);
    }

    public void setOnUtteranceProgressListener(UtteranceProgressListener listener) {
        textToSpeech.setOnUtteranceProgressListener(listener);
    }

    public void stop() {
        textToSpeech.stop();
    }
}
