package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Lukas on 28. 8. 2015.
 */
public class ObdRPMModule extends AbstractTimedUpdateDisplayModule<GlobalExtraFastUpdateEvent> {

    public ObdRPMModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(titleResource, iconResource, unitResource);
    }

    @Override
    public void onEventMainThread(GlobalExtraFastUpdateEvent event) {
        updateValue();
    }
}
