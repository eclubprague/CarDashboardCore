package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.obd.OBDGatewayService;
import com.eclubprague.cardashboard.core.obd.ObdCommandJob;
import com.github.pires.obd.commands.SpeedObdCommand;
import com.github.pires.obd.commands.engine.EngineRPMObdCommand;

public class ObdSpeedModule extends AbstractTimedUpdateDisplayModule<GlobalExtraFastUpdateEvent> {

    public static final String TAG = ObdSpeedModule.class.getSimpleName();

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_obd_speed_title);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp);
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_obd_speed_units);

    public ObdSpeedModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
    }

    public ObdSpeedModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, unitResource);
    }


    @Override
    public void onPause(IModuleContext moduleContext) {
        super.onPause(moduleContext);

    }

    @Override
    public String getUpdatedValue() {
        OBDGatewayService gatewayService = (OBDGatewayService) OBDGatewayService.getInstance();
        if (gatewayService != null) {
            gatewayService.enqueue(new ObdCommandJob(new SpeedObdCommand()));
            ObdCommandJob results = gatewayService.getResult(SpeedObdCommand.class);
            if (results != null && results.getCommand().getResult() != null){
                setLastValue(results.getCommand().getCalculatedResult());
            }

        }
        return getLastValue();
    }
}
