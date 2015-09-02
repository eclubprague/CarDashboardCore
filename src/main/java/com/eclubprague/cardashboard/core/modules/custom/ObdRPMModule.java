package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.obd.OBDGatewayService;
import com.eclubprague.cardashboard.core.obd.ObdCommandJob;
import com.github.pires.obd.commands.engine.EngineRPMObdCommand;

public class ObdRPMModule extends AbstractTimedUpdateDisplayModule<GlobalExtraFastUpdateEvent> {

    public static final String TAG = ObdRPMModule.class.getSimpleName();

    public ObdRPMModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, unitResource);
    }

    @Override
    public void onEventMainThread(GlobalExtraFastUpdateEvent event) {
        OBDGatewayService gatewayService = (OBDGatewayService) OBDGatewayService.getInstance();
        if (gatewayService != null) {
            gatewayService.enqueue(new ObdCommandJob(new EngineRPMObdCommand()));
            ObdCommandJob results = gatewayService.getResult(EngineRPMObdCommand.class);
            if (results != null && results.getCommand().getResult() != null){
                updateValue(results.getCommand().getCalculatedResult());
            }

        }
    }
}
