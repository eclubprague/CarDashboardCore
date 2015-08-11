package com.eclubprague.cardashboard.core.model.eventbus.events;

/**
 * Created by Michael on 11. 8. 2015.
 */
public class GlobalExtraSlowUpdate extends GlobalUpdateEvent {
    private static final GlobalExtraSlowUpdate e = new GlobalExtraSlowUpdate();

    private GlobalExtraSlowUpdate() {

    }

    public static GlobalExtraSlowUpdate createNewEvent() {
        return e;
    }
}
