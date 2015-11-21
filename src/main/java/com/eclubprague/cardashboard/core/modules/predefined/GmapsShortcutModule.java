package com.eclubprague.cardashboard.core.modules.predefined;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;

import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;

/**
 * Created by Michael on 21.11.2015.
 */
public class GmapsShortcutModule extends AbstractShortcutModule {
    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_shortcuts_new_maps_google);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_map_black_24dp);

    public GmapsShortcutModule() {
        setTitle( TITLE_RESOURCE );
        setIcon( ICON_RESOURCE );
    }

    public GmapsShortcutModule( StringResource titleResource, IconResource iconResource, Intent intent ) {
        super( titleResource, iconResource, intent );
    }
}
