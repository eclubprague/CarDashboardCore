package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleViewFactory;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Base implementation of shortcut module.
 * It leads to an external application through predefined Intent.
 */
public class AbstractShortcutModule extends AbstractSimpleModule {
    private final Intent intent;

    public AbstractShortcutModule(StringResource titleResource, IconResource iconResource, Intent intent) {
        super(titleResource, iconResource);
        this.intent = intent;
    }

    public AbstractShortcutModule(IModuleContext moduleContext, ISubmenuModule parent, StringResource titleResource, IconResource iconResource, Intent intent) {
        super(moduleContext, parent, titleResource, iconResource);
        this.intent = intent;
    }

    public AbstractShortcutModule(IModuleContext moduleContext, ISubmenuModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource, Intent intent) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource);
        this.intent = intent;
    }

    @Override
    public void onClickEvent(Context context) {
        context.startActivity(intent);
    }

    @Override
    public void onLongClickEvent(Context context) {
    }

    @Override
    public View createNewView(Context context, ViewGroup parent) {
        return ModuleViewFactory.createPassive(context, parent, getIcon(), getTitle());
    }

    @Override
    public ViewGroup createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(context, holderResourceId, holderParent, getIcon(), getTitle());
    }
}
