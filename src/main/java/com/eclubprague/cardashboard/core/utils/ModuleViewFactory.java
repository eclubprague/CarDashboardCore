package com.eclubprague.cardashboard.core.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IModuleListener;
import com.eclubprague.cardashboard.core.modules.base.IQuickMenuListener;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.views.ModuleActiveView;
import com.eclubprague.cardashboard.core.views.ModuleView;
import com.eclubprague.cardashboard.core.views.QuickMenuView;

/**
 * Factory class creating new module views.
 * <p/>
 * Created by Michael on 14. 7. 2015.
 */
public class ModuleViewFactory {
    public static ModuleView createPassive(IModuleContext moduleContext, ViewGroup parent, IModuleListener listener, IconResource iconResource, StringResource titleResource) {
        ModuleView view = (ModuleView) LayoutInflater.from(moduleContext.getContext()).inflate(R.layout.module_passive_icon_title, parent, false);
        return ModuleViewUtils.preparePassive(view, listener, moduleContext, iconResource, titleResource);
    }

    public static ViewWithHolder<ModuleView> createPassiveWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent, IModuleListener listener, IconResource iconResource, StringResource titleResource) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(moduleContext.getContext()).inflate(holderResourceId, holderParent, false);
        ModuleView view = createPassive(moduleContext, holder, listener, iconResource, titleResource);
        holder.addView(view);
        return new ViewWithHolder<>(view, holder);
    }

    public static ModuleActiveView createActive(IModuleContext moduleContext, ViewGroup parent, IModuleListener listener, IconResource iconResource, StringResource titleResource, StringResource unitResource) {
        ModuleActiveView view = (ModuleActiveView) LayoutInflater.from(moduleContext.getContext()).inflate(R.layout.module_active_icon, parent, false);
        return ModuleViewUtils.prepareActive(view, listener, moduleContext, iconResource, titleResource, unitResource);
    }


    public static ViewWithHolder<ModuleActiveView> createActiveWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent, IModuleListener listener, IconResource iconResource, StringResource titleResource, StringResource unitResource) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(moduleContext.getContext()).inflate(holderResourceId, holderParent, false);
        ModuleActiveView view = createActive(moduleContext, holder, listener, iconResource, titleResource, unitResource);
        holder.addView(view);
        return new ViewWithHolder<>(view, holder);
    }

    public static View createQuickMenu(ModuleView moduleView, IModuleContext moduleContext, ViewGroup parent, IQuickMenuListener listener) {
        QuickMenuView view = (QuickMenuView) LayoutInflater.from(moduleContext.getContext()).inflate(R.layout.module_quickmenu, parent, false);
        return ModuleViewUtils.prepareQuickMenu(moduleView, view, listener, moduleContext);
    }

    public static ViewWithHolder<View> createQuickMenuWithHolder(ModuleView moduleView, IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent, IQuickMenuListener listener) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(moduleContext.getContext()).inflate(holderResourceId, holderParent, false);
        View view = createQuickMenu(moduleView, moduleContext, holder, listener);
        holder.addView(view);
        return new ViewWithHolder<>(view, holder);
    }
}
