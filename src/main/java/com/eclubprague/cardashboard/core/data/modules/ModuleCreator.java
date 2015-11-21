package com.eclubprague.cardashboard.core.data.modules;

import android.content.Context;

import com.eclubprague.cardashboard.core.modules.base.IModule;

/**
 * Created by Michael on 20.11.2015.
 */
public enum ModuleCreator {
    DEFAULT {
        @Override
        public IModule create( Context context, String className ) throws ClassNotFoundException {
            return loadModule( className );
        }
    };

    /**
     * Returns default implementation of given module class. In case module requires interaction, it invokes the required tools.
     *
     * @param context   Context for resources
     * @param className module class name
     * @return default module
     */
    abstract public IModule create( Context context, String className ) throws ClassNotFoundException;


    protected static IModule loadModule( String className ) throws ClassNotFoundException {
        try {
            Class<IModule> moduleClass = (Class<IModule>) ClassLoader.getSystemClassLoader().loadClass( className );
            return moduleClass.newInstance();
        } catch ( IllegalAccessException | InstantiationException e ) {
            throw new ClassNotFoundException( e.getMessage(), e );
        }
    }
}
