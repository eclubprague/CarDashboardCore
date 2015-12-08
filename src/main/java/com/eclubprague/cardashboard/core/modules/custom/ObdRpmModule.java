package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;
import android.util.Log;

import com.eclubprague.cardashboard.core.R;

import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.obd.OBDGatewayService;
import com.eclubprague.cardashboard.core.obd.ObdCommandJob;
import com.github.pires.obd.commands.engine.EngineRPMObdCommand;

public class ObdRpmModule extends AbstractTimedUpdateDisplayModule<GlobalExtraFastUpdateEvent> {

    public static final String TAG = ObdRpmModule.class.getSimpleName();

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_obd_rpm_title );
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_directions_car_black_24dp );
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId( R.string.module_obd_rpm_units );

    public ObdRpmModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
    }


    @Override
    public void onPause( IModuleContext moduleContext ) {
        super.onPause( moduleContext );

    }

    @Override
    public String getUpdatedValue() {
        OBDGatewayService gatewayService = (OBDGatewayService) OBDGatewayService.getInstance();
        if (gatewayService != null && gatewayService.isRunning()) {
            gatewayService.enqueue(new ObdCommandJob(new EngineRPMObdCommand()));
            ObdCommandJob results = gatewayService.getResult(EngineRPMObdCommand.class);
            if (results != null && results.getCommand().getResult() != null) {
                String value = results.getCommand().getCalculatedResult();
                setLastValue(value);
                Log.d(TAG, value);
            }
        }

        return getLastValue();
    }
}
