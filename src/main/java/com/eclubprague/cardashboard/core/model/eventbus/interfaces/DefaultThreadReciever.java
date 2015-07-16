package com.eclubprague.cardashboard.core.model.eventbus.interfaces;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by Michael on 5. 4. 2015.
 * <p/>
 * Receiver interface for default thread (based on the posting thread) events.
 * Pay attention to the method annotation, it must be replicated in implementation!
 */
public interface DefaultThreadReciever<T extends Event> {

    @Subscribe(threadMode = ThreadMode.PostThread)
    void onEvent(T event);
}
