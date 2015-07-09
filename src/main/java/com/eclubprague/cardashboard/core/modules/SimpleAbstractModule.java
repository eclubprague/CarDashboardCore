package com.eclubprague.cardashboard.core.modules;

import com.eclubprague.cardashboard.core.modules.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 *
 * Simple implementation of IModule interface.
 */
public abstract class SimpleAbstractModule implements IModule {
    private final IconResource iconResource;
    private final StringResource titleResource;

    public SimpleAbstractModule(IconResource iconResource, StringResource titleResource) {
        this.iconResource = iconResource;
        this.titleResource = titleResource;
    }

    @Override
    public IconResource getIcon() {
        return iconResource;
    }

    @Override
    public StringResource getTitle() {
        return titleResource;
    }
}
