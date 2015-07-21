package com.eclubprague.cardashboard.core.modules.predefined;

import android.content.Context;

import com.eclubprague.cardashboard.core.modules.base.AbstractSubmenuModule;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Top menu module, containing the first level of modules.
 */
public class HomeScreenModule extends AbstractSubmenuModule {
    private static final HomeScreenModule instance = new HomeScreenModule();

    public static HomeScreenModule getInstance() {
        return instance;
    }

    public HomeScreenModule() {
        super(null, null);
    }

    @Override
    public void onClickEvent(Context context) {

    }

    @Override
    public void onLongClickEvent(Context context) {

    }
}
