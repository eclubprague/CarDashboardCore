package com.eclubprague.cardashboard.core.model.eventbus.events;

/**
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
