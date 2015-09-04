package com.eclubprague.cardashboard.core.utils;

import com.eclubprague.cardashboard.core.data.modules.ModuleType;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;

/**
 * Created by Michael on 04.09.2015.
 */
public class ModuleUtils {
    private static final String TAG = "ModuleUtils";

    public static IParentModule forEach(IParentModule parentModule, Action action, ModuleType moduleType) {
//        Log.d(TAG, "Entering: " + parentModule);
        if (moduleType.equals(ModuleType.PARENT)) {
//            Log.d(TAG, "Performing action: " + parentModule);
            parentModule = (IParentModule) action.performAction(parentModule);
        }
        for (int i = 0; i < parentModule.getSubmodules().size(); i++) {
            IModule module = parentModule.getSubmodules().get(i);
//            Log.d(TAG, "Checking: " + module);
//            Log.d(TAG, "Checking parent: " + module);
            if (module.getModuleEnum().getModuleType().equals(ModuleType.PARENT)) {
                module = forEach((IParentModule) module, action, moduleType);
            } else if (module.getModuleEnum().getModuleType().equals(moduleType)) {
//                Log.d(TAG, "Performing action: " + module);
                module = action.performAction(module);
            }
        }
        return parentModule;
    }

    public static IParentModule forEachDeepCopy(IParentModule parentModule, Action action, ModuleType moduleType) {
//        Log.d(TAG, "Entering: " + parentModule);
        parentModule = parentModule.copy();
        if (moduleType.equals(ModuleType.PARENT)) {
            parentModule = (IParentModule) action.performAction(parentModule);
        }
        for (int i = 0; i < parentModule.getSubmodules().size(); i++) {
            IModule module = parentModule.getSubmodules().get(i);
//            Log.d(TAG, "Checking: " + module);
//            Log.d(TAG, "Checking parent: " + module);
            if (module.getModuleEnum().getModuleType().equals(ModuleType.PARENT)) {
                IParentModule editedModule = forEachDeepCopy((IParentModule) module, action, moduleType);
                parentModule.getSubmodules().set(i, editedModule);
            } else if (module.getModuleEnum().getModuleType().equals(moduleType)) {
//                Log.d(TAG, "Performing action: " + module);
                IModule editedModule = action.performAction(module);
                parentModule.getSubmodules().set(i, editedModule);
            }
        }
        return parentModule;
    }

    public interface Action {
        IModule performAction(IModule module);
    }


}
