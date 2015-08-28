package com.eclubprague.cardashboard.core.application;

import android.app.IntentService;
import android.content.Context;

import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.obd.DummyGatewayService;

/**
 * Created by Michael on 24.08.2015.
 */
public enum GlobalApplication {
    INSTANCE;




    private IModuleContext moduleContext;
    private IntentService obdService;
//    private Set<OnModuleContextSetListener> onModuleSelectListeners;

//    GlobalApplication() {
//        onModuleSelectListeners = new HashSet<>();
//    }

    public static GlobalApplication getInstance() {
        return INSTANCE;
    }

    public IModuleContext getModuleContext() {
        return moduleContext;
    }

    public Context getContext() {
        if (moduleContext == null) {
            return null;
        }
        return moduleContext.getContext();
    }

    public void setModuleContext(IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
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

    //    public void addModuleContextListener(OnModuleContextSetListener listener){
//        onModuleSelectListeners.add(listener);
//    }

//    public static interface OnModuleContextSetListener {
//        void onModuleContextSet(IModuleContext moduleContext);
//    }
}
