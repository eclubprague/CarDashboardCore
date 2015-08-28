package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleActiveView;
import com.eclubprague.cardashboard.core.views.ModuleView;

import java.util.List;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Base implementation of display module.
 * Displays information. ATM it displays only String up to 4 characters.
 * Should launch simple menu on click (TODO)
 */
abstract public class AbstractDisplayModule extends AbstractSimpleModule {
    private String value = null;
    private StringResource unitResource;


    public AbstractDisplayModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource);
        this.unitResource = unitResource;
    }

    public AbstractDisplayModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, bgColorResource, fgColorResource);
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
        IModuleContext moduleContext = GlobalApplication.getInstance().getModuleContext();
        this.value = value;
        if (value == null || getViews(moduleContext) == null) {
            return;
        } else {
            List<ModuleView> moduleViewList = getViews(moduleContext);
            for (ModuleView moduleView : moduleViewList) {
                ModuleActiveView moduleActiveView = (ModuleActiveView) moduleView;
                moduleActiveView.setValue(value);
            }
        }
    }

    private void firstUpdate(String value, ModuleActiveView view) {
        IModuleContext moduleContext = GlobalApplication.getInstance().getModuleContext();
        if (value == null || getViews(moduleContext) == null) {
            return;
        }
        this.value = value;
        view.setValue(value);
    }

    @Override
    public ModuleView createNewView(IModuleContext moduleContext, ViewGroup parent) {
        ModuleActiveView view = ModuleViewFactory.createActive(moduleContext, parent, this, getIcon(), getTitle(), getUnit());
        firstUpdate(value, view);
        return view;
    }

    @Override
    public ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder<ModuleActiveView> viewWithHolder = ModuleViewFactory.createActiveWithHolder(moduleContext, holderResourceId, holderParent, this, getIcon(), getTitle(), getUnit());
        ModuleActiveView view = viewWithHolder.view;
        firstUpdate(value, view);
        return new ViewWithHolder<ModuleView>(viewWithHolder.view, viewWithHolder.holder);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                super.toString() + ", " +
                "value='" + value + '\'' +
                ", unitResource=" + unitResource +
                '}';
    }
}
