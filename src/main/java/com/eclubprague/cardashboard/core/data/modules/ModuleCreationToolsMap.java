package com.eclubprague.cardashboard.core.data.modules;

import android.util.Log;

import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.custom.settings.ThemeSwitchModule;
import com.eclubprague.cardashboard.core.modules.predefined.*;
import com.eclubprague.cardashboard.core.modules.custom.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 20.11.2015.
 */
public class ModuleCreationToolsMap {
    private static ModuleCreationToolsMap INSTANCE = null;

    private static final String TAG = ModuleCreationToolsMap.class.getSimpleName();

    private final Map<Class<? extends IModule>, ModuleLoader> loaderMap = new HashMap<>();
    private final Map<Class<? extends IModule>, ModuleCreator> creatorMap = new HashMap<>();

    private ModuleCreationToolsMap() {
        // register all
        register(BackModule.class, ModuleLoader.SUPPORT_SKIP, ModuleCreator.SUPPORT_SKIP);
        register(EmptyModule.class, ModuleLoader.DEFAULT, ModuleCreator.DEFAULT);
        register(SimpleParentModule.class, ModuleLoader.PARENT, ModuleCreator.DEFAULT);
        register(SimpleShortcutModule.class, ModuleLoader.INTENT, ModuleCreator.CUSTOM_INTENT);
        register(AppShortcutModule.class, ModuleLoader.APP_SHORTCUT, ModuleCreator.APP_SHORTCUT);
        register(GmapsShortcutModule.class, ModuleLoader.INTENT, ModuleCreator.GMAPS_SHORTCUT);

        register(ClockModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT);
        register(ClockSecondsModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT);
        register(CompassModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT);
        register(DeviceBatteryModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT);
        register(FolderModule.class, ModuleLoader.PARENT, ModuleCreator.DEFAULT);
        register(GpsSpeedModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT);
        register(LightButtonModule.class, ModuleLoader.DEFAULT, ModuleCreator.DEFAULT);
        register(ObdRpmModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT);
        register(TemperatureModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT);

        register(ThemeSwitchModule.class, ModuleLoader.DISPLAY, ModuleCreator.DEFAULT);
    }

    public static ModuleCreationToolsMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModuleCreationToolsMap();
        }
        Log.d(TAG, "Getting instance: " + INSTANCE);
        return INSTANCE;
    }

    public ModuleCreationToolsMap register(Class<? extends IModule> clazz, ModuleLoader moduleLoader, ModuleCreator moduleCreator) {
        Log.d(TAG, "registering class: " + clazz.getName());
        return register(clazz, moduleLoader).register(clazz, moduleCreator);
    }

    private ModuleCreationToolsMap register(Class<? extends IModule> clazz, ModuleLoader moduleLoader) {
        Log.d(TAG, "moduleLoader = " + moduleLoader);
        loaderMap.put(clazz, moduleLoader);
        Log.d(TAG, "inserted = " + loaderMap.get(clazz));
        return this;
    }

    public ModuleLoader getLoader(Class<? extends IModule> clazz) {
        ModuleLoader loader = loaderMap.get(clazz);
        if (loader == null) {
            Log.d(TAG, "returning null loader for: " + clazz.getName());
            for (Map.Entry<Class<? extends IModule>, ModuleLoader> entry:
                 loaderMap.entrySet()) {
                Log.d(TAG, "entry: " + entry);
            }
        }
        return loader;
    }

    private ModuleCreationToolsMap register(Class<? extends IModule> clazz, ModuleCreator moduleCreator) {
        Log.d(TAG, "moduleCreator = " + moduleCreator);
        creatorMap.put(clazz, moduleCreator);
        Log.d(TAG, "inserted = " + creatorMap.get(clazz));
        return this;
    }

    public ModuleCreator getCreator(Class<? extends IModule> clazz) {
        ModuleCreator creator = creatorMap.get(clazz);
        if (creator == null) {
            Log.d(TAG, "returning null creator for: " + clazz.getName());
        }
        return creator;
    }
}
