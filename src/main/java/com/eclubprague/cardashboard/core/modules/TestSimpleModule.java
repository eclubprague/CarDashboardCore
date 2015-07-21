package com.eclubprague.cardashboard.core.modules;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eclubprague.cardashboard.core.modules.base.AbstractSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.ISubmenuModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleViewFactory;

/**
 * Created by Michael on 15. 7. 2015.
 * <p/>
 * Simple module implementation for testing.
 */
public class TestSimpleModule extends AbstractSimpleModule {

    public TestSimpleModule(IModuleContext moduleContext, ISubmenuModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource);
    }

    @Override
    public void onClickEvent(Context context) {
        Toast.makeText(context, "short click", Toast.LENGTH_SHORT);
    }

    @Override
    public void onLongClickEvent(Context context) {
        Toast.makeText(context, "long click", Toast.LENGTH_SHORT);
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
