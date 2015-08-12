package com.eclubprague.cardashboard.core.modules.predefined;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.AbstractSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.ModuleEvent;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleViewFactory;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * An empty module serving as an add button (can be replaced by another module TODO).
 */
public class EmptyModule extends AbstractSimpleModule {

    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.appmenu_module_add_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_add_black_24dp);

    public EmptyModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE);
    }

    public EmptyModule(@NonNull IParentModule parent) {
        super(parent, TITLE_RESOURCE, ICON_RESOURCE);
    }

    public EmptyModule(@NonNull IModuleContext moduleContext, @NonNull IParentModule parent) {
        super(moduleContext, parent, TITLE_RESOURCE, ICON_RESOURCE);
    }

    public EmptyModule(@NonNull IModuleContext moduleContext, @NonNull IParentModule parent, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(moduleContext, parent, TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource);
    }

    @Override
    protected View createNewView(Context context, ViewGroup parent) {
        return ModuleViewFactory.createPassive(context, parent, this, getModuleContext(), getIcon(), getTitle());
    }

    @Override
    protected ViewWithHolder createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(context, holderResourceId, holderParent, this, getModuleContext(), getIcon(), getTitle());
    }

    @Override
    public void onClickEvent(IModuleContext context) {
        getModuleContext().onModuleEvent(this, ModuleEvent.ADD);
    }

    @Override
    public void onLongClickEvent(IModuleContext context) {
    }
}
