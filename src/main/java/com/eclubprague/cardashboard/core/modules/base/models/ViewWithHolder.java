package com.eclubprague.cardashboard.core.modules.base.models;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Michael on 22. 7. 2015.
 * <p/>
 * Messenger for view and its holder.
 */
public class ViewWithHolder<T extends View> {
    public final T view;
    public final ViewGroup holder;

    public ViewWithHolder(T view, ViewGroup holder) {
        this.view = view;
        this.holder = holder;
    }
}
