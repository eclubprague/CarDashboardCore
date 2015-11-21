package com.eclubprague.cardashboard.core.data.modules;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.fragments.ApplicationListDialogFragment;
import com.eclubprague.cardashboard.core.fragments.CustomShortcutDialogFragment;
import com.eclubprague.cardashboard.core.fragments.GmapsShortcutDialogFragment;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.predefined.AppShortcutModule;
import com.eclubprague.cardashboard.core.modules.predefined.GmapsShortcutModule;
import com.eclubprague.cardashboard.core.modules.predefined.SimpleShortcutModule;

/**
 * Created by Michael on 20.11.2015.
 */
public enum ModuleCreator {
    DEFAULT {
        @Override
        public void create( IModuleContext context, Class<? extends IModule> moduleClass, OnModuleCreateListener onModuleCreateListener ) throws InstantiationException {
            onModuleCreateListener.onModuleCreated( loadModule( moduleClass ) );
        }
    },
    SUPPORT_SKIP {
        @Override
        public void create( IModuleContext context, Class<? extends IModule> moduleClass, OnModuleCreateListener onModuleCreateListener ) throws InstantiationException {
        }
    },
    APP_SHORTCUT {
        @Override
        public void create( final IModuleContext context, Class<? extends IModule> moduleClass, final OnModuleCreateListener onModuleCreateListener ) throws InstantiationException {
            ApplicationListDialogFragment.newInstance( context, new ApplicationListDialogFragment.OnApplicationSelectedListener() {
                @Override
                public void onApplicationSelected( ApplicationInfo applicationInfo ) {
                    PackageManager pm = context.getContext().getPackageManager();
                    StringResource titleResource = StringResource.fromString( applicationInfo.loadLabel( pm ).toString() );
                    IconResource iconResource = IconResource.fromDrawable( applicationInfo.loadIcon( pm ) );
                    Intent intent = pm.getLaunchIntentForPackage( applicationInfo.packageName );
                    AppShortcutModule shortcutModule = new AppShortcutModule( titleResource, iconResource, intent );
                    onModuleCreateListener.onModuleCreated( shortcutModule );
                }
            } ).show( context.getActivity().getFragmentManager(), context.getResources().getString( R.string.title_application_list ) );
        }
    },
    CUSTOM_INTENT {
        @Override
        public void create( IModuleContext context, Class<? extends IModule> moduleClass, final OnModuleCreateListener onModuleCreateListener ) throws InstantiationException {

            CustomShortcutDialogFragment.newInstance( new CustomShortcutDialogFragment.OnShortcutCreatedListener() {
                @Override
                public void onShortcutCreated( String title, Intent intent ) {
                    StringResource titleResource = StringResource.fromString( title );
                    IconResource iconResource = SimpleShortcutModule.ICON_RESOURCE;
                    SimpleShortcutModule shortcutModule = new SimpleShortcutModule( titleResource, iconResource, intent );
                    onModuleCreateListener.onModuleCreated( shortcutModule );
                }
            } ).show( context.getActivity().getFragmentManager(), context.getResources().getString( R.string.title_custom_intent ) );
        }
    },
    GMAPS_SHORTCUT {
        @Override
        public void create( IModuleContext context, Class<? extends IModule> moduleClass, final OnModuleCreateListener onModuleCreateListener ) throws InstantiationException {

            GmapsShortcutDialogFragment.newInstance( context.getContext(), new GmapsShortcutDialogFragment.OnIntentCreatedListener() {
                @Override
                public void onIntentCreated( IconResource iconResource, StringResource titleResource, Intent intent ) {
                    GmapsShortcutModule shortcutModule = new GmapsShortcutModule( titleResource, iconResource, intent );
                    onModuleCreateListener.onModuleCreated( shortcutModule );
                }
            } ).show( context.getActivity().getFragmentManager(), context.getResources().getString( R.string.title_custom_gmaps ) );
        }
    };

    /**
     * Returns default implementation of given module class. In case module requires interaction, it invokes the required tools.
     *
     * @param context     Context for resources
     * @param moduleClass module class
     * @return default module
     */
    abstract public void create( IModuleContext context, Class<? extends IModule> moduleClass, OnModuleCreateListener onModuleCreateListener ) throws InstantiationException;


    protected static IModule loadModule( Class<? extends IModule> moduleClass ) throws InstantiationException {
        try {
            return moduleClass.newInstance();
        } catch ( IllegalAccessException e ) {
            throw new InstantiationError( e.getMessage() );
        }
    }

    public interface OnModuleCreateListener {
        void onModuleCreated( IModule module );
    }
}
