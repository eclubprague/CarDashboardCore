package com.eclubprague.cardashboard.core.modules.predefined;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;

import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.views.ModuleView;

/**
 * Created by Michael on 02.09.2015.
 */
public class SimpleShortcutModule extends AbstractShortcutModule {
    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_shortcuts_custom );
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_exit_to_app_black_24dp );

    public SimpleShortcutModule() {
        setTitle( TITLE_RESOURCE );
        setIcon( ICON_RESOURCE );
    }

    public SimpleShortcutModule( StringResource titleResource, IconResource iconResource, Intent intent ) {
        super( titleResource, iconResource, intent );
    }
}
