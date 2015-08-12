package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
    private final StringResource errorMessage;

    public AbstractShortcutModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull Intent intent, @NonNull StringResource errorMessage) {
        super(titleResource, iconResource);
        this.intent = intent;
        this.errorMessage = errorMessage;
    }

    public AbstractShortcutModule(@NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull Intent intent, @NonNull StringResource errorMessage) {
        super(parent, titleResource, iconResource);
        this.intent = intent;
        this.errorMessage = errorMessage;
    }

    public AbstractShortcutModule(@NonNull IModuleContext moduleContext, @NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull Intent intent, @NonNull StringResource errorMessage) {
        super(moduleContext, parent, titleResource, iconResource);
        this.intent = intent;
        this.errorMessage = errorMessage;
    }

    public AbstractShortcutModule(@NonNull IModuleContext moduleContext, @NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull Intent intent, @NonNull StringResource errorMessage) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource);
        this.intent = intent;
        this.errorMessage = errorMessage;
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

    public StringResource getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "AbstractShortcutModule{" +
                super.toString() + ", " +
                "intent=" + intent +
                '}';
    }
}
