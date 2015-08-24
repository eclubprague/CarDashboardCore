package com.eclubprague.cardashboard.core.modules;

import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.AbstractSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;

/**
 * Created by Michael on 15. 7. 2015.
 * <p/>
 * Simple module implementation for testing.
 */
public class TestSimpleModule extends AbstractSimpleModule {

    public TestSimpleModule(StringResource titleResource, IconResource iconResource) {
        super(titleResource, iconResource);
    }

    @Override
    public ModuleView createNewView(IModuleContext moduleContext, ViewGroup parent) {
        return ModuleViewFactory.createPassive(moduleContext, parent, this, getIcon(), getTitle());
    }

    @Override
    public ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(moduleContext, holderResourceId, holderParent, this, getIcon(), getTitle());
    }
}
