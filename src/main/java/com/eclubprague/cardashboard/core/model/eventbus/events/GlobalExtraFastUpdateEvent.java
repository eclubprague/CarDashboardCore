package com.eclubprague.cardashboard.core.model.eventbus.events;

/**
 * Created by Michael on 11. 8. 2015.
 */
public class GlobalExtraFastUpdateEvent extends GlobalUpdateEvent {
    private static final GlobalExtraFastUpdateEvent e = new GlobalExtraFastUpdateEvent();

    private GlobalExtraFastUpdateEvent() {

    }

    public static GlobalExtraFastUpdateEvent createNewEvent() {
        return e;
    }
}
