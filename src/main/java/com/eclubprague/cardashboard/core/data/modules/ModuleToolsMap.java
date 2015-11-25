package com.eclubprague.cardashboard.core.data.modules;

import com.eclubprague.cardashboard.core.modules.base.IModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 20.11.2015.
 */
public enum ModuleToolsMap {
    INSTANCE;

    private final Map<Class<? extends IModule>, ModuleLoader> loaderMap = new HashMap<>();
    private final Map<Class<? extends IModule>, ModuleCreator> creatorMap = new HashMap<>();

    ModuleToolsMap() {
        // register all
    }

    public static ModuleToolsMap getInstance() {
        return INSTANCE;
    }

    public ModuleToolsMap register( Class<? extends IModule> clazz, ModuleLoader moduleLoader ) {
        loaderMap.put( clazz, moduleLoader );
        return this;
    }

    public ModuleLoader getLoader( Class<? extends IModule> clazz ) {
        return loaderMap.get( clazz );
    }

    public ModuleToolsMap register( Class<? extends IModule> clazz, ModuleCreator moduleCreator ) {
        creatorMap.put( clazz, moduleCreator );
        return this;
    }

    public ModuleCreator getCreator( Class<? extends IModule> clazz ) {
        return creatorMap.get( clazz );
    }
}
