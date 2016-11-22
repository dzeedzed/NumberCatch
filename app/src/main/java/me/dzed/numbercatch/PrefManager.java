package me.dzed.numbercatch;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dzed on 05/05/16.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "numbercatch-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String LANGUAGE_LOCALE = "LanguageLocale";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean getIsFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setLanguageLocale(String languageLocale) {
        editor.putString(LANGUAGE_LOCALE, languageLocale);
        editor.commit();
    }

    public String getLanguageLocale() {
        return pref.getString(LANGUAGE_LOCALE, "en_US");
    }



}
