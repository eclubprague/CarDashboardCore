package com.eclubprague.cardashboard.core.modules.base;

/**
 * Created by Michael on 27. 7. 2015.
 * <p>
 * Listener interface for module card actions.
 */
public interface IModuleListener {

    void onClickEvent(IModuleContext context);

    void onLongClickEvent(IModuleContext context);
}
