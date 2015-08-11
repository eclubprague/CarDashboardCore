package com.eclubprague.cardashboard.core.model.eventbus.interfaces;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by Michael on 5. 4. 2015.
 * <p/>
 * Receiver interface for async thread events.
 * Pay attention to the method annotation, it must be replicated in implementation!
 */
public interface AsyncThreadReceiver<T extends Event> {

    @Subscribe(threadMode = ThreadMode.Async)
    void onEventAsync(T event);
}
