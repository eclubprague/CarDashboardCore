package com.eclubprague.cardashboard.core.modules.base;

/**
 * Created by Michael on 12.08.2015.
 */
public interface IActivityStateChangeListener {
    void onPause();

    void onResume();

    void onStart();

    void onStop();
}
