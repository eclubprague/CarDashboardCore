package com.eclubprague.cardashboard.core.data.modules;

import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 03.09.2015.
 */
public class ModuleInfo {
    private static int globalId = 0;
    private final ModuleEnum module;
    private final int id;

    public ModuleInfo(ModuleEnum module) {
        this.module = module;
        this.id = globalId++;
    }

    public int getId() {
        return id;
    }

    public ModuleEnum getModule() {
        return module;
    }

    public StringResource getTitle() {
        return module.getTitle();
    }

    public IconResource getIcon() {
        return module.getIcon();
    }
}
