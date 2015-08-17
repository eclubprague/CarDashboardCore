package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IModuleListener;
import com.eclubprague.cardashboard.core.modules.base.IQuickMenuListener;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewUtils;

/**
 * Factory class creating new module views.
 *
 * Created by Michael on 14. 7. 2015.
 */
public class ModuleViewFactory {
    public static ModuleView createPassive(Context context, ViewGroup parent, IModuleListener listener, IModuleContext moduleContext, IconResource iconResource, StringResource titleResource) {
        ModuleView view = (ModuleView) LayoutInflater.from(context).inflate(R.layout.module_passive_icon_title, parent, false);
        return ModuleViewUtils.preparePassive(view, listener, moduleContext, iconResource, titleResource);
    }

    public static ViewWithHolder<ModuleView> createPassiveWithHolder(Context context, int holderResourceId, ViewGroup holderParent, IModuleListener listener, IModuleContext moduleContext, IconResource iconResource, StringResource titleResource) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(context).inflate(holderResourceId, holderParent, false);
        ModuleView view = createPassive(context, holder, listener, moduleContext, iconResource, titleResource);
        holder.addView(view);
        return new ViewWithHolder<>(view, holder);
    }

    public static ModuleActiveView createActive(Context context, ViewGroup parent, IModuleListener listener, IModuleContext moduleContext, IconResource iconResource, StringResource titleResource, StringResource unitResource) {
        ModuleActiveView view = (ModuleActiveView) LayoutInflater.from(context).inflate(R.layout.module_active_icon, parent, false);
        return ModuleViewUtils.prepareActive(view, listener, moduleContext, iconResource, titleResource, unitResource);
    }


    public static ViewWithHolder<ModuleActiveView> createActiveWithHolder(Context context, int holderResourceId, ViewGroup holderParent, IModuleListener listener, IModuleContext moduleContext, IconResource iconResource, StringResource titleResource, StringResource unitResource) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(context).inflate(holderResourceId, holderParent, false);
        ModuleActiveView view = createActive(context, holder, listener, moduleContext, iconResource, titleResource, unitResource);
        holder.addView(view);
        return new ViewWithHolder<>(view, holder);
    }

    public static View createQuickMenu(Context context, ViewGroup parent, IQuickMenuListener listener, IModuleContext moduleContext) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_quickmenu, parent, false);
        return ModuleViewUtils.prepareQuickMenu(view, listener, moduleContext);
    }

    public static ViewWithHolder<View> createQuickMenuWithHolder(Context context, int holderResourceId, ViewGroup holderParent, IQuickMenuListener listener, IModuleContext moduleContext) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(context).inflate(holderResourceId, holderParent, false);
        View view = createQuickMenu(context, holder, listener, moduleContext);
        holder.addView(view);
        return new ViewWithHolder<>(view, holder);
    }
}
