package com.eclubprague.cardashboard.core.modules.predefined;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.modules.base.AbstractParentModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * Simple implementation of submenu module.
 */
public class SimpleParentModule extends AbstractParentModule {

    public SimpleParentModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        super(moduleEnum, titleResource, iconResource);
    }

    public SimpleParentModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(moduleEnum, titleResource, iconResource, bgColorResource, fgColorResource);
    }

    @Override
    public IParentModule copy() {
        SimpleParentModule newParent = new SimpleParentModule(getModuleEnum(), getTitle(), getIcon());
        if (hasBackgroundColor()) {
            newParent.setBackgroundColor(getBackgroundColor());
        }
        if (hasForegroundColor()) {
            newParent.setForegroundColor(getForegroundColor());
        }
        newParent.addSubmodules(getSubmodules());
        return newParent;
    }

    @Override
    public IParentModule copyDeep() {
        SimpleParentModule newParent = new SimpleParentModule(getModuleEnum(), getTitle(), getIcon());
        if (hasBackgroundColor()) {
            newParent.setBackgroundColor(getBackgroundColor());
        }
        if (hasForegroundColor()) {
            newParent.setForegroundColor(getForegroundColor());
        }
        for (IModule m : getSubmodules()) {
            if (m instanceof IParentModule) {
                IParentModule parentModule = (IParentModule) m;
                newParent.addSubmodules(parentModule.copyDeep());
            } else {
                newParent.addSubmodules(m);
            }
        }
        return newParent;
    }
}
