package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
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
abstract public class AbstractShortcutModule extends AbstractSimpleModule {
    private final Intent intent;

    public AbstractShortcutModule(StringResource titleResource, IconResource iconResource, Intent intent) {
        super(titleResource, iconResource);
        this.intent = intent;
    }

    public AbstractShortcutModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource, Intent intent) {
        super(moduleContext, parent, titleResource, iconResource);
        this.intent = intent;
    }

    public AbstractShortcutModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource, Intent intent) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource);
        this.intent = intent;
    }

    @Override
    public void onClickEvent(IModuleContext context) {
        super.onClickEvent(context);
        context.launchIntent(intent, getErrorMessage());
    }

    @Override
    public View createNewView(Context context, ViewGroup parent) {
        return ModuleViewFactory.createPassive(context, parent, this, getModuleContext(), getIcon(), getTitle());
    }

    @Override
    public ViewWithHolder createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(context, holderResourceId, holderParent, this, getModuleContext(), getIcon(), getTitle());
    }

    protected abstract StringResource getErrorMessage();

    @Override
    public String toString() {
        return "AbstractShortcutModule{" +
                super.toString() + ", " +
                "intent=" + intent +
                '}';
    }
}
