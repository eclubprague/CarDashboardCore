package com.eclubprague.cardashboard.core.application;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;

import com.eclubprague.cardashboard.core.modules.base.IModuleContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Michael on 24.08.2015.
 */
public enum GlobalDataProvider {
    INSTANCE;

    private IModuleContext moduleContext;
    private IntentService obdService;

    private Set<ModuleContextChangeListener> moduleContextChangeListeners = new HashSet<>();
//    private Set<OnModuleContextSetListener> onModuleSelectListeners;

//    GlobalDataProvider() {
//        onModuleSelectListeners = new HashSet<>();
//    }

    public static GlobalDataProvider getInstance() {
        return INSTANCE;
    }

    public IModuleContext getModuleContext() {
        return moduleContext;
    }

    public Activity getActivity() {
        return moduleContext.getActivity();
    }

    public Context getContext() {
        return moduleContext.getContext();
    }

    public void setModuleContext(IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
        for (ModuleContextChangeListener listener : moduleContextChangeListeners) {
            listener.onChange(moduleContext);
        }
//        if(this.moduleContext instanceof Activity){
//            setActivity(activity);
//        }
//        for (OnModuleContextSetListener listener : onModuleSelectListeners) {
//            listener.onModuleContextSet(moduleContext);
//        }
    }

    public void setObdService(IntentService obdService) {
        this.obdService = obdService;
    }

    public IntentService getObdService() {
        return obdService;
    }

    public void register(ModuleContextChangeListener listener) {
        moduleContextChangeListeners.add(listener);
    }

    public void unregister(ModuleContextChangeListener listener) {
        moduleContextChangeListeners.remove(listener);
    }

    //    public void addModuleContextListener(OnModuleContextSetListener listener){
//        onModuleSelectListeners.add(listener);
//    }

//    public static interface OnModuleContextSetListener {
//        void onModuleContextSet(IModuleContext moduleContext);
//    }

    public interface ModuleContextChangeListener {
        void onChange(IModuleContext newContext);
    }
}
