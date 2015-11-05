package com.eclubprague.cardashboard.core.data.modules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 03.09.2015.
 */
public class ModuleInfoContainer extends ModuleInfo {

    private List<ModuleInfo> submodules;

    public ModuleInfoContainer( ModuleEnum module ) {
        super( module );
        submodules = new ArrayList<>();
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
        ModuleInfoContainer obdContainer = new ModuleInfoContainer( ModuleEnum.OBD_PARENT );
        containers.add( obdContainer );
        obdContainer.add(
                new ModuleInfo( ModuleEnum.GPS_SPEED ),
                new ModuleInfo( ModuleEnum.OBD_RPM )
        );
        ModuleInfoContainer otherContainer = new ModuleInfoContainer( ModuleEnum.OTHER_PARENT );
        containers.add( otherContainer );
        otherContainer.add(
                new ModuleInfo( ModuleEnum.FOLDER ),
                new ModuleInfo( ModuleEnum.CLOCK ),
                new ModuleInfo( ModuleEnum.CLOCK_SECONDS ),
                new ModuleInfo( ModuleEnum.DEVICE_BATTERY ),
                new ModuleInfo( ModuleEnum.COMPASS )
        );
        ModuleInfoContainer settingsContainer = new ModuleInfoContainer( ModuleEnum.SETTINGS_PARENT );
        containers.add( settingsContainer );
        settingsContainer.add(
                new ModuleInfo( ModuleEnum.TEST ),
                new ModuleInfo( ModuleEnum.SETTINGS_THEME )
        );
        ModuleInfoContainer shortcutContainer = new ModuleInfoContainer( ModuleEnum.SHORTCUT_PARENT );
        containers.add( shortcutContainer );
        shortcutContainer.add(
                new ModuleInfo( ModuleEnum.SHORTCUT ),
                new ModuleInfo( ModuleEnum.SHORTCUT_CUSTOM ),
                new ModuleInfo( ModuleEnum.SHORTCUT_MAPS_GOOGLE )
        );
        return containers;
    }
}
