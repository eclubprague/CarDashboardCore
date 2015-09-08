package com.eclubprague.cardashboard.core.data.modules;

import android.content.Intent;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.custom.ClockModule;
import com.eclubprague.cardashboard.core.modules.custom.ClockSecondsModule;
import com.eclubprague.cardashboard.core.modules.custom.CompassModule;
import com.eclubprague.cardashboard.core.modules.custom.DeviceBatteryModule;
import com.eclubprague.cardashboard.core.modules.custom.ErrorTester;
import com.eclubprague.cardashboard.core.modules.custom.FolderModule;
import com.eclubprague.cardashboard.core.modules.custom.GpsSpeedModule;
import com.eclubprague.cardashboard.core.modules.custom.ObdRpmModule;
import com.eclubprague.cardashboard.core.modules.predefined.BackModule;
import com.eclubprague.cardashboard.core.modules.predefined.EmptyModule;
import com.eclubprague.cardashboard.core.modules.predefined.SimpleParentModule;
import com.eclubprague.cardashboard.core.modules.predefined.SimpleShortcutModule;

/**
 * Created by Michael on 03.09.2015.
 */
public enum ModuleEnum {
    EMPTY(ModuleType.DEFINED) {
        @Override
        protected void init() {
            titleResource = EmptyModule.TITLE_RESOURCE;
            iconResource = EmptyModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new EmptyModule();
        }

    },
    CLOCK(ModuleType.DEFINED) {
        @Override
        protected void init() {
            titleResource = ClockModule.TITLE_RESOURCE;
            iconResource = ClockModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new ClockModule();
        }
    },
    CLOCK_SECONDS(ModuleType.DEFINED) {
        @Override
        protected void init() {
            titleResource = ClockSecondsModule.TITLE_RESOURCE;
            iconResource = ClockSecondsModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new ClockSecondsModule();
        }
    },
    COMPASS(ModuleType.DEFINED) {
        @Override
        protected void init() {
            titleResource = CompassModule.TITLE_RESOURCE;
            iconResource = CompassModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new CompassModule();
        }
    },
    DEVICE_BATTERY(ModuleType.DEFINED) {
        @Override
        protected void init() {
            titleResource = DeviceBatteryModule.TITLE_RESOURCE;
            iconResource = DeviceBatteryModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new DeviceBatteryModule();
        }
    },
    FOLDER(ModuleType.PARENT) {
        @Override
        protected void init() {
            titleResource = FolderModule.TITLE_RESOURCE;
            iconResource = FolderModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new FolderModule();
        }
    },
    GPS_SPEED(ModuleType.DEFINED) {
        @Override
        protected void init() {
            titleResource = GpsSpeedModule.TITLE_RESOURCE;
            iconResource = GpsSpeedModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new GpsSpeedModule();
        }
    },
    OBD_RPM(ModuleType.DEFINED) {
        @Override
        protected void init() {
            titleResource = ObdRpmModule.TITLE_RESOURCE;
            iconResource = ObdRpmModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new ObdRpmModule();
        }
    },
    SHORTCUT(ModuleType.SHORTCUT) {
        @Override
        protected void init() {
            titleResource = StringResource.fromResourceId(R.string.module_shortcuts_new);
            iconResource = IconResource.fromResourceId(R.drawable.ic_exit_to_app_black_24dp);
        }

        @Override
        public IModule newInstance() {
            throw new UnsupportedOperationException("This method is not supported for given ModuleEnum: " + this.name());
        }

        @Override
        public IModule newInstance(StringResource titleResource, IconResource iconResource, Intent intent) {
            return new SimpleShortcutModule(this, titleResource, iconResource, intent);
        }
    },
    OBD_PARENT(ModuleType.PARENT) {
        @Override
        protected void init() {
            titleResource = StringResource.fromResourceId(R.string.module_obd_title);
            iconResource = IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp);
        }

        @Override
        public IModule newInstance() {
            return new SimpleParentModule(this, getTitle(), getIcon());
        }
    },
    OTHER_PARENT(ModuleType.PARENT) {
        @Override
        protected void init() {
            titleResource = StringResource.fromResourceId(R.string.module_others_title);
            iconResource = IconResource.fromResourceId(R.drawable.ic_apps_black_24dp);
        }

        @Override
        public IModule newInstance() {
            return new SimpleParentModule(this, getTitle(), getIcon());
        }
    },
    SETTINGS_PARENT(ModuleType.PARENT) {
        @Override
        protected void init() {
            titleResource = StringResource.fromResourceId(R.string.module_settings_title);
            iconResource = IconResource.fromResourceId(R.drawable.ic_settings_black_24dp);
        }

        @Override
        public IModule newInstance() {
            return new SimpleParentModule(this, getTitle(), getIcon());
        }
    },
    SHORTCUT_PARENT(ModuleType.PARENT) {
        @Override
        protected void init() {
            titleResource = StringResource.fromResourceId(R.string.module_shortcuts_title);
            iconResource = IconResource.fromResourceId(R.drawable.ic_exit_to_app_black_24dp);
        }

        @Override
        public IModule newInstance() {
            return new SimpleParentModule(this, getTitle(), getIcon());
        }
    },
    HOMESCREEN_PARENT(ModuleType.PARENT) {
        @Override
        protected void init() {
            titleResource = StringResource.fromResourceId(R.string.module_home_title);
            iconResource = IconResource.fromResourceId(R.drawable.ic_home_black_24dp);
        }

        @Override
        public IModule newInstance() {
            return new SimpleParentModule(this, getTitle(), getIcon());
        }
    },
    BACK(ModuleType.SUPPORT_SKIP) {
        @Override
        protected void init() {
            titleResource = BackModule.TITLE_RESOURCE;
            iconResource = BackModule.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            throw new UnsupportedOperationException("This method is not supported for given ModuleEnum: " + this.name());
        }
    },
    TEST(ModuleType.DEFINED) {
        @Override
        protected void init() {
            titleResource = ErrorTester.TITLE_RESOURCE;
            iconResource = ErrorTester.ICON_RESOURCE;
        }

        @Override
        public IModule newInstance() {
            return new ErrorTester();
        }
    };

    protected StringResource titleResource;
    protected IconResource iconResource;
    private final ModuleType moduleType;

    ModuleEnum(ModuleType moduleType) {
        this.moduleType = moduleType;
        init();
    }

    abstract protected void init();

    abstract public IModule newInstance();

    public IModule newInstance(StringResource titleResource, IconResource iconResource) {
        IModule m = newInstance();
        m.setTitle(titleResource);
        m.setIcon(iconResource);
        return m;
    }

    public IModule newInstance(StringResource titleResource, IconResource iconResource, Intent intent) {
        throw new UnsupportedOperationException("This method is not supported for given ModuleEnum: " + this.name());
    }

    public StringResource getTitle() {
        return titleResource;
    }

    public IconResource getIcon() {
        return iconResource;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }
}
