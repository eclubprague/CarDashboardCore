package com.eclubprague.cardashboard.core.modules.base;

import java.util.List;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * An interface for submenu modules (modules containing more modules).
 */
public interface ISubmenuModule extends IModule {
    List<IModule> getSubmodules(IModuleContext moduleContext);

    public ISubmenuModule addSubmodules(IModule... modules);

    public ISubmenuModule addSubmodules(List<IModule> modules);
}
