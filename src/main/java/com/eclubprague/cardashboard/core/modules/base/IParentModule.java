package com.eclubprague.cardashboard.core.modules.base;

import java.util.List;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * An interface for submenu modules (modules containing more modules).
 */
public interface IParentModule extends IModule {
    List<IModule> getSubmodules(IModuleContext moduleContext);

    IParentModule addSubmodules(IModule... modules);

    IParentModule addSubmodules(List<IModule> modules);
}
