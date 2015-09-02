package com.eclubprague.cardashboard.core.modules.predefined;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.AbstractSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * Module leading back to topModule.
 */
public class BackModule extends AbstractSimpleModule {
    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.appmenu_module_up_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_reply_black_24dp);
    private final IParentModule topModule;

    public BackModule(IParentModule topModule) {
        super(TITLE_RESOURCE, ICON_RESOURCE);
        this.topModule = topModule;
    }

    public BackModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, IParentModule topModule) {
        super(TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource);
        this.topModule = topModule;
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
    public void onClickEvent(IModuleContext context, ModuleView moduleView) {
//        Log.d("BackModule", getParent().toString());
        context.goBackFromSubmodules(topModule);
    }

    @Override
    public void onLongClickEvent(IModuleContext context, ModuleView moduleView) {
        onClickEvent(context, moduleView);
        //super.onLongClickEvent(context);
    }
}
