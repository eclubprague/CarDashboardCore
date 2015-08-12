package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.content.Intent;

import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 15. 7. 2015.
 *
 * Module context required for calls from modules.
 */
public interface IModuleContext {
    void goToSubmodules(IParentModule parentModule);

    void goBackFromSubmodules(IParentModule parentModule);

    void toggleQuickMenu(IModule module, boolean activate);

    void turnQuickMenusOff();

    void launchIntent(Intent intent, StringResource errorMessage);

    void swapModules(IModule oldModule, IModule newModule, boolean animate);

    Context getContext();

    void addListener(IActivityStateChangeListener listener);
}
