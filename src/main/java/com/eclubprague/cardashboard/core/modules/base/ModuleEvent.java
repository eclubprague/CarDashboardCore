package com.eclubprague.cardashboard.core.modules.base;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 12.08.2015.
 */
public enum ModuleEvent {
    MOVE(IconResource.fromResourceId(R.drawable.ic_open_with_black_24dp), StringResource.fromResourceId(R.string.event_move)),
    DELETE(IconResource.fromResourceId(R.drawable.ic_delete_black_24dp), StringResource.fromResourceId(R.string.event_delete)),
    CANCEL(IconResource.fromResourceId(R.drawable.ic_close_black_24dp), StringResource.fromResourceId(R.string.event_cancel)),
    ADD(IconResource.fromResourceId(R.drawable.ic_add_black_24dp), StringResource.fromResourceId(R.string.event_add)),
    MORE(IconResource.fromResourceId(R.drawable.ic_more_horiz_black_24dp), StringResource.fromResourceId(R.string.event_more));

    private final IconResource iconResource;
    private final StringResource titleResource;

    ModuleEvent(IconResource iconResource, StringResource titleResource) {
        this.iconResource = iconResource;
        this.titleResource = titleResource;
    }

    public IconResource getIcon() {
        return iconResource;
    }

    public StringResource getTitleResource() {
        return titleResource;
    }
}
