package com.eclubprague.cardashboard.core.data.modules;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Michael on 20.11.2015.
 */
public enum ModuleLoader {

    DEFAULT {
        @Override
        public IModule load( Context context, JSONObject jsonObject ) throws JSONException {
            return createAndFillModule( context, jsonObject );
        }

        @Override
        public JSONObject save( Context context, IModule module ) throws JSONException {
            return fillJsonObject( new JSONObject(), context, module );
        }
    },
    PARENT {
        @Override
        public IModule load( Context context, JSONObject jsonObject ) throws JSONException {
            IParentModule parentModule = (IParentModule) createAndFillModule( context, jsonObject );
            JSONArray jsonSubModules = jsonObject.getJSONArray( COLUMN_SUBMODULES );
            for ( int i = 0; i < jsonSubModules.length(); i++ ) {
                JSONObject subModuleJsonObject = jsonSubModules.getJSONObject( i );
                ModuleLoader moduleLoader = getModuleLoader( subModuleJsonObject );
                IModule module = moduleLoader.load( context, subModuleJsonObject );
                parentModule.addSubmodules( module );
            }
            return parentModule;
        }

        @Override
        public JSONObject save( Context context, IModule module ) throws JSONException {
            IParentModule parentModule = (IParentModule) module;
            JSONObject jsonObject = fillJsonObject( new JSONObject(), context, module );
            JSONArray jsonSubModulesArray = new JSONArray();
            for ( IModule m : parentModule.getSubmodules() ) {
                ModuleLoader moduleLoader = moduleToolsMap.getLoader( m.getClass() );
                JSONObject mJsonObject = moduleLoader.save( context, m );
                if ( mJsonObject != null ) {
                    jsonSubModulesArray.put( mJsonObject );
                }
            }
            jsonObject.put( COLUMN_SUBMODULES, jsonSubModulesArray );
            return jsonObject;
        }
    },
    APP_SHORTCUT {
        @Override
        public IModule load( Context context, JSONObject jsonObject ) throws JSONException {
            AbstractShortcutModule module;
            try {
                module = (AbstractShortcutModule) loadModule( jsonObject.getString( COLUMN_CLASS ) );
            } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException e ) {
                throw new JSONException( e.getMessage() );
            }
            module.setTitle( getTitle( context, jsonObject ) );
            Intent intent = getIntent( context, jsonObject );
            module.setIntent( intent );
            try {
                module.setIcon( IconResource.fromDrawable( context.getPackageManager().getApplicationIcon( intent.getPackage() ) ) );
            } catch ( PackageManager.NameNotFoundException e ) {
                module.setIcon( IconResource.fromResourceId( R.drawable.ic_exit_to_app_black_24dp ) );
            }
            return module;
        }

