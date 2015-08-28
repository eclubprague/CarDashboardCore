package com.eclubprague.cardashboard.core.modules.custom;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalSlowUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 12.08.2015.
 */
public class DeviceBatteryModule extends AbstractTimedUpdateDisplayModule<GlobalSlowUpdateEvent> {

    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_battery_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_battery_std_black_24dp);
    private static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_battery_units);

    private int previousIconId = -1;

    public DeviceBatteryModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        init();
    }

    public DeviceBatteryModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);
        init();
    }

    private void init() {
//        FastEventBus.getInstance().register(this, GlobalSlowUpdateEvent.class);
        update();
    }

    @Override
    public void onEventMainThread(GlobalSlowUpdateEvent event) {
        update();
    }

    private void update() {
//        Log.d(DeviceBatteryModule.class.getSimpleName(), "updating");
        Context context = GlobalApplication.getInstance().getContext();
        if (context == null) {
            updateValue("?");
            setIconIfChange(R.drawable.ic_battery_unknown_black_24dp);
            return;
        }
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int batteryPct = (int) (100 * level / (float) scale);
//        Random r = new Random();
//        batteryPct = r.nextInt(101);
        updateValue(Integer.toString(batteryPct));
        if (isCharging) {
            if (batteryPct < 20) {
                setIconIfChange(R.drawable.ic_battery_charging_20_black_24dp);
            } else if (batteryPct < 30) {
                setIconIfChange(R.drawable.ic_battery_charging_20_black_24dp);
            } else if (batteryPct < 50) {
                setIconIfChange(R.drawable.ic_battery_charging_30_black_24dp);
            } else if (batteryPct < 60) {
                setIconIfChange(R.drawable.ic_battery_charging_50_black_24dp);
            } else if (batteryPct < 80) {
                setIconIfChange(R.drawable.ic_battery_charging_60_black_24dp);
            } else if (batteryPct < 90) {
                setIconIfChange(R.drawable.ic_battery_charging_80_black_24dp);
            } else if (batteryPct < 100) {
                setIconIfChange(R.drawable.ic_battery_charging_90_black_24dp);
            } else {
                setIconIfChange(R.drawable.ic_battery_charging_full_black_24dp);
            }
        } else {
            if (batteryPct < 20) {
                setIconIfChange(R.drawable.ic_battery_alert_black_24dp);
            } else if (batteryPct < 30) {
                setIconIfChange(R.drawable.ic_battery_20_black_24dp);
            } else if (batteryPct < 50) {
                setIconIfChange(R.drawable.ic_battery_30_black_24dp);
            } else if (batteryPct < 60) {
                setIconIfChange(R.drawable.ic_battery_50_black_24dp);
            } else if (batteryPct < 80) {
                setIconIfChange(R.drawable.ic_battery_60_black_24dp);
            } else if (batteryPct < 90) {
                setIconIfChange(R.drawable.ic_battery_80_black_24dp);
            } else if (batteryPct < 100) {
                setIconIfChange(R.drawable.ic_battery_90_black_24dp);
            } else {
                setIconIfChange(R.drawable.ic_battery_full_black_24dp);
            }
        }
    }

    private void setIconIfChange(int iconResourceId) {
        if (previousIconId != iconResourceId) {
            setIcon(IconResource.fromResourceId(iconResourceId));
            previousIconId = iconResourceId;
        }
    }

    @Override
    public IModule onCopy(IModule newInstance) throws ReflectiveOperationException {
        DeviceBatteryModule m = (DeviceBatteryModule) super.onDeepCopy(newInstance);
        m.setIconIfChange(getIcon().getResourceId());
        return m;
    }

    @Override
    public IModule onDeepCopy(IModule newInstance) throws ReflectiveOperationException {
        DeviceBatteryModule m = (DeviceBatteryModule) super.onDeepCopy(newInstance);
        m.setIconIfChange(getIcon().getResourceId());
        return m;
    }
}
