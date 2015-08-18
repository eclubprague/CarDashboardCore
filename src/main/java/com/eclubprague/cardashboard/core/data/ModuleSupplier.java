package com.eclubprague.cardashboard.core.data;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.TestDisplayModule;
import com.eclubprague.cardashboard.core.modules.TestSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.AbstractParentModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.custom.ClockModule;
import com.eclubprague.cardashboard.core.modules.custom.DeviceBatteryModule;
import com.eclubprague.cardashboard.core.modules.custom.GoogleMapsModule;
import com.eclubprague.cardashboard.core.modules.custom.GpsSpeedModule;
import com.eclubprague.cardashboard.core.modules.predefined.SimpleParentModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Supplies module hierarchy. Currently for testing purposes only.
 */
abstract public class ModuleSupplier {
    private static final String TAG = ModuleSupplier.class.getSimpleName();

    private static final ModuleSupplier baseInstance = new ModuleSupplier() {
        @Override
        protected IParentModule createHomeScreenModule(IModuleContext moduleContext) {
            IParentModule homeScreenModule = homeScreenModule();
            IParentModule obdParent = new SimpleParentModule(
                    StringResource.fromString("OBD"),
                    IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp));
            obdParent.addSubmodules(new TestDisplayModule(
                    StringResource.fromString("Click counter"),
                    IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
                    StringResource.fromString("clicks")
            ), new GpsSpeedModule());
            IParentModule otherParent = new SimpleParentModule(
                    StringResource.fromString("Other"),
                    IconResource.fromResourceId(R.drawable.ic_open_with_black_24dp));
            otherParent.addSubmodules(
                    new ClockModule(),
                    new DeviceBatteryModule()
            );
            IParentModule settingsParent = new SimpleParentModule(
                    StringResource.fromString("Settings"),
                    IconResource.fromResourceId(R.drawable.ic_settings_black_24dp));
            homeScreenModule.addSubmodules(obdParent, otherParent, settingsParent);
            putRecursively(homeScreenModule);
            return homeScreenModule;
        }
    };
    private static final ModuleSupplier personalInstance = new ModuleSupplier() {
        @Override
        protected IParentModule createHomeScreenModule(IModuleContext moduleContext) {
            IParentModule homeScreenModule = homeScreenModule();
            List<IModule> modules = new ArrayList<>();
            AbstractParentModule submenuModule;
            modules.add(new DeviceBatteryModule());
            modules.add(new GpsSpeedModule());
            modules.add(new TestSimpleModule(
                    StringResource.fromString("Settings"),
                    IconResource.fromResourceId(R.drawable.ic_settings_black_24dp)));
            modules.add(submenuModule = new SimpleParentModule(
                    StringResource.fromString("OBD"),
                    IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp)));
            modules.add(new TestSimpleModule(
                    StringResource.fromString("Voice input"),
                    IconResource.fromResourceId(R.drawable.ic_mic_black_24dp)));
            modules.add(new GoogleMapsModule());
            modules.add(new TestSimpleModule(
                    StringResource.fromString("SMS"),
                    IconResource.fromResourceId(R.drawable.ic_chat_black_24dp)));
            modules.add(new TestSimpleModule(
                    StringResource.fromString("Email"),
                    IconResource.fromResourceId(R.drawable.ic_email_black_24dp)));
//        String gm = "Google maps";
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 10; i++) {
//            sb.append(gm.charAt(i % gm.length()));
//            modules.add(new TestSimpleModule(
//                    null,
//                    homeScreenModule, StringResource.fromString(sb.toString()),
//                    IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
//                    null, null));
//        }
            List<IModule> carModules = new ArrayList<>();
            TestDisplayModule testDisplayModule;
            carModules.add(testDisplayModule = new TestDisplayModule(
                    StringResource.fromString("Speed"),
                    IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp),
                    StringResource.fromString("kmph")
            ));
            submenuModule.addSubmodules(testDisplayModule);
            carModules.add(testDisplayModule = new TestDisplayModule(
                    StringResource.fromString("Click counter"),
                    IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
                    StringResource.fromString("clicks")
            ));
            submenuModule.addSubmodules(new ClockModule());
            testDisplayModule.updateValue(Integer.toString(999));
            submenuModule.addSubmodules(testDisplayModule);
            homeScreenModule.addSubmodules(modules);
            putRecursively(homeScreenModule);
            return homeScreenModule;
        }
    };


    private static final Map<ModuleId, IModule> map = new HashMap<>();
    private IParentModule homeScreenModule;

    protected ModuleSupplier() {
    }

    public static ModuleSupplier getPersonalInstance() {
        return personalInstance;
    }

    public static ModuleSupplier getBaseInstance() {
        return baseInstance;
    }

    public IModule findModule(IModuleContext moduleContext, ModuleId id) {
        return map.get(id).setModuleContext(moduleContext);
    }

    public IParentModule findSubmenuModule(IModuleContext moduleContext, ModuleId id) {
        return (IParentModule) map.get(id).setModuleContext(moduleContext);
    }

    public void put(IModule module) {
        map.put(module.getId(), module);
    }

    public void put(IModule... modules) {
        for (IModule m : modules) {
            put(m);
        }
    }

    public void put(List<IModule> modules) {
        for (IModule m : modules) {
            put(m);
        }
    }

    public IParentModule getHomeScreenModule(IModuleContext moduleContext) {
        if (homeScreenModule == null) {
            homeScreenModule = createHomeScreenModule(moduleContext);
        }
        return (IParentModule) homeScreenModule.setModuleContext(moduleContext);
    }

    public List<IModule> getAll() {
        return new ArrayList<>(map.values());
    }

    protected void putRecursively(IParentModule parentModule) {
        put(parentModule);
        for (IModule m : parentModule.getSubmodules()) {
            if (m instanceof IParentModule) {
                putRecursively((IParentModule) m);
            } else {
                put(m);
            }
        }
    }

    protected IParentModule homeScreenModule() {
        return new SimpleParentModule(
                StringResource.fromResourceId(R.string.module_home_title),
                IconResource.fromResourceId(R.drawable.ic_home_black_24dp)
        );
    }

    protected abstract IParentModule createHomeScreenModule(IModuleContext moduleContext);
}
