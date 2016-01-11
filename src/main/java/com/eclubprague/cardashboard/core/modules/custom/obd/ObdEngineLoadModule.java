package com.eclubprague.cardashboard.core.modules.custom.obd;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;

/**
 * Created by Michael on 11.01.2016.
 */
public class ObdEngineLoadModule extends ObdBaseModule<com.github.pires.obd.commands.engine.EngineLoadObdCommand> {
    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_obd_load_title );
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_directions_car_black_24dp );
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId( R.string.module_others_battery_units );

    public ObdEngineLoadModule() {
        super( TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE );
    }
}
