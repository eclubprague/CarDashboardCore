package com.eclubprague.cardashboard.core.modules.predefined;

import android.content.Context;

import com.eclubprague.cardashboard.core.modules.base.AbstractSubmenuModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.ISubmenuModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * Simple implementation of submenu module.
 */
public class SimpleSubmenuModule extends AbstractSubmenuModule {

    public SimpleSubmenuModule(IModuleContext moduleContext, ISubmenuModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource);
    }

    @Override
    public void onLongClickEvent(Context context) {

    }
}
