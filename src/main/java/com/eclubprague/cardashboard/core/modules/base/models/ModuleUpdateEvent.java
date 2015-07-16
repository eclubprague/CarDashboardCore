package com.eclubprague.cardashboard.core.modules.base.models;

import com.eclubprague.cardashboard.core.model.eventbus.interfaces.Event;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Event for updating display modules. Messenger pattern.
 */
public class ModuleUpdateEvent implements Event {
    private final String value;

    public ModuleUpdateEvent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
