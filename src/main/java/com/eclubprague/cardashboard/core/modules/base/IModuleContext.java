package com.eclubprague.cardashboard.core.modules.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleView;

/**
 * Created by Michael on 15. 7. 2015.
 * <p/>
 * Module context required for calls from modules.
 */
public interface IModuleContext {
    void goToSubmodules(IParentModule parentModule);

    void goBackFromSubmodules(IParentModule previousParentModule);

    void toggleQuickMenu(IModule module, ModuleView moduleView, boolean activate);

    void turnQuickMenusOff();

    void launchIntent(Intent intent, StringResource errorMessage);

    Context getContext();

    void onModuleEvent(IModule module, ModuleView moduleView, ModuleEvent event);

    Activity getActivity();
}
