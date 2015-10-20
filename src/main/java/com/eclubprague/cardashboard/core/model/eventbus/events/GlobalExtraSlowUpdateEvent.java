package com.eclubprague.cardashboard.core.model.eventbus.events;

/**
 * Every 300 000 ms (5 min)
 * Created by Michael on 11. 8. 2015.
 */
public class GlobalExtraSlowUpdateEvent extends GlobalUpdateEvent {
    private static final GlobalExtraSlowUpdateEvent e = new GlobalExtraSlowUpdateEvent();

    private GlobalExtraSlowUpdateEvent() {

    }

    public static GlobalExtraSlowUpdateEvent createNewEvent() {
        return e;
    }
}
