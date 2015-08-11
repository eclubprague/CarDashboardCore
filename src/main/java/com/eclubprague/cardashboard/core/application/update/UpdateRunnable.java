package com.eclubprague.cardashboard.core.application.update;

import com.eclubprague.cardashboard.core.model.eventbus.FastEventBus;

/**
 * Created by Michael on 11. 8. 2015.
 */
public class UpdateRunnable implements Runnable {
    private final UpdateFrequency updateFrequency;

    public UpdateRunnable(UpdateFrequency updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    @Override
    public void run() {
        FastEventBus.getInstance().post(updateFrequency.createNewEvent());
    }
}
