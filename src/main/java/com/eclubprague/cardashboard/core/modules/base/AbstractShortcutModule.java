package com.eclubprague.cardashboard.core.modules.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;

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

    public AbstractShortcutModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull Intent intent, @NonNull StringResource errorMessage) {
        super(titleResource, iconResource, bgColorResource, fgColorResource);
        this.intent = intent;
        this.errorMessage = errorMessage;
    }

    @Override
    public void onClickEvent(IModuleContext context) {
        super.onClickEvent(context);
        context.launchIntent(intent, getErrorMessage());
    }

    @Override
    public ModuleView createNewView(IModuleContext moduleContext, ViewGroup parent) {
        return ModuleViewFactory.createPassive(moduleContext, parent, this, getIcon(), getTitle());
    }

    @Override
    public ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(moduleContext, holderResourceId, holderParent, this, getIcon(), getTitle());
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
