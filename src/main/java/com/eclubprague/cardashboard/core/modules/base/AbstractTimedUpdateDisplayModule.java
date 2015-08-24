package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.model.eventbus.FastEventBus;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.Event;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.MainThreadReceiver;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleView;

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

    public AbstractTimedUpdateDisplayModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, bgColorResource, fgColorResource, unitResource);
        init();
    }

    private void init() {
        clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
//        Log.d(getClass().getSimpleName(), "found class: " + clazz.getSimpleName());
    }

    @Override
    public ModuleView createView(IModuleContext moduleContext, ViewGroup parent) {
        moduleContext.addListener(this);
        return super.createView(moduleContext, parent);
    }

    @Override
    public ViewWithHolder<ModuleView> createViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        moduleContext.addListener(this);
        return super.createViewWithHolder(moduleContext, holderResourceId, holderParent);
    }

    @Override
    public void onEvent(ModuleEvent event, IModuleContext moduleContext) {
        super.onEvent(event, moduleContext);
        if (event.equals(ModuleEvent.DELETE)) {
            moduleContext.removeListener(this);
        }
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
