package com.eclubprague.cardashboard.core.utils;

import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;

/**
 * Created by Michael on 04.09.2015.
 */
public class ModuleUtils {
    private static final String TAG = "ModuleUtils";

    public static IParentModule forEach(IParentModule parentModule, Action action, Class<? extends IModule> moduleClass) {
//        Log.d(TAG, "Entering: " + parentModule);
        if (IParentModule.class.isAssignableFrom( moduleClass )) {
//            Log.d(TAG, "Performing action: " + parentModule);
            parentModule = (IParentModule) action.performAction(parentModule);
        }
        for (int i = 0; i < parentModule.getSubmodules().size(); i++) {
            IModule module = parentModule.getSubmodules().get(i);
//            Log.d(TAG, "Checking: " + module);
//            Log.d(TAG, "Checking parent: " + module);
            if (IParentModule.class.isAssignableFrom( module.getClass() )) {
                module = forEach((IParentModule) module, action, moduleClass);
            } else if (moduleClass.isAssignableFrom( module.getClass() )) {
//                Log.d(TAG, "Performing action: " + module);
                module = action.performAction(module);
            }
        }
        return parentModule;
    }

    public static IParentModule forEachDeepCopy(IParentModule parentModule, Action action, Class<? extends IModule> moduleClass) {
//        Log.d(TAG, "Entering: " + parentModule);
        parentModule = parentModule.copy();
        if (IParentModule.class.isAssignableFrom( moduleClass )) {
            parentModule = (IParentModule) action.performAction(parentModule);
        }
        for (int i = 0; i < parentModule.getSubmodules().size(); i++) {
            IModule module = parentModule.getSubmodules().get(i);
//            Log.d(TAG, "Checking: " + module);
//            Log.d(TAG, "Checking parent: " + module);
            if (IParentModule.class.isAssignableFrom( module.getClass() )) {
                IParentModule editedModule = forEachDeepCopy((IParentModule) module, action, moduleClass);
                parentModule.getSubmodules().set(i, editedModule);
            } else if (moduleClass.isAssignableFrom( module.getClass() )) {
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
