package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalSlowUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.MainThreadReceiver;
import com.eclubprague.cardashboard.core.modules.base.AbstractDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 12.08.2015.
 */
public class DeviceBatteryModule extends AbstractDisplayModule implements MainThreadReceiver<GlobalSlowUpdateEvent> {

    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_battery_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_battery_std_black_24dp);
    private static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_battery_units);

    public DeviceBatteryModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, unitResource);
    }

    public DeviceBatteryModule(@NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(parent, titleResource, iconResource, unitResource);
    }

    public DeviceBatteryModule(@NonNull IModuleContext moduleContext, @NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(moduleContext, parent, titleResource, iconResource, unitResource);
    }

    public DeviceBatteryModule(@NonNull IModuleContext moduleContext, @NonNull IParentModule parent, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull StringResource unitResource) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource, unitResource);
    }

    @Override
    public void onEventMainThread(GlobalSlowUpdateEvent event) {

    }
}
