package com.eclubprague.cardashboard.core.modules.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
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
    private Intent intent;
    private StringResource errorMessage;
    private static final StringResource defaultError = StringResource.fromResourceId(R.string.error_module_shortcut);

    public AbstractShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        super(moduleEnum, titleResource, iconResource);
    }

    public AbstractShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(moduleEnum, titleResource, iconResource, bgColorResource, fgColorResource);
    }

    public AbstractShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, Intent intent) {
        super(moduleEnum, titleResource, iconResource);
        this.intent = intent;
        this.errorMessage = StringResource.fromString(defaultError.getString(GlobalApplication.getInstance().getContext()) + intent.toUri(0));
    }

    public AbstractShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, StringResource errorMessage, Intent intent) {
        super(moduleEnum, titleResource, iconResource);
        this.errorMessage = errorMessage;
        this.intent = intent;
    }

    public AbstractShortcutModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, StringResource errorMessage, Intent intent) {
        super(moduleEnum, titleResource, iconResource, bgColorResource, fgColorResource);
        this.errorMessage = errorMessage;
        this.intent = intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        this.errorMessage = StringResource.fromString(defaultError.getString(GlobalApplication.getInstance().getContext()) + intent.toUri(0));
    }

    public Intent getIntent() {
        return intent;
    }

    @Override
    public void onClickEvent(IModuleContext context, ModuleView moduleView) {
        super.onClickEvent(context, moduleView);
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
        return getClass().getSimpleName() + "{" +
                super.toString() + ", " +
                "intent=" + intent +
                '}';
    }
}
