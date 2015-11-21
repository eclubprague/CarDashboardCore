package com.eclubprague.cardashboard.core.data.modules;

import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.IModule;

/**
 * Created by Michael on 03.09.2015.
 */
public class ModuleInfo {
    private static int globalId = 0;
    private final int id;

    private final StringResource titleResource;
    private final IconResource iconResource;
    private final Class<? extends IModule> moduleClass;

    public ModuleInfo( Class<? extends IModule> moduleClass, StringResource titleResource, IconResource iconResource ) {
        this.titleResource = titleResource;
        this.iconResource = iconResource;
        this.moduleClass = moduleClass;
        this.id = globalId++;
    }

    public int getId() {
        return id;
    }

    public ModuleCreator getCreator() {
        return ModuleCreationToolsMap.getInstance().getCreator( moduleClass );
    }

    public Class<? extends IModule> getModuleClass() {
        return moduleClass;
    }

    public StringResource getTitle() {
        return getTitle();
    }

    public IconResource getIcon() {
        return getIcon();
    }
}
