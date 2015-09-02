package com.eclubprague.cardashboard.core.modules.base;

import com.eclubprague.cardashboard.core.views.ModuleView;

/**
 * Created by Michael on 27. 7. 2015.
 * <p>
 * Listener interface for module card actions.
 */
public interface IModuleListener {

    void onClickEvent(IModuleContext context, ModuleView moduleView);

    void onLongClickEvent(IModuleContext context, ModuleView moduleView);
}
