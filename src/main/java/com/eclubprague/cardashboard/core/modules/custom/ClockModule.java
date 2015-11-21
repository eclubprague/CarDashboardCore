package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;

import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;

import java.util.Calendar;

/**
 * Module displaying Clock (in format HH:MM)
 */
public class ClockModule extends AbstractTimedUpdateDisplayModule<GlobalMediumUpdateEvent> {

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_others_clock_title );
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_clock_black_24dp );
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId( R.string.module_others_clock_units );


    public ClockModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
    }

    @Override
    public String getUpdatedValue() {
        Calendar c = Calendar.getInstance();
        return String.format( "%02d:%02d", c.get( Calendar.HOUR_OF_DAY ), c.get( Calendar.MINUTE ) );
    }
}
