package com.eclubprague.cardashboard.core.model.interfaces;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Iterface for updatable classes/objects.
 */
public interface IUpdatable {
    void onUpdate(Context context, Bundle data);
}
