package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.MainThreadReciever;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleViewFactory;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Base implementation of display module.
 * Displays information. ATM it displays only String up to 4 characters.
 * Should launch simple menu on click (TODO)
 */
abstract public class AbstractDisplayModule extends AbstractSimpleModule implements MainThreadReciever<ModuleUpdateEvent> {
    private String value = null;
    private TextView valueView = null;
    private StringResource unitResource;


    public AbstractDisplayModule(StringResource titleResource, IconResource iconResource, StringResource unitResource) {
        super(titleResource, iconResource);
        this.unitResource = unitResource;
        EventBus.getDefault().register(this);
    }

    public AbstractDisplayModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource, StringResource unitResource) {
        super(moduleContext, parent, titleResource, iconResource);
        this.unitResource = unitResource;
        EventBus.getDefault().register(this);
    }

    public AbstractDisplayModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource, StringResource unitResource) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource);
        this.unitResource = unitResource;
        EventBus.getDefault().register(this);
    }

    public StringResource getUnit() {
        return unitResource;
    }

    public AbstractDisplayModule setUnitResource(StringResource unitResource) {
        this.unitResource = unitResource;
        return this;
    }

    protected void updateValue(String value) {
        this.value = value;
        if (valueView != null) {
            valueView.setText(value);
        }
    }

    @Override
    public View createNewView(Context context, ViewGroup parent) {
        View view = ModuleViewFactory.createActive(context, parent, this, getModuleContext(), getIcon(), getTitle(), getUnit());
        valueView = (TextView) view.findViewById(R.id.module_value);
        if (value != null) {
            updateValue(value);
        }
        return view;
    }

    @Override
    public ViewWithHolder createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder viewWithHolder = ModuleViewFactory.createActiveWithHolder(context, holderResourceId, holderParent, this, getModuleContext(), getIcon(), getTitle(), getUnit());
        valueView = (TextView) viewWithHolder.view.findViewById(R.id.module_value);
        if (value != null) {
            updateValue(value);
        }
        return viewWithHolder;
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEventMainThread(ModuleUpdateEvent event) {
        updateValue(event.getValue());
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AbstractDisplayModule{" +
                super.toString() + ", " +
                "value='" + value + '\'' +
                ", valueView=" + valueView +
                ", unitResource=" + unitResource +
                '}';
    }
}
