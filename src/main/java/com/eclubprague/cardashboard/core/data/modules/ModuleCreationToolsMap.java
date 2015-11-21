package com.eclubprague.cardashboard.core.data.modules;

import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.custom.settings.ThemeSwitchModule;
import com.eclubprague.cardashboard.core.modules.predefined.*;
import com.eclubprague.cardashboard.core.modules.custom.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 20.11.2015.
 */
public enum ModuleCreationToolsMap {
    INSTANCE;

    private final Map<Class<? extends IModule>, ModuleLoader> loaderMap = new HashMap<>();
    private final Map<Class<? extends IModule>, ModuleCreator> creatorMap = new HashMap<>();

    ModuleCreationToolsMap() {
        // register all
        register( BackModule.class, ModuleLoader.SUPPORT_SKIP, ModuleCreator.SUPPORT_SKIP );
        register( EmptyModule.class, ModuleLoader.DEFAULT, ModuleCreator.DEFAULT );
        register( SimpleParentModule.class, ModuleLoader.PARENT, ModuleCreator.DEFAULT );
        register( SimpleShortcutModule.class, ModuleLoader.INTENT, ModuleCreator.CUSTOM_INTENT );
        register( AppShortcutModule.class, ModuleLoader.APP_SHORTCUT, ModuleCreator.APP_SHORTCUT );
        register( GmapsShortcutModule.class, ModuleLoader.INTENT, ModuleCreator.GMAPS_SHORTCUT );

        register( ClockModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT );
        register( ClockSecondsModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT );
        register( CompassModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT );
        register( DeviceBatteryModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT );
        register( FolderModule.class, ModuleLoader.PARENT, ModuleCreator.DEFAULT );
        register( GpsSpeedModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT );
        register( LightButtonModule.class, ModuleLoader.DEFAULT, ModuleCreator.DEFAULT );
        register( ObdRpmModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT );
        register( TemperatureModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT );

        register( ThemeSwitchModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT );
    }

    public static ModuleCreationToolsMap getInstance() {
        return INSTANCE;
    }

    public ModuleCreationToolsMap register( Class<? extends IModule> clazz, ModuleLoader moduleLoader, ModuleCreator moduleCreator ) {
        return register( clazz, moduleLoader ).register( clazz, moduleCreator );
    }

    private ModuleCreationToolsMap register( Class<? extends IModule> clazz, ModuleLoader moduleLoader ) {
        loaderMap.put( clazz, moduleLoader );
        return this;
    }

    public ModuleLoader getLoader( Class<? extends IModule> clazz ) {
        return loaderMap.get( clazz );
    }

    private ModuleCreationToolsMap register( Class<? extends IModule> clazz, ModuleCreator moduleCreator ) {
        creatorMap.put( clazz, moduleCreator );
        return this;
    }

    public ModuleCreator getCreator( Class<? extends IModule> clazz ) {
        return creatorMap.get( clazz );
    }
}
