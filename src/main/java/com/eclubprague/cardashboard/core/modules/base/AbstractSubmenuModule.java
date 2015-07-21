package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleViewFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Base implementation of submenu module.
 * Submenu module leads to "submenu", meaning it launches another menu with a different set of modules.
 */
abstract public class AbstractSubmenuModule extends AbstractSimpleModule implements ISubmenuModule {
    private final List<IModule> submodules = new ArrayList<>();

    public AbstractSubmenuModule(StringResource titleResource, IconResource iconResource) {
        super(titleResource, iconResource);
    }

    public AbstractSubmenuModule(IModuleContext moduleContext, ISubmenuModule parent, StringResource titleResource, IconResource iconResource) {
        super(moduleContext, parent, titleResource, iconResource);
    }

    public AbstractSubmenuModule(IModuleContext moduleContext, ISubmenuModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource);
    }

    public void addSubmodules(IModule... modules) {
        submodules.addAll(Arrays.asList(modules));
    }

    public void addSubmodules(List<IModule> modules) {
        submodules.addAll(modules);
    }

    @Override
    public List<IModule> getSubmodules(IModuleContext moduleContext) {
        for (IModule m : submodules) {
            m.setModuleContext(moduleContext);
        }
        return submodules;
    }

    @Override
    public View createNewView(Context context, ViewGroup parent) {
        return ModuleViewFactory.createPassive(context, parent, getIcon(), getTitle());
    }

    @Override
    public ViewGroup createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(context, holderResourceId, holderParent, getIcon(), getTitle());
    }

    @Override
    public void onClickEvent(Context context) {
//        Log.d("SubmenuModule", "setting modules: " + getSubmodules().size());
        getModuleContext().setSubmenuModule(this);
    }
}
