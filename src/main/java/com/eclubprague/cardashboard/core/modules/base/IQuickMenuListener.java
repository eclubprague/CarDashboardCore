package com.eclubprague.cardashboard.core.modules.base;

import com.eclubprague.cardashboard.core.views.ModuleView;

import java.util.Set;

/**
 * Created by Michael on 27. 7. 2015.
 * <p>
 * Listener interface for quickmenu actions.
 */
public interface IQuickMenuListener {
    void onEvent(ModuleEvent event, ModuleView moduleView, IModuleContext moduleContext);

    Set<ModuleEvent> getAvailableActions();
}
