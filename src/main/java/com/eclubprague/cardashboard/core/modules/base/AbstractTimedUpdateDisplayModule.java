package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.model.eventbus.FastEventBus;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.Event;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.MainThreadReceiver;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Michael on 13.08.2015.
 */
public abstract class AbstractTimedUpdateDisplayModule<T extends Event> extends AbstractDisplayModule implements MainThreadReceiver<T> {

    private Class<T> clazz;
    private static final String TAG = AbstractTimedUpdateDisplayModule.class.getSimpleName();

    public AbstractTimedUpdateDisplayModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, unitResource);
        init();
    }

    public AbstractTimedUpdateDisplayModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, bgColorResource, fgColorResource, unitResource);
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
        FastEventBus.getInstance().register(this, clazz);
//        Log.d(TAG, "Starting: " + getClass().getSimpleName());
    }

    @Override
    public void onStop(IModuleContext moduleContext) {
        super.onStop(moduleContext);
        FastEventBus.getInstance().unregister(this, clazz);
//        Log.d(TAG, "Stopping: " + getClass().getSimpleName());
    }
}
