package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;

import java.util.Calendar;

/**
 * Created by Michael on 02.09.2015.
 */
public class ClockSecondsModule extends AbstractTimedUpdateDisplayModule<GlobalMediumUpdateEvent> {
    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_clock_seconds_title);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_clock_black_24dp);
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_clock_seconds_units);

    public ClockSecondsModule() {
        super(ModuleEnum.CLOCK_SECONDS, TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
    }

    public ClockSecondsModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(ModuleEnum.CLOCK_SECONDS, TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);
    }

    @Override
    public String getUpdatedValue() {
        Calendar c = Calendar.getInstance();
        return String.format("%02d:%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
    }
}
