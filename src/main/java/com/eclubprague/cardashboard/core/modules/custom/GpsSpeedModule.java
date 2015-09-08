package com.eclubprague.cardashboard.core.modules.custom;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.application.GlobalDataProvider;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;

public class GpsSpeedModule extends AbstractTimedUpdateDisplayModule<GlobalMediumUpdateEvent> implements LocationListener {

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_gpsspeed_title);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_map_black_24dp);
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_gpsspeed_units);

    public GpsSpeedModule() {
        super(ModuleEnum.GPS_SPEED, TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
    }

    public GpsSpeedModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(ModuleEnum.GPS_SPEED, TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);

    }

    LocationManager locationManager;

    @Override
    public String getUpdatedValue() {
        Context context = GlobalDataProvider.getInstance().getContext();
        if (locationManager == null && context != null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        return getLastValue();
    }


    @Override
    public void onPause(IModuleContext moduleContext) {
        super.onPause(moduleContext);
        if (locationManager != null)
            locationManager.removeUpdates(this);
    }

    @Override
    public void onResume(IModuleContext moduleContext) {
        super.onResume(moduleContext);
        if (locationManager != null)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        setLastValue("" + location.getSpeed());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO: 12. 8. 2015 co se stane kdyz je GPS disabled
    }

    @Override
    public void onProviderDisabled(String provider) {}

}


