package com.eclubprague.cardashboard.core.modules.predefined;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.AbstractSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleViewFactory;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * Module leading back to parent.
 */
public class BackModule extends AbstractSimpleModule {

    public BackModule() {
        super(StringResource.fromResourceId(R.string.appmenu_module_up_title), IconResource.fromResourceId(R.drawable.ic_reply_black_24dp));
    }

    public BackModule(IModuleContext moduleContext, IParentModule parent) {
        super(moduleContext, parent, StringResource.fromResourceId(R.string.appmenu_module_up_title), IconResource.fromResourceId(R.drawable.ic_reply_black_24dp));
    }

    public BackModule(IModuleContext moduleContext, IParentModule parent, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, parent, StringResource.fromResourceId(R.string.appmenu_module_up_title), IconResource.fromResourceId(R.drawable.ic_reply_black_24dp), bgColorResource, fgColorResource);
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
//        Log.d("BackModule", getParent().toString());
        getModuleContext().goBackFromSubmodules(getParent().getParent());
    }

    @Override
    public void onLongClickEvent(IModuleContext context) {
        onClickEvent(context);
        //super.onLongClickEvent(context);
    }
}
