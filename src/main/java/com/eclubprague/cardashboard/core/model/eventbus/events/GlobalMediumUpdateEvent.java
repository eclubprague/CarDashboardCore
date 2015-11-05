package com.eclubprague.cardashboard.core.model.eventbus.events;

/**
 * Every 1000 ms
 * Created by Michael on 11. 8. 2015.
 */
public class GlobalMediumUpdateEvent extends GlobalUpdateEvent {
    private static final GlobalMediumUpdateEvent e = new GlobalMediumUpdateEvent();

    private GlobalMediumUpdateEvent() {

    }

    public static GlobalMediumUpdateEvent createNewEvent() {
        return e;
    }
}
