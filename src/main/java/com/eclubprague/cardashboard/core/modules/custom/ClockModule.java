package com.eclubprague.cardashboard.core.modules.custom;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.eventbus.FastEventBus;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.MainThreadReceiver;
import com.eclubprague.cardashboard.core.modules.base.AbstractDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.util.Calendar;

/**
 * Created by Michael on 11. 8. 2015.
 */
public class ClockModule extends AbstractDisplayModule implements MainThreadReceiver<GlobalMediumUpdateEvent> {

    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_clock_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_clock_black_24dp);
    private static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_clock_units);

    public ClockModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        FastEventBus.getInstance().register(this, GlobalMediumUpdateEvent.class);
    }

    public ClockModule(IModuleContext moduleContext, IParentModule parent) {
        super(moduleContext, parent, TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        FastEventBus.getInstance().register(this, GlobalMediumUpdateEvent.class);
    }

    public ClockModule(IModuleContext moduleContext, IParentModule parent, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, parent, TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);
        FastEventBus.getInstance().register(this, GlobalMediumUpdateEvent.class);
    }

    @Override
    public void onEventMainThread(GlobalMediumUpdateEvent event) {
        Calendar c = Calendar.getInstance();
        updateValue(String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
    }
}
