package com.eclubprague.cardashboard.core.modules.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.model.resources.StringResource;

/**
 * Created by Michael on 15. 7. 2015.
 * <p/>
 * Module context required for calls from modules.
 */
public interface IModuleContext {
    void goToParentModule(IParentModule parentModule);

    void goBackFromParentModule(IParentModule previousParentModule);

    void toggleQuickMenu(IModule module, boolean activate);

    void turnQuickMenusOff();

    void launchIntent(Intent intent, StringResource errorMessage);

    Context getContext();

    void onModuleEvent(IModule module, ModuleEvent event);

    Activity getActivity();

    ViewGroup getSnackbarHolder();
}