        @Override
        public JSONObject save( Context context, IModule module ) throws JSONException {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put( COLUMN_CLASS, module.getClass().getName() );
            jsonObject.put( COLUMN_LOADER, name() );
            StringResource titleResource = module.getTitle();
            if ( titleResource.getResourceId() == 0 ) {
                jsonObject.put( COLUMN_TITLE, titleResource.getString( context ) );
            } else {
                jsonObject.put( COLUMN_TITLE_RESOURCE_NAME, context.getResources().getResourceEntryName( titleResource.getResourceId() ) );
                jsonObject.put( COLUMN_TITLE_RESOURCE_PACKAGE, context.getResources().getResourcePackageName( titleResource.getResourceId() ) );
                jsonObject.put( COLUMN_TITLE_RESOURCE_TYPE, context.getResources().getResourceTypeName( titleResource.getResourceId() ) );
            }
            jsonObject.put( COLUMN_INTENT, ( (AbstractShortcutModule) module ).getIntent().toUri( 0 ) );
            return jsonObject;
        }
    },
    INTENT {
        @Override
        public IModule load( Context context, JSONObject jsonObject ) throws JSONException {
            AbstractShortcutModule module = (AbstractShortcutModule) createAndFillModule( context, jsonObject );
            module.setIntent( getIntent( context, jsonObject ) );
            return module;
        }

        @Override
        public JSONObject save( Context context, IModule module ) throws JSONException {
            JSONObject jsonObject = fillJsonObject( new JSONObject(), context, module );
            jsonObject.put( COLUMN_INTENT, ( (AbstractShortcutModule) module ).getIntent().toUri( 0 ) );
            return jsonObject;
        }
    },
    SUPPORT_SKIP {
        @Override
        public IModule load( Context context, JSONObject jsonObject ) throws JSONException {
            throw new AssertionError();
        }

        @Override
        public JSONObject save( Context context, IModule module ) throws JSONException {
            return null;
        }
    },
    HTTP {
        @Override
        public IModule load( Context context, JSONObject jsonObject ) throws JSONException {
            return null;
        }

        @Override
        public JSONObject save( Context context, IModule module ) throws JSONException {
            return null;
        }
    };

    public static ModuleLoader getModuleLoader( JSONObject jsonObject ) throws JSONException {
        return valueOf( jsonObject.getString( COLUMN_LOADER ) );
    }

    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_LOADER = "loader";
    public static final String COLUMN_SUBMODULES = "submodules";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ICON_RESOURCE_NAME = "iconResourceName";
    public static final String COLUMN_ICON_RESOURCE_PACKAGE = "iconResourcePackage";
    public static final String COLUMN_ICON_RESOURCE_TYPE = "iconResourceType";
    public static final String COLUMN_TITLE_RESOURCE_NAME = "titleResourceName";
    public static final String COLUMN_TITLE_RESOURCE_PACKAGE = "titleResourcePackage";
    public static final String COLUMN_TITLE_RESOURCE_TYPE = "titleResourceType";
    public static final String COLUMN_INTENT = "intent";
    public static final String EXCEPTION_START = "JSON file error: ";

    protected final ModuleToolsMap moduleToolsMap = ModuleToolsMap.getInstance();

    /**
     * Creates IModule from given JSON object definition.
     *
     * @param context    Context for resources
     * @param jsonObject
     * @return IModule from given JSON
     */
    abstract public IModule load( Context context, JSONObject jsonObject ) throws JSONException;

    /**
     * Creates JSON object from given module
     *
     * @param context Context for resources
     * @param module
     * @return JSONOnject from given IModule object
     */
    abstract public JSONObject save( Context context, IModule module ) throws JSONException;

    protected static IModule loadModule( String className ) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<IModule> moduleClass = (Class<IModule>) ClassLoader.getSystemClassLoader().loadClass( className );
        return moduleClass.newInstance();
    }

    protected static StringResource getTitle( Context context, JSONObject jsonObject ) throws JSONException {
        String moduleTitle;
        try {
            moduleTitle = jsonObject.getString( COLUMN_TITLE );
        } catch ( JSONException e ) {
            String moduleTitleResName;
            moduleTitleResName = jsonObject.getString( COLUMN_TITLE_RESOURCE_NAME );
            String defPackage = jsonObject.getString( COLUMN_TITLE_RESOURCE_PACKAGE );
            String typeName = jsonObject.getString( COLUMN_TITLE_RESOURCE_TYPE );
            int titleResId = context.getResources().getIdentifier( moduleTitleResName, typeName, defPackage );
            if ( titleResId == 0 ) {
                throw new JSONException( EXCEPTION_START + "could not find resource for given data: " + defPackage + ":" + typeName + "\\/" + moduleTitleResName );
            }
            return StringResource.fromResourceId( titleResId );
        }
        return StringResource.fromString( moduleTitle );
    }

    protected static IconResource getIcon( Context context, JSONObject jsonObject ) throws JSONException {
        String moduleIconResName;
        moduleIconResName = jsonObject.getString( COLUMN_ICON_RESOURCE_NAME );
        int iconResId;
        String defPackage = jsonObject.getString( COLUMN_ICON_RESOURCE_PACKAGE );
        String typeName = jsonObject.getString( COLUMN_ICON_RESOURCE_TYPE );
        iconResId = context.getResources().getIdentifier( moduleIconResName, typeName, defPackage );
        if ( iconResId == 0 ) {
            throw new JSONException( EXCEPTION_START + "could not find resource for given data: " + defPackage + ":" + typeName + "\\/" + moduleIconResName );
        }
        return IconResource.fromResourceId( iconResId );
    }

    protected static Intent getIntent( Context context, JSONObject jsonObject ) throws JSONException {
        String intentString = jsonObject.getString( COLUMN_INTENT );
        try {
            return Intent.parseUri( intentString, 0 );
        } catch ( URISyntaxException e ) {
            throw new JSONException( e.getMessage() );
        }
    }

    protected IModule createAndFillModule( Context context, JSONObject jsonObject ) throws JSONException {
        IParentModule module;
        try {
            module = (IParentModule) loadModule( jsonObject.getString( COLUMN_CLASS ) );
        } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException e ) {
            throw new JSONException( e.getMessage() );
        }
        module.setTitle( getTitle( context, jsonObject ) );
        module.setIcon( getIcon( context, jsonObject ) );
        return module;
    }

    /**
     * Cannot be used by Shortcut module! (due to Icon)
     *
     * @param jsonObject
     * @param context
     * @param module
     * @return
     * @throws JSONException
     */
    protected JSONObject fillJsonObject( JSONObject jsonObject, Context context, IModule module ) throws JSONException {
        jsonObject.put( COLUMN_CLASS, module.getClass().getName() );
        jsonObject.put( COLUMN_LOADER, name() );
        StringResource titleResource = module.getTitle();
        if ( titleResource.getResourceId() == 0 ) {
            jsonObject.put( COLUMN_TITLE, titleResource.getString( context ) );
        } else {
            jsonObject.put( COLUMN_TITLE_RESOURCE_NAME, context.getResources().getResourceEntryName( titleResource.getResourceId() ) );
            jsonObject.put( COLUMN_TITLE_RESOURCE_PACKAGE, context.getResources().getResourcePackageName( titleResource.getResourceId() ) );
            jsonObject.put( COLUMN_TITLE_RESOURCE_TYPE, context.getResources().getResourceTypeName( titleResource.getResourceId() ) );
        }
        IconResource iconResource = module.getIcon();
        if ( iconResource.getResourceId() == 0 ) {
            throw new UnsupportedOperationException( "Icon must have resource ID" );
        } else {
            jsonObject.put( COLUMN_ICON_RESOURCE_NAME, context.getResources().getResourceEntryName( iconResource.getResourceId() ) );
            jsonObject.put( COLUMN_ICON_RESOURCE_PACKAGE, context.getResources().getResourcePackageName( iconResource.getResourceId() ) );
            jsonObject.put( COLUMN_ICON_RESOURCE_TYPE, context.getResources().getResourceTypeName( iconResource.getResourceId() ) );
        }
        return jsonObject;
    }
}
