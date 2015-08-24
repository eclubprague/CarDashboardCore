package com.eclubprague.cardashboard.core.modules.predefined;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.AbstractSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.ModuleEvent;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;

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

    public EmptyModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource);
    }

    @Override
    protected ModuleView createNewView(IModuleContext moduleContext, ViewGroup parent) {
        return ModuleViewFactory.createPassive(moduleContext, parent, this, getIcon(), getTitle());
    }

    @Override
    protected ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(moduleContext, holderResourceId, holderParent, this, getIcon(), getTitle());
    }

    @Override
    public void onClickEvent(IModuleContext context) {
        context.onModuleEvent(this, ModuleEvent.ADD);
    }

    @Override
    public void onLongClickEvent(IModuleContext context) {
    }
}
