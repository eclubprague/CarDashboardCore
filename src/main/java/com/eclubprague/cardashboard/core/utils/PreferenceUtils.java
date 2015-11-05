package com.eclubprague.cardashboard.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.eclubprague.cardashboard.core.application.GlobalDataProvider;

/**
 * Created by Michael on 24.09.2015.
 */
public class PreferenceUtils {
    private static final String SHARED_PREF_KEY = PreferenceUtils.class.getSimpleName() + "asd5a6dasd4";

    public static final String KEY_THEME = "theme";

    public static SharedPreferences getPreferences( Context context ) {
        return context.getSharedPreferences( SHARED_PREF_KEY, 0 );
    }

    public static SharedPreferences getPreferences() {
        Context context = GlobalDataProvider.getInstance().getContext();
        return getPreferences( context );
    }
}
