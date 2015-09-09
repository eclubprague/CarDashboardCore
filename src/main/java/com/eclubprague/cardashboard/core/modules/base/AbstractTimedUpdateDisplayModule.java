package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;
import android.util.Log;

import com.eclubprague.cardashboard.core.application.eventbus.FastEventBus;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.Event;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.EventReceiver;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Michael on 13.08.2015.
 */
public abstract class AbstractTimedUpdateDisplayModule<T extends Event> extends AbstractDisplayModule implements EventReceiver<T> {

    private Class<T> clazz;
    private String memory;
    private static final String TAG = AbstractTimedUpdateDisplayModule.class.getSimpleName();

    public AbstractTimedUpdateDisplayModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(moduleEnum, titleResource, iconResource, unitResource);
        init();
    }

    public AbstractTimedUpdateDisplayModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull StringResource unitResource) {
        super(moduleEnum, titleResource, iconResource, bgColorResource, fgColorResource, unitResource);
        init();
    }

    private void init() {
        clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
//        Log.d(getClass().getSimpleName(), "found class: " + clazz.getSimpleName());
    }

    @Override
    public void onPause(IModuleContext moduleContext) {
        super.onPause(moduleContext);
//        Log.d(TAG, "Pausing: " + getClass().getSimpleName());
        FastEventBus.getInstance().unregister(this, clazz);
    }

    @Override
    public void onResume(IModuleContext moduleContext) {
        super.onResume(moduleContext);
        FastEventBus.getInstance().register(this, clazz);
//        Log.d(TAG, "Resuming: " + getClass().getSimpleName());
    }

    @Override
    public void onStart(IModuleContext moduleContext) {
        super.onStart(moduleContext);
//        Log.d(TAG, "on start");
//        FastEventBus.getInstance().register(this, clazz);
//        Log.d(TAG, "Starting: " + getClass().getSimpleName());
    }

    @Override
    public void onStop(IModuleContext moduleContext) {
        super.onStop(moduleContext);
//        Log.d(TAG, "on stop");
//        FastEventBus.getInstance().unregister(this, clazz);
//        Log.d(TAG, "Stopping: " + getClass().getSimpleName());
    }

    @Override
    public void onEvent(ModuleEvent event, IModuleContext moduleContext) {
        super.onEvent(event, moduleContext);
        switch (event) {
            case DELETE:
                Log.d(TAG, "on delete");
                FastEventBus.getInstance().unregister(this, clazz);
        }
    }

    @Override
    public void onEventReceived(T event) {
        updateValue(memory = getUpdatedValue());
    }

    protected String getLastValue() {
        return memory;
    }

    protected void setLastValue(String lastValue) {
        this.memory = lastValue;
    }
}
