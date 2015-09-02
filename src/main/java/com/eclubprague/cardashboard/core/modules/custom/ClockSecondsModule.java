package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.util.Calendar;

/**
 * Created by Michael on 02.09.2015.
 */
public class ClockSecondsModule extends AbstractTimedUpdateDisplayModule<GlobalMediumUpdateEvent> {
    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_clock_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_clock_black_24dp);
    private static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_clock_units);

    public ClockSecondsModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        init();
    }

    public ClockSecondsModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);
        init();
    }

    private void init() {
        updateTime();
    }

    private void updateTime() {
        Calendar c = Calendar.getInstance();
        updateValue(String.format("%02d:%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND)));
    }

    @Override
    public void onEventMainThread(GlobalMediumUpdateEvent event) {
//        Log.d(getClass().getSimpleName(), "UPDATING");
        updateTime();
    }
}
