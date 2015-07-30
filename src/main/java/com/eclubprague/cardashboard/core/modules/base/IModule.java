package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Interface for modules.
 */
public interface IModule extends IModuleListener, IQuickMenuListener {

    IModuleContext getModuleContext();

    IModule setModuleContext(IModuleContext moduleContext);

    IParentModule getParent();

    IModule setParent(IParentModule parent);

    IconResource getIcon();

    IModule setIcon(IconResource iconResource);

    StringResource getTitle();

    IModule setTitle(StringResource titleResource);

    ColorResource getBackgroundColor();

    IModule setBackgroundColor(ColorResource bgColorResource);

    ColorResource getForegroundColor();

    IModule setForegroundColor(ColorResource fgColorResource);

    ModuleId getId();

    View createView(Context context, ViewGroup parent);

    View getView();

    ViewWithHolder createViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent);

    View createQuickMenuView(Context context, ViewGroup parent);

    ViewWithHolder createQuickMenuViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent);

    void saveHolder(ViewGroup holder);

    ViewGroup loadHolder();

}