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

    public AbstractTimedUpdateDisplayModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, unitResource);
        init();
    }

    public AbstractTimedUpdateDisplayModule(@NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(parent, titleResource, iconResource, unitResource);
        init();
    }

    public AbstractTimedUpdateDisplayModule(@NonNull IModuleContext moduleContext, @NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(moduleContext, parent, titleResource, iconResource, unitResource);
        moduleContext.addListener(this);
        init();
    }

    public AbstractTimedUpdateDisplayModule(@NonNull IModuleContext moduleContext, @NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull StringResource unitResource) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource, unitResource);
        moduleContext.addListener(this);
        init();
    }

    private void init() {
        clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
//        Log.d(getClass().getSimpleName(), "found class: " + clazz.getSimpleName());
        FastEventBus.getInstance().register(this, clazz);
    }

    @Override
    public IModule setModuleContext(@NonNull IModuleContext moduleContext) {
        super.setModuleContext(moduleContext);
        moduleContext.addListener(this);
        return this;
    }

    @Override
    public void onDelete(IModuleContext moduleContext) {
        moduleContext.removeListener(this);
        super.onDelete(moduleContext);
    }

    @Override
    public void onPause() {
        super.onPause();
        FastEventBus.getInstance().unregister(this, clazz);
    }

    @Override
    public void onResume() {
        super.onResume();
        FastEventBus.getInstance().register(this, clazz);
    }

    @Override
    public void onStart() {
        super.onStart();
        FastEventBus.getInstance().register(this, clazz);
    }

    @Override
    public void onStop() {
        super.onStop();
//        Log.d(getClass().getSimpleName(), "stopping");
        FastEventBus.getInstance().unregister(this, clazz);
    }
}
