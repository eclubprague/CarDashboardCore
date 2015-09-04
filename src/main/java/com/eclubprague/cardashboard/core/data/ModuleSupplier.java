package com.eclubprague.cardashboard.core.data;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.eclubprague.cardashboard.core.data.database.ModuleDAO;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.custom.ClockModule;
import com.eclubprague.cardashboard.core.modules.custom.CompassModule;
import com.eclubprague.cardashboard.core.modules.custom.DeviceBatteryModule;
import com.eclubprague.cardashboard.core.modules.custom.GpsSpeedModule;

import java.io.IOException;
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
    private static final ModuleSupplier personalInstance = new ModuleSupplier() {
        @Override
        protected IParentModule createHomeScreenModule(IModuleContext moduleContext) {

            Context context = GlobalApplication.getInstance().getContext();
            IParentModule homeScreenModule = null;
            try {
                homeScreenModule = ModuleDAO.loadParentModule(context);
            } catch (IOException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Unable to load personal settings. Loading default.").setMessage(e.getMessage()).create().show();
                // should be prompt with file error
//                throw new RuntimeException(e);
            }
            if (homeScreenModule == null) {
                homeScreenModule = (IParentModule) ModuleEnum.HOMESCREEN_PARENT.newInstance();
                IParentModule obdParent = (IParentModule) ModuleEnum.OBD_PARENT.newInstance();
                obdParent.addSubmodules(new GpsSpeedModule());
                IParentModule otherParent = (IParentModule) ModuleEnum.OTHER_PARENT.newInstance();
                otherParent.addSubmodules(
                        new ClockModule(),
                        new DeviceBatteryModule(),
                        new CompassModule()
                );
                IParentModule settingsParent = (IParentModule) ModuleEnum.SETTINGS_PARENT.newInstance();
                homeScreenModule.addSubmodules(obdParent, otherParent, settingsParent);
            }
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

    public IModule findModule(IModuleContext moduleContext, ModuleId id) {
        return map.get(id);
    }

    public IParentModule findSubmenuModule(IModuleContext moduleContext, ModuleId id) {
        return (IParentModule) map.get(id);
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

    public void remove(IModule module) {
        map.remove(module.getId());
    }

    public IParentModule getHomeScreenModule(IModuleContext moduleContext) {
        if (homeScreenModule == null) {
            homeScreenModule = createHomeScreenModule(moduleContext);
        }
        return (IParentModule) homeScreenModule;
    }

    public List<IModule> getAll() {
        return new ArrayList<>(map.values());
    }

    public void save(IModuleContext moduleContext) throws IOException {
        ModuleDAO.saveParentModuleAsync(moduleContext.getContext(), getHomeScreenModule(moduleContext));
    }

    public void clear() {
        homeScreenModule = null;
    }

    protected void putRecursively(IParentModule parentModule) {
        Log.d(TAG, "Putting in BaseSupplier: " + parentModule.getClass().getSimpleName() + ": " + parentModule.getTitle().getString(GlobalApplication.getInstance().getContext()));
        put(parentModule);
        for (IModule m : parentModule.getSubmodules()) {
            if (m instanceof IParentModule) {
                putRecursively((IParentModule) m);
            } else {
//                Log.d(TAG, "Putting in BaseSupplier: " + m.getClass().getSimpleName());
                put(m);
            }
        }
    }

    protected abstract IParentModule createHomeScreenModule(IModuleContext moduleContext);
}
