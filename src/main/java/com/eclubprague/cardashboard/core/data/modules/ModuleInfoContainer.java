package com.eclubprague.cardashboard.core.data.modules;

import android.text.TextDirectionHeuristic;

import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.custom.*;
import com.eclubprague.cardashboard.core.modules.custom.settings.ThemeSwitchModule;
import com.eclubprague.cardashboard.core.modules.predefined.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 03.09.2015.
 */
public class ModuleInfoContainer extends ModuleInfo {

    private final List<ModuleInfo> submodules = new ArrayList<>();

    public ModuleInfoContainer( Class<? extends IModule> moduleClass, StringResource titleResource, IconResource iconResource ) {
        super( moduleClass, titleResource, iconResource );
    }

    public void add( ModuleInfo moduleInfo ) {
        submodules.add( moduleInfo );
    }

    public void add( ModuleInfo... moduleInfos ) {
        for ( ModuleInfo m : moduleInfos ) {
            submodules.add( m );
        }
    }

    public ModuleInfo get( int position ) {
        return submodules.get( position );
    }

    public int getSize() {
        return submodules.size();
    }

    public static List<ModuleInfoContainer> getModules() {
        List<ModuleInfoContainer> containers = new ArrayList<>();
        ModuleInfoContainer obdContainer = new ModuleInfoContainer( SimpleParentModule.class, SimpleParentModule.OBD_TITLE_RESOURCE, SimpleParentModule.OBD_ICON_RESOURCE );
        containers.add( obdContainer );
        obdContainer.add(
                new ModuleInfo( GpsSpeedModule.class, GpsSpeedModule.TITLE_RESOURCE, GpsSpeedModule.ICON_RESOURCE ),
                new ModuleInfo( ObdRpmModule.class, ObdRpmModule.TITLE_RESOURCE, ObdRpmModule.ICON_RESOURCE )
        );
        ModuleInfoContainer otherContainer = new ModuleInfoContainer( SimpleParentModule.class, SimpleParentModule.OTHERS_TITLE_RESOURCE, SimpleParentModule.OTHERS_ICON_RESOURCE );
        containers.add( otherContainer );
        otherContainer.add(
                new ModuleInfo( FolderModule.class, FolderModule.TITLE_RESOURCE, FolderModule.ICON_RESOURCE ),
                new ModuleInfo( ClockModule.class, ClockModule.TITLE_RESOURCE, ClockModule.ICON_RESOURCE ),
                new ModuleInfo( ClockSecondsModule.class, ClockSecondsModule.TITLE_RESOURCE, ClockSecondsModule.ICON_RESOURCE ),
                new ModuleInfo( DeviceBatteryModule.class, DeviceBatteryModule.TITLE_RESOURCE, DeviceBatteryModule.ICON_RESOURCE ),
                new ModuleInfo( CompassModule.class, CompassModule.TITLE_RESOURCE, CompassModule.ICON_RESOURCE ),
                new ModuleInfo( LightButtonModule.class, LightButtonModule.TITLE_RESOURCE, LightButtonModule.ICON_RESOURCE )
        );
        ModuleInfoContainer settingsContainer = new ModuleInfoContainer( SimpleParentModule.class, SimpleParentModule.SETTINGS_TITLE_RESOURCE, SimpleParentModule.SETTINGS_ICON_RESOURCE );
        containers.add( settingsContainer );
        settingsContainer.add(
                new ModuleInfo( ErrorTester.class, ErrorTester.TITLE_RESOURCE, ErrorTester.ICON_RESOURCE ),
                new ModuleInfo( ThemeSwitchModule.class, ThemeSwitchModule.TITLE_RESOURCE, ThemeSwitchModule.ICON_RESOURCE )
        );
        ModuleInfoContainer shortcutContainer = new ModuleInfoContainer( SimpleParentModule.class, SimpleParentModule.SHORTCUT_TITLE_RESOURCE, SimpleParentModule.SHORTCUT_ICON_RESOURCE );
        containers.add( shortcutContainer );
        shortcutContainer.add(
                new ModuleInfo( AppShortcutModule.class, AppShortcutModule.TITLE_RESOURCE, AppShortcutModule.ICON_RESOURCE ),
                new ModuleInfo( SimpleShortcutModule.class, SimpleShortcutModule.TITLE_RESOURCE, SimpleShortcutModule.ICON_RESOURCE ),
                new ModuleInfo( GmapsShortcutModule.class, GmapsShortcutModule.TITLE_RESOURCE, GmapsShortcutModule.ICON_RESOURCE )
        );
        return containers;
    }
}
