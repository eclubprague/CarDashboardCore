package com.eclubprague.cardashboard.core.modules.predefined;

import android.support.annotation.NonNull;


import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractParentModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * Simple implementation of submenu module.
 */
public class SimpleParentModule extends AbstractParentModule {
    public static final StringResource HOME_TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_home_title );
    public static final IconResource HOME_ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_home_black_24dp );

    public static final StringResource OBD_TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_obd_title );
    public static final IconResource OBD_ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_directions_car_black_24dp );

    public static final StringResource OTHERS_TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_others_title );
    public static final IconResource OTHERS_ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_apps_black_24dp );

    public static final StringResource SETTINGS_TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_settings_title );
    public static final IconResource SETTINGS_ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_settings_black_24dp );

    public static final StringResource SHORTCUT_TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_shortcuts_title );
    public static final IconResource SHORTCUT_ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_exit_to_app_black_24dp );


    public SimpleParentModule() {
    }

    public SimpleParentModule( StringResource titleResource, IconResource iconResource ) {
        setTitle( titleResource );
        setIcon( iconResource );
    }

    @Override
    public IParentModule copy() {
        SimpleParentModule newParent = new SimpleParentModule( getTitle(), getIcon() );
        if ( hasBackgroundColor() ) {
            newParent.setBackgroundColor( getBackgroundColor() );
        }
        if ( hasForegroundColor() ) {
            newParent.setForegroundColor( getForegroundColor() );
        }
        newParent.addSubmodules( getSubmodules() );
        return newParent;
    }

    @Override
    public IParentModule copyDeep() {
        SimpleParentModule newParent = new SimpleParentModule( getTitle(), getIcon() );
        if ( hasBackgroundColor() ) {
            newParent.setBackgroundColor( getBackgroundColor() );
        }
        if ( hasForegroundColor() ) {
            newParent.setForegroundColor( getForegroundColor() );
        }
        for ( IModule m : getSubmodules() ) {
            if ( m instanceof IParentModule ) {
                IParentModule parentModule = (IParentModule) m;
                newParent.addSubmodules( parentModule.copyDeep() );
            } else {
                newParent.addSubmodules( m );
            }
        }
        return newParent;
    }
}
