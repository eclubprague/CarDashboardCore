package com.eclubprague.cardashboard.core.modules.base;

import java.util.List;

/**
 * Created by Michael on 27. 7. 2015.
 * <p>
 * Listener interface for quickmenu actions.
 */
public interface IQuickMenuListener {
    void onEvent(ModuleEvent event, IModuleContext moduleContext);

    List<ModuleEvent> getAvailableActions();
}
