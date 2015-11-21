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
public class AppShortcutModule extends AbstractShortcutModule {
    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_shortcuts_new);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_exit_to_app_black_24dp);

    public AppShortcutModule() {
        setTitle( TITLE_RESOURCE );
        setIcon( ICON_RESOURCE );
    }

    public AppShortcutModule( StringResource titleResource, IconResource iconResource, Intent intent ) {
        super( titleResource, iconResource, intent );
    }
}
