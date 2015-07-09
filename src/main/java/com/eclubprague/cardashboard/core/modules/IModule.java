package com.eclubprague.cardashboard.core.modules;

import com.eclubprague.cardashboard.core.modules.models.IconResource;
import com.eclubprague.cardashboard.core.modules.models.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 *
 * Interface for modules.
 */
public interface IModule {

    IconResource getIcon();

    StringResource getTitle();

}
