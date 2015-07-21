package com.eclubprague.cardashboard.core.data;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.TestDisplayModule;
import com.eclubprague.cardashboard.core.modules.TestSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.AbstractSubmenuModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.ISubmenuModule;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.predefined.HomeScreenModule;
import com.eclubprague.cardashboard.core.modules.predefined.SimpleSubmenuModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Supplies module hierarchy. Currently for testing purposes only.
 */
public class ModuleSupplier {
    private static final String TAG = ModuleSupplier.class.getSimpleName();

    private static final ModuleSupplier instance = new ModuleSupplier();
    private final Map<ModuleId, IModule> map = new HashMap<>();

    private ModuleSupplier() {
        put(HomeScreenModule.getInstance());
        List<IModule> modules = new ArrayList<>();
        AbstractSubmenuModule submenuModule;
        modules.add(new TestSimpleModule(
                null,
                HomeScreenModule.getInstance(),
                StringResource.fromString("Settings"),
                IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
                null, null));
        modules.add(submenuModule = new SimpleSubmenuModule(
                null,
                HomeScreenModule.getInstance(),
                StringResource.fromString("OBD"),
                IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                HomeScreenModule.getInstance(),
                StringResource.fromString("Voice input"),
                IconResource.fromResourceId(R.drawable.ic_mic_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                HomeScreenModule.getInstance(),
                StringResource.fromString("Google maps"),
                IconResource.fromResourceId(R.drawable.ic_map_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                HomeScreenModule.getInstance(),
                StringResource.fromString("SMS"),
                IconResource.fromResourceId(R.drawable.ic_chat_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                HomeScreenModule.getInstance(),
                StringResource.fromString("Email"),
                IconResource.fromResourceId(R.drawable.ic_email_black_24dp),
                null, null));
        String gm = "Google maps";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(gm.charAt(i % gm.length()));
            modules.add(new TestSimpleModule(
                    null,
                    HomeScreenModule.getInstance(), StringResource.fromString(sb.toString()),
                    IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
                    null, null));
        }
        List<IModule> carModules = new ArrayList<>();
        TestDisplayModule testDisplayModule;
        carModules.add(testDisplayModule = new TestDisplayModule(
                null,
                submenuModule,
                StringResource.fromString("Speed"),
                IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp),
                null,
                null,
                StringResource.fromString("kmph")
        ));
        submenuModule.addSubmodules(testDisplayModule);
        carModules.add(testDisplayModule = new TestDisplayModule(
                null,
                submenuModule,
                StringResource.fromString("Click counter"),
                IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
                null,
                null,
                StringResource.fromString("clicks")
        ));
        testDisplayModule.onEventMainThread(new ModuleUpdateEvent(Integer.toString(999)));
        submenuModule.addSubmodules(testDisplayModule);
        put(submenuModule.getSubmodules(null));
        HomeScreenModule.getInstance().addSubmodules(modules);
        put(modules);

    }

    public static ModuleSupplier getInstance() {
        return instance;
    }

    public IModule findModule(IModuleContext moduleContext, ModuleId id) {
        return map.get(id).setModuleContext(moduleContext);
    }

    public ISubmenuModule findSubmenuModule(IModuleContext moduleContext, ModuleId id) {
        return (ISubmenuModule) map.get(id).setModuleContext(moduleContext);
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

    public ISubmenuModule getHomeScreenModule(IModuleContext moduleContext) {
        return (ISubmenuModule) HomeScreenModule.getInstance().setModuleContext(moduleContext);
    }
}
