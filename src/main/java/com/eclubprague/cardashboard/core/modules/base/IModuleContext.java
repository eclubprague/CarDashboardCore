package com.eclubprague.cardashboard.core.modules.base;

import android.content.Intent;

/**
 * Created by Michael on 15. 7. 2015.
 *
 * Module context required for calls from modules.
 */
public interface IModuleContext {
    void goToSubmodules(IParentModule parentModule);

    void goBackFromSubmodules(IParentModule parentModule);

    void toggleQuickMenu(IModule module, boolean activate);

    void launchIntent(Intent intent);

    void swapModules(IModule oldModule, IModule newModule, boolean animate);
}
