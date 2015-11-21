package com.eclubprague.cardashboard.core.modules.custom.settings;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.application.GlobalDataProvider;

import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.utils.ThemeUtils;

/**
 * Created by Michael on 24.09.2015.
 */
public class ThemeSwitchModule extends AbstractDisplayModule {
    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_settings_theme_title );
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_settings_black_24dp );
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId( R.string.module_unit_empty );


    public ThemeSwitchModule() {
        super( TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE );
    }

    @Override
    public void onClickEvent( IModuleContext context ) {
        super.onClickEvent( context );
        if ( ThemeUtils.getCurrentTheme( context ).equals( ThemeUtils.Theme.DARK ) ) {
            ThemeUtils.changeTheme( ThemeUtils.Theme.LIGHT, context );
        } else {
            ThemeUtils.changeTheme( ThemeUtils.Theme.DARK, context );
        }
    }

    @Override
    public String getUpdatedValue() {
        return ThemeUtils.getCurrentTheme( GlobalDataProvider.getInstance().getModuleContext() ).name().toLowerCase();
    }
}
