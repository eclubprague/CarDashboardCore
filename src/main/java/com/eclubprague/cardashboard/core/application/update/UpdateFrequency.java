package com.eclubprague.cardashboard.core.application.update;

import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraSlowUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalFastUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalSlowUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalUpdateEvent;

/**
 * Created by Michael on 11. 8. 2015.
 */
public enum UpdateFrequency {
    EXTRA_FAST(200) {
        @Override
        public GlobalUpdateEvent createNewEvent() {
            return GlobalExtraFastUpdateEvent.createNewEvent();
        }
    },
    FAST(500) {
        @Override
        public GlobalUpdateEvent createNewEvent() {
            return GlobalFastUpdateEvent.createNewEvent();
        }
    },
    MEDIUM(1000) {
        @Override
        public GlobalUpdateEvent createNewEvent() {
            return GlobalMediumUpdateEvent.createNewEvent();
        }
    },
    SLOW(5000) {
        @Override
        public GlobalUpdateEvent createNewEvent() {
            return GlobalSlowUpdateEvent.createNewEvent();
        }
    },
    EXTRA_SLOW(1000 * 60 * 5) {
        @Override
        public GlobalUpdateEvent createNewEvent() {
            return GlobalExtraSlowUpdateEvent.createNewEvent();
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
