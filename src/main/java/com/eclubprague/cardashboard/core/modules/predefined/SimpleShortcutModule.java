package com.eclubprague.cardashboard.core.modules.predefined;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleView;

/**
 * Created by Michael on 02.09.2015.
 */
public class SimpleShortcutModule extends AbstractShortcutModule {

//    public SimpleShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource) {
//        super(moduleEnum, titleResource, iconResource);
//    }
//
//    public SimpleShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
//        super(moduleEnum, titleResource, iconResource, bgColorResource, fgColorResource);
//    }

    public SimpleShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, StringResource errorMessage, Intent intent) {
        super(moduleEnum, titleResource, iconResource, bgColorResource, fgColorResource, errorMessage, intent);
    }

    public SimpleShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, StringResource errorMessage, Intent intent) {
        super(moduleEnum, titleResource, iconResource, errorMessage, intent);
    }

    public SimpleShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, Intent intent) {
        super(moduleEnum, titleResource, iconResource, intent);
    }

    @Override
    public ModuleView createNewView(IModuleContext moduleContext, ViewGroup parent) {
        ModuleView v = super.createNewView(moduleContext, parent);
        Log.d("SimpleShortcutModule", "creating new view: " + getTitle().getString(moduleContext.getContext()) + ", " + v);
        return v;
    }

    @Override
    public ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder<ModuleView> v = super.createNewViewWithHolder(moduleContext, holderResourceId, holderParent);
        Log.d("SimpleShortcutModule", "creating new view: " + getTitle().getString(moduleContext.getContext()) + ", " + v.view);
        return v;
    }
}
