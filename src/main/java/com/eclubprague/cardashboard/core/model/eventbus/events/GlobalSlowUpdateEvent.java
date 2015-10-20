package com.eclubprague.cardashboard.core.model.eventbus.events;

/**
 * Every 5000 ms
 * Created by Michael on 11. 8. 2015.
 */
public class GlobalSlowUpdateEvent extends GlobalUpdateEvent {
    private static final GlobalSlowUpdateEvent e = new GlobalSlowUpdateEvent();

    private GlobalSlowUpdateEvent() {

    }

    public static GlobalSlowUpdateEvent createNewEvent() {
        return e;
    }
}