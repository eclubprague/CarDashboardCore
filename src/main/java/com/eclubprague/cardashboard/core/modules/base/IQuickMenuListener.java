package com.eclubprague.cardashboard.core.modules.base;

/**
 * Created by Michael on 27. 7. 2015.
 * <p>
 * Listener interface for quickmenu actions.
 */
public interface IQuickMenuListener {
    void onCancel(IModuleContext moduleContext);

    void onDelete(IModuleContext moduleContext);

    void onMove(IModuleContext moduleContext);

    void onMore(IModuleContext moduleContext);
}
