package com.eclubprague.cardashboard.core.modules.predefined;

import com.eclubprague.cardashboard.core.modules.base.AbstractParentModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * Simple implementation of submenu module.
 */
public class SimpleParentModule extends AbstractParentModule {

    public SimpleParentModule(StringResource titleResource, IconResource iconResource) {
        super(titleResource, iconResource);
    }

    public SimpleParentModule(IModuleContext moduleContext, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, titleResource, iconResource, bgColorResource, fgColorResource);
    }
}
