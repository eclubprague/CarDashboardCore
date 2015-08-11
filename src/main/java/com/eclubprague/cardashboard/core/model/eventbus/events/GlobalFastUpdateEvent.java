package com.eclubprague.cardashboard.core.model.eventbus.events;

/**
 * Created by Michael on 11. 8. 2015.
 */
public class GlobalFastUpdateEvent extends GlobalUpdateEvent {
    private static final GlobalFastUpdateEvent e = new GlobalFastUpdateEvent();

    private GlobalFastUpdateEvent() {

    }

    public static GlobalFastUpdateEvent createNewEvent() {
        return e;
    }
}
