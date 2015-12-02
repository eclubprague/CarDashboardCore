package com.eclubprague.cardashboard.core.data.modules;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;

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
        public IModule load( IModuleContext context, JSONObject jsonObject ) throws JSONException {
            return createAndFillModule( context, jsonObject );
        }

        @Override
        public JSONObject save( IModuleContext context, IModule module ) throws JSONException {
            return fillJsonObject( new JSONObject(), context, module );
        }
    },
    DISPLAY {
        @Override
        public IModule load( IModuleContext context, JSONObject jsonObject ) throws JSONException {
            AbstractDisplayModule displayModule = (AbstractDisplayModule) createAndFillModule( context, jsonObject );
            displayModule.setUnitResource( getStringResource( context, jsonObject, COLUMN_UNIT, COLUMN_UNIT_RESOURCE ) );
            return displayModule;
        }

        @Override
        public JSONObject save( IModuleContext context, IModule module ) throws JSONException {
            AbstractDisplayModule displayModule = (AbstractDisplayModule) module;
            JSONObject jsonObject = fillJsonObject( new JSONObject(), context, module );
            putStringResource( context, jsonObject, displayModule.getUnit(), COLUMN_UNIT, COLUMN_UNIT_RESOURCE );
            return jsonObject;
        }
    },
    PARENT {
        @Override
        public IModule load( IModuleContext context, JSONObject jsonObject ) throws JSONException {
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
        public JSONObject save( IModuleContext context, IModule module ) throws JSONException {
            IParentModule parentModule = (IParentModule) module;
            JSONObject jsonObject = fillJsonObject( new JSONObject(), context, module );
            JSONArray jsonSubModulesArray = new JSONArray();
            for ( IModule m : parentModule.getSubmodules() ) {
                Log.d("ModuleLoader", "moduleCreationToolsMap = " + moduleCreationToolsMap);
                ModuleLoader moduleLoader = moduleCreationToolsMap.getLoader( m.getClass() );
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
        public IModule load( IModuleContext context, JSONObject jsonObject ) throws JSONException {
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
                module.setIcon( IconResource.fromDrawable( context.getContext().getPackageManager().getApplicationIcon( intent.getPackage() ) ) );
            } catch ( PackageManager.NameNotFoundException e ) {
                module.setIcon( IconResource.fromResourceId( R.drawable.ic_exit_to_app_black_24dp ) );
            }
            return module;
        }

        @Override
        public JSONObject save( IModuleContext context, IModule module ) throws JSONException {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put( COLUMN_CLASS, module.getClass().getName() );
            jsonObject.put( COLUMN_LOADER, name() );
            putStringResource( context, jsonObject, module.getTitle(), COLUMN_TITLE, COLUMN_TITLE_RESOURCE );
            jsonObject.put( COLUMN_INTENT, ( (AbstractShortcutModule) module ).getIntent().toUri( 0 ) );
            return jsonObject;
        }
    },
    INTENT {
        @Override
        public IModule load( IModuleContext context, JSONObject jsonObject ) throws JSONException {
            AbstractShortcutModule module = (AbstractShortcutModule) createAndFillModule( context, jsonObject );
            module.setIntent( getIntent( context, jsonObject ) );
            return module;
        }

        @Override
        public JSONObject save( IModuleContext context, IModule module ) throws JSONException {
            JSONObject jsonObject = fillJsonObject( new JSONObject(), context, module );
            jsonObject.put( COLUMN_INTENT, ( (AbstractShortcutModule) module ).getIntent().toUri( 0 ) );
            return jsonObject;
        }
    },
    SUPPORT_SKIP {
        @Override
        public IModule load( IModuleContext context, JSONObject jsonObject ) throws JSONException {
            throw new AssertionError();
        }

        @Override
        public JSONObject save( IModuleContext context, IModule module ) throws JSONException {
            return null;
        }
    },
    HTTP {
        @Override
        public IModule load( IModuleContext context, JSONObject jsonObject ) throws JSONException {
            return null;
        }

        @Override
        public JSONObject save( IModuleContext context, IModule module ) throws JSONException {
            return null;
        }
    };

    public static ModuleLoader getModuleLoader( JSONObject jsonObject ) throws JSONException {
        return valueOf( jsonObject.getString( COLUMN_LOADER ) );
    }

    protected static final String COLUMN_CLASS = "class";
    protected static final String COLUMN_LOADER = "loader";
    protected static final String COLUMN_SUBMODULES = "submodules";
    protected static final String COLUMN_TITLE = "title";
    protected static final ResourceColumns COLUMN_TITLE_RESOURCE = new ResourceColumns( COLUMN_TITLE );
    protected static final String COLUMN_ICON = "icon";
    protected static final ResourceColumns COLUMN_ICON_RESOURCE = new ResourceColumns( COLUMN_ICON );
    protected static final String COLUMN_UNIT = "unit";
    protected static final ResourceColumns COLUMN_UNIT_RESOURCE = new ResourceColumns( COLUMN_UNIT );
    protected static final String COLUMN_INTENT = "intent";
    protected static final String EXCEPTION_START = "JSON file error: ";

    protected final ModuleCreationToolsMap moduleCreationToolsMap;

    ModuleLoader() {
        Log.d("ModuleLoader", "constructor, initializing toolsMap = " + ModuleCreationToolsMap.getInstance());
        moduleCreationToolsMap = ModuleCreationToolsMap.getInstance();
    }

    /**
     * Creates IModule from given JSON object definition.
     *
     * @param context    Context for resources
     * @param jsonObject
     * @return IModule from given JSON
     */
    abstract public IModule load( IModuleContext context, JSONObject jsonObject ) throws JSONException;

    /**
     * Creates JSON object from given module
     *
     * @param context Context for resources
     * @param module
     * @return JSONOnject from given IModule object
     */
    abstract public JSONObject save( IModuleContext context, IModule module ) throws JSONException;

    protected static IModule loadModule( String className ) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<IModule> moduleClass = (Class<IModule>) ClassLoader.getSystemClassLoader().loadClass( className );
        return moduleClass.newInstance();
    }

    protected static StringResource getTitle( IModuleContext context, JSONObject jsonObject ) throws JSONException {
        return getStringResource( context, jsonObject, COLUMN_TITLE, COLUMN_TITLE_RESOURCE );
    }

    protected static StringResource getStringResource( IModuleContext context, JSONObject jsonObject, String column, ResourceColumns resourceColumns ) throws JSONException {
        String str;
        try {
            str = jsonObject.getString( column );
        } catch ( JSONException e ) {
            String resName = jsonObject.getString( resourceColumns.resourceName );
            String defPackage = jsonObject.getString( resourceColumns.resourcePackage );
            String typeName = jsonObject.getString( resourceColumns.resourceType );
            int strResId = context.getResources().getIdentifier( resName, typeName, defPackage );
            if ( strResId == 0 ) {
                throw new JSONException( EXCEPTION_START + "could not find resource for given data: " + defPackage + ":" + typeName + "\\/" + resName );
            }
            return StringResource.fromResourceId( strResId );
        }
        return StringResource.fromString( str );
    }

    protected static IconResource getIcon( IModuleContext context, JSONObject jsonObject ) throws JSONException {
        return getIconResource( context, jsonObject, COLUMN_ICON_RESOURCE );
    }

    protected static IconResource getIconResource( IModuleContext context, JSONObject jsonObject, ResourceColumns resourceColumns ) throws JSONException {
        String resName = jsonObject.getString( resourceColumns.resourceName );
        String defPackage = jsonObject.getString( resourceColumns.resourcePackage );
        String typeName = jsonObject.getString( resourceColumns.resourceType );
        int iconResId = context.getResources().getIdentifier( resName, typeName, defPackage );
        if ( iconResId == 0 ) {
            throw new JSONException( EXCEPTION_START + "could not find resource for given data: " + defPackage + ":" + typeName + "\\/" + resName );
        }
        return IconResource.fromResourceId( iconResId );
    }

    protected static Intent getIntent( IModuleContext context, JSONObject jsonObject ) throws JSONException {
        String intentString = jsonObject.getString( COLUMN_INTENT );
        try {
            return Intent.parseUri( intentString, 0 );
        } catch ( URISyntaxException e ) {
            throw new JSONException( e.getMessage() );
        }
    }

    protected static JSONObject putStringResource( IModuleContext context, JSONObject jsonObject, StringResource stringResource, String column, ResourceColumns resourceColumns ) throws JSONException {
        if ( stringResource.getResourceId() == 0 ) {
            jsonObject.put( column, stringResource.getString( context.getContext() ) );
        } else {
            jsonObject.put( resourceColumns.resourceName, context.getResources().getResourceEntryName( stringResource.getResourceId() ) );
            jsonObject.put( resourceColumns.resourcePackage, context.getResources().getResourcePackageName( stringResource.getResourceId() ) );
            jsonObject.put( resourceColumns.resourceType, context.getResources().getResourceTypeName( stringResource.getResourceId() ) );
        }
        return jsonObject;
    }

    protected static JSONObject putIconResource( IModuleContext context, JSONObject jsonObject, IconResource iconResource, ResourceColumns resourceColumns ) throws JSONException {
        if ( iconResource.getResourceId() == 0 ) {
            throw new UnsupportedOperationException( "Icon must have resource ID" );
        } else {
            jsonObject.put( resourceColumns.resourceName, context.getResources().getResourceEntryName( iconResource.getResourceId() ) );
            jsonObject.put( resourceColumns.resourcePackage, context.getResources().getResourcePackageName( iconResource.getResourceId() ) );
            jsonObject.put( resourceColumns.resourceType, context.getResources().getResourceTypeName( iconResource.getResourceId() ) );
        }
        return jsonObject;
    }

    protected IModule createAndFillModule( IModuleContext context, JSONObject jsonObject ) throws JSONException {
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
    protected JSONObject fillJsonObject( JSONObject jsonObject, IModuleContext context, IModule module ) throws JSONException {
        jsonObject.put( COLUMN_CLASS, module.getClass().getName() );
        jsonObject.put( COLUMN_LOADER, name() );
        putStringResource( context, jsonObject, module.getTitle(), COLUMN_TITLE, COLUMN_TITLE_RESOURCE );
        putIconResource( context, jsonObject, module.getIcon(), COLUMN_ICON_RESOURCE );
        return jsonObject;
    }

    private static class ResourceColumns {
        public final String resourceName;
        public final String resourcePackage;
        public final String resourceType;

        public ResourceColumns( String column ) {
            this.resourceName = column + "ResourceName";
            this.resourcePackage = column + "ResourcePackage";
            this.resourceType = column + "ResourceType";
        }
    }
}
