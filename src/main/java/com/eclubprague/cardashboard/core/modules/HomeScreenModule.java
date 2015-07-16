package com.eclubprague.cardashboard.core.modules;

import android.content.Context;

import com.eclubprague.cardashboard.core.data.ModuleSupplier;
import com.eclubprague.cardashboard.core.modules.base.AbstractSubmenuModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.util.List;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Top menu module, containing the first level of modules.
 */
public class HomeScreenModule extends AbstractSubmenuModule {
    private static HomeScreenModule ourInstance = new HomeScreenModule(null, null, null, null, null, ModuleSupplier.getHomeScreenModules());

    public static HomeScreenModule getInstance() {
        return ourInstance;
    }

    public HomeScreenModule(IModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource, List<IModule> submodules) {
        super(parent, titleResource, iconResource, bgColorResource, fgColorResource, submodules);
    }

    @Override
    public void onClickEvent(Context context) {

    }

    @Override
    public void onLongClickEvent(Context context) {

    }
}
