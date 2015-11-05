package com.eclubprague.cardashboard.core.utils;

import android.content.SharedPreferences;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;

/**
 * Created by Michael on 24.09.2015.
 */
public class ThemeUtils {

    public static void changeTheme(Theme newTheme, IModuleContext context){
        SharedPreferences.Editor editor = PreferenceUtils.getPreferences( context.getContext() ).edit();
        editor.putString( PreferenceUtils.KEY_THEME, newTheme.name() );
        editor.commit();
        context.restartApplication();
    }

    public static Theme getCurrentTheme(IModuleContext context){
        Theme theme;
        try {
            theme = Theme.valueOf( PreferenceUtils.getPreferences( context.getContext() ).getString( PreferenceUtils.KEY_THEME, Theme.DARK.name() ) );
        }catch(IllegalArgumentException e){
            // TODO report error
            theme = Theme.DARK;
        }
        return theme;
    }

    public static enum Theme {

        DARK(R.style.DarkTheme), LIGHT(R.style.LightTheme);

        private final int resId;

        Theme( int resId ) {
            this.resId = resId;
        }

        public int getResId(){
            return resId;
        }
    }
}
