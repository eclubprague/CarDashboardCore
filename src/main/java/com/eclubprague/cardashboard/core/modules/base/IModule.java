package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Interface for modules.
 */
public interface IModule {

    IModule getParent();

    IconResource getIcon();

    StringResource getTitle();

    ColorResource getBackgroundColor();

    ColorResource getForegroundColor();

    void onClickEvent(Context context);

    void onLongClickEvent(Context context);

    View createView(Context context, ViewGroup parent);

    ViewGroup createViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent);

}
