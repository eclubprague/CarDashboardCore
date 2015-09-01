package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;
import android.util.Log;

import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.obd.DummyGatewayService;
import com.eclubprague.cardashboard.core.obd.ObdCommandJob;
import com.github.pires.obd.commands.engine.EngineRPMObdCommand;

public class ObdRPMModule extends AbstractTimedUpdateDisplayModule<GlobalExtraFastUpdateEvent> {

    public static final String TAG = ObdRPMModule.class.getSimpleName();

    public ObdRPMModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, unitResource);
    }

    @Override
    public void onEventMainThread(GlobalExtraFastUpdateEvent event) {
        Log.d(TAG, "enEvent");
        DummyGatewayService gatewayService = (DummyGatewayService) GlobalApplication.getInstance().getObdService();
        ObdCommandJob obdCommandJob = new ObdCommandJob(new EngineRPMObdCommand());
        gatewayService.enqueue(obdCommandJob);
        updateValue(String.valueOf(gatewayService.getResult(EngineRPMObdCommand.class).getId()));
    }
}
