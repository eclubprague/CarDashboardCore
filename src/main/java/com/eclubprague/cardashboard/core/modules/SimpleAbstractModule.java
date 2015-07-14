package com.eclubprague.cardashboard.core.modules;

import com.eclubprague.cardashboard.core.modules.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Simple implementation of IModule interface.
 */
public class SimpleAbstractModule implements IModule {
    private final IconResource iconResource;
    private final StringResource titleResource;
    private final ColorResource bgColorResource;
    private final ColorResource fgColorResource;

    public SimpleAbstractModule(IconResource iconResource, StringResource titleResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        this.iconResource = iconResource;
        this.titleResource = titleResource;
        this.bgColorResource = bgColorResource;
        this.fgColorResource = fgColorResource;
    }

    @Override
    public IconResource getIcon() {
        return iconResource;
    }

    @Override
    public StringResource getTitle() {
        return titleResource;
    }

    @Override
    public ColorResource getBackgroundColor() {
        return bgColorResource;
    }

    @Override
    public ColorResource getForegroundColor() {
        return fgColorResource;
    }
}
