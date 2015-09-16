package com.eclubprague.cardashboard.core.modules.base;

import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.views.ModuleView;

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
    ModuleView getView();

//    List<ModuleView> getViews(IModuleContext context);

//    IModule removeViews(IModuleContext context);

//    IModule addView(IModuleContext moduleContext, ModuleView moduleView);

//    IModule removeView(IModuleContext moduleContext, ModuleView moduleView);
//
//    IModule removeView(ModuleView moduleView);

    ViewWithHolder<ModuleView> createViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent);

    View createQuickMenuView(IModuleContext moduleContext, ViewGroup parent);

    ViewWithHolder createQuickMenuViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent);

    void setHolder(ViewGroup holder);

    ViewGroup getHolder();

    ModuleEnum getModuleEnum();

}
