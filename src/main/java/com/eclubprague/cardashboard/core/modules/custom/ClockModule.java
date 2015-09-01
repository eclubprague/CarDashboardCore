package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;
import android.util.Log;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.util.Calendar;

public class ClockModule extends AbstractTimedUpdateDisplayModule<GlobalMediumUpdateEvent> {

    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_clock_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_clock_black_24dp);
    private static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_clock_units);

    public ClockModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        init();
    }

    public ClockModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);
        init();
    }

    private void init() {
        updateTime();
    }

    private void updateTime() {
        Calendar c = Calendar.getInstance();
        updateValue(String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
    }

    @Override
    public void onEventMainThread(GlobalMediumUpdateEvent event) {
        updateTime();
    }
}
