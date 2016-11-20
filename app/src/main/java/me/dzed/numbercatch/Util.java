package me.dzed.numbercatch;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by dzed on 19/11/16.
 */
public class Util {

    public static void setTextViewFont(Context context, TextView textView,
                                       String fontPath) {
        // Sets the font face for a TextView
        AssetManager am = context.getAssets();
        Typeface typeface = Typeface.createFromAsset(am, fontPath);
        textView.setTypeface(typeface);
    }

}
