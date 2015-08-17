package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleActiveView;
import com.eclubprague.cardashboard.core.views.ModuleView;
import com.eclubprague.cardashboard.core.views.ModuleViewFactory;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Base implementation of display module.
 * Displays information. ATM it displays only String up to 4 characters.
 * Should launch simple menu on click (TODO)
 */
abstract public class AbstractDisplayModule extends AbstractSimpleModule {
    private String value = null;
    private ModuleActiveView view = null;
    private StringResource unitResource;


    public AbstractDisplayModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource);
        this.unitResource = unitResource;
    }

    public AbstractDisplayModule(@NonNull IModuleContext moduleContext, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(moduleContext, titleResource, iconResource);
        this.unitResource = unitResource;
    }

    public AbstractDisplayModule(@NonNull IModuleContext moduleContext, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull StringResource unitResource) {
        super(moduleContext, titleResource, iconResource, bgColorResource, fgColorResource);
        this.unitResource = unitResource;
    }

    public StringResource getUnit() {
        return unitResource;
    }

    public AbstractDisplayModule setUnitResource(StringResource unitResource) {
        this.unitResource = unitResource;
        return this;
    }

    public void updateValue(String value) {
        this.value = value;
        if (value == null || view == null) {
            return;
        }
        view.setValue(value);
    }

    @Override
    public ModuleView createNewView(Context context, ViewGroup parent) {
        view = ModuleViewFactory.createActive(context, parent, this, getModuleContext(), getIcon(), getTitle(), getUnit());
        updateValue(value);
        return view;
    }

    @Override
    public ViewWithHolder<ModuleView> createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder<ModuleActiveView> viewWithHolder = ModuleViewFactory.createActiveWithHolder(context, holderResourceId, holderParent, this, getModuleContext(), getIcon(), getTitle(), getUnit());
        view = viewWithHolder.view;
        updateValue(value);
        return new ViewWithHolder<ModuleView>(viewWithHolder.view, viewWithHolder.holder);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AbstractDisplayModule{" +
                super.toString() + ", " +
                "value='" + value + '\'' +
                ", valueView=" + view +
                ", unitResource=" + unitResource +
                '}';
    }
}
