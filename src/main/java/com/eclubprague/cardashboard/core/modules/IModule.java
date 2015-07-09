package com.eclubprague.cardashboard.core.modules;

import com.eclubprague.cardashboard.core.modules.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 *
 * Interface for modules.
 */
public interface IModule {

    IconResource getIcon();

    StringResource getTitle();

    ColorResource getBackgroundColor();

    ColorResource getForegroundColor();

}
