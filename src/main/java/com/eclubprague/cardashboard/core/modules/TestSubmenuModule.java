package com.eclubprague.cardashboard.core.modules;

import android.content.Context;
import android.view.LayoutInflater;
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

/**
 * Created by Michael on 22. 7. 2015.
 * <p/>
 * Testing submenu, will be deleted
 */
public class TestSubmenuModule extends AbstractSimpleModule {

    public TestSubmenuModule(StringResource titleResource, IconResource iconResource) {
        super(titleResource, iconResource);
    }

    public TestSubmenuModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource) {
        super(moduleContext, parent, titleResource, iconResource);
    }

    public TestSubmenuModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource);
    }

    @Override
    protected View createNewView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_submenu, parent, false);
        return view;
    }

    @Override
    protected ViewWithHolder createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(context).inflate(holderResourceId, holderParent, false);
        View view = createNewView(context, holder);
        holder.addView(view);
        return new ViewWithHolder(view, holder);
    }

    @Override
    public void onClickEvent(Context context) {

    }

    @Override
    public void onLongClickEvent(Context context) {

    }
}
