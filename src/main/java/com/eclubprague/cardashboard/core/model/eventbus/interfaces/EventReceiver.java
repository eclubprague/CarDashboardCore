package com.eclubprague.cardashboard.core.model.eventbus.interfaces;

/**
 * Created by Michael on 5. 4. 2015.
 * <p/>
 */
public interface EventReceiver<T extends Event> {

    void onEventReceived(T event);
}
