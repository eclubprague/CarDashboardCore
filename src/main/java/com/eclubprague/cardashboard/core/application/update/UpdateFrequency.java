package com.eclubprague.cardashboard.core.application.update;

import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalFastUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalSlowUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalUpdateEvent;

/**
 * Created by Michael on 11. 8. 2015.
 */
public enum UpdateFrequency {
    FAST(200) {
        @Override
        public GlobalUpdateEvent createNewEvent() {
            return GlobalFastUpdateEvent.createNewEvent();
        }
    },
    MEDIUM(500) {
        @Override
        public GlobalUpdateEvent createNewEvent() {
            return GlobalMediumUpdateEvent.createNewEvent();
        }
    },
    SLOW(1000) {
        @Override
        public GlobalUpdateEvent createNewEvent() {
            return GlobalSlowUpdateEvent.createNewEvent();
        }
    };

    private final long interval;

    UpdateFrequency(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }

    abstract public GlobalUpdateEvent createNewEvent();
}
