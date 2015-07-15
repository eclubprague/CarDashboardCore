package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;

import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Interface for modules.
 */
public interface IModule {

    IconResource getIcon();

    StringResource getTitle();

    ColorResource getBackgroundColor();

    ColorResource getForegroundColor();

    void onClick(Context context);

    void onLongClick(Context context);

}
