package com.eclubprague.cardashboard.core.modules.base;

/**
 * Created by Michael on 12.08.2015.
 */
public interface IActivityStateChangeListener {
    void onPause(IModuleContext moduleContext);

    void onResume(IModuleContext moduleContext);

    void onStart(IModuleContext moduleContext);

    void onStop(IModuleContext moduleContext);

    void onDestroy(IModuleContext moduleContext);
}
