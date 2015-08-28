package com.eclubprague.cardashboard.core.modules.base;

import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleView;

import java.util.List;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Interface for modules.
 */
public interface IModule extends IModuleListener, IQuickMenuListener, IActivityStateChangeListener {

    IconResource getIcon();

    IModule setIcon(IconResource iconResource);

    StringResource getTitle();

    IModule setTitle(StringResource titleResource);

    ColorResource getBackgroundColor();

    IModule setBackgroundColor(ColorResource bgColorResource);

    ColorResource getForegroundColor();

    IModule setForegroundColor(ColorResource fgColorResource);

    ModuleId getId();

    ModuleView createView(IModuleContext moduleContext, ViewGroup parent);

    List<ModuleView> getViews(IModuleContext context);

    IModule removeViews(IModuleContext context);

    ViewWithHolder<ModuleView> createViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent);

    View createQuickMenuView(IModuleContext moduleContext, ViewGroup parent);

    ViewWithHolder createQuickMenuViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent);

    void setHolder(ViewGroup holder);

    ViewGroup getHolder();

}
