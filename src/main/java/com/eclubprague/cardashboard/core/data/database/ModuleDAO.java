package com.eclubprague.cardashboard.core.data.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.eclubprague.cardashboard.core.data.Constants;
import com.eclubprague.cardashboard.core.data.ModuleSupplier;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.predefined.BackModule;
import com.eclubprague.cardashboard.core.modules.predefined.SimpleParentModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Michael on 18.08.2015.
 */
public class ModuleDAO {
    public static final String TAG = ModuleDAO.class.getSimpleName();
    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_SUBMODULES = "submodules";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ICON_RESOURCE_NAME = "iconResourceName";
    public static final String COLUMN_ICON_RESOURCE_PACKAGE = "iconResourcePackage";
    public static final String COLUMN_ICON_RESOURCE_TYPE = "iconResourceType";
    public static final String COLUMN_TITLE_RESOURCE_NAME = "titleResourceName";
    public static final String COLUMN_TITLE_RESOURCE_PACKAGE = "titleResourcePackage";
    public static final String COLUMN_TITLE_RESOURCE_TYPE = "titleResourceType";

    public static final String EXCEPTION_START = "JSON file error: ";

    //    private static Map<Class, IModule> map;
    private final IModuleContext moduleContext;

    public ModuleDAO(IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
//        if (map == null) {
//            map = new HashMap<>();
//            ModuleSupplier.getDefaultInstance().getHomeScreenModule(moduleContext); // initializes modules
//            for (IModule module : ModuleSupplier.getDefaultInstance().getAll()) {
//                put(module.getClass(), module);
//            }
//            ModuleSupplier.getDefaultInstance().clear();
//        }
    }

    public IParentModule loadParentModule() throws IOException {
        File file = new File(moduleContext.getContext().getFilesDir(), Constants.FILE_PERSONAL_UI);
        if (!file.exists()) {
            return ModuleSupplier.getBaseInstance().getHomeScreenModule(moduleContext);
        }
        Log.d(TAG, "Input file exists: " + file.exists());
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        Log.d(TAG, "Input file content: " + sb);
        br.close();
        return readParentModule(sb.toString());
    }

    public IParentModule readParentModule(String data) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            IParentModule parentModule = loadParentModule(moduleContext.getContext(), jsonObject);
            return parentModule;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException("JSON file error: " + e.getMessage(), e.getCause());
        }
    }

    public void saveParentModule(IParentModule parentModule) throws IOException {
        new SaveAsyncTask().execute(new SaveMessenger(moduleContext, parentModule));
    }

    public String writeParentModule(IParentModule parentModule) throws IOException {
        try {
            JSONObject jsonObject = getParentJsonObject(moduleContext.getContext(), parentModule);
//            Log.d(TAG, jsonObject.toString(4));
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage(), e.getCause());
        }
    }


    private IParentModule loadParentModule(Context context, JSONObject jsonObject) throws IOException, JSONException {
        IParentModule parentModule;
        StringResource titleResource = getTitle(context, jsonObject);
        if (titleResource == null) {
            titleResource = getTitleFromResource(context, jsonObject);
            if (titleResource == null) {
                throw new IOException(EXCEPTION_START + "title not found for parent module");
            }
        }
        IconResource iconResource = getIcon(context, jsonObject);
        if (iconResource == null) {
            throw new IOException(EXCEPTION_START + "icon not found for parent module");
        }
        parentModule = new SimpleParentModule(titleResource, iconResource);
        JSONArray submodules = null;
        try {
            submodules = jsonObject.getJSONArray(COLUMN_SUBMODULES);
        } catch (JSONException e) {
            throw new IOException(EXCEPTION_START + "array of submodules not found for parent module");
        }
        for (int i = 0; i < submodules.length(); i++) {
            JSONObject moduleObject = submodules.getJSONObject(i);
            IModule module = loadModule(context, moduleObject);
            parentModule.addSubmodules(module);
        }
        return parentModule;
    }

    private IModule loadModule(Context context, JSONObject moduleObject) throws IOException, JSONException {
        Class moduleClass = getClass(context, moduleObject);
        IModule module;
        try {
            module = (IModule) moduleClass.newInstance();
        } catch (InstantiationException e) {
            throw new IOException(EXCEPTION_START + "unable to create new instance: " + moduleClass.getName());
        } catch (IllegalAccessException e) {
            throw new IOException(EXCEPTION_START + "unable to access new instance creation: " + moduleClass.getName());
        }
        module = fillModule(module, context, moduleObject);
        if (IParentModule.class.isAssignableFrom(module.getClass())) {
            IParentModule parentModule = (IParentModule) module;
            JSONArray submodules;
            try {
                submodules = moduleObject.getJSONArray(COLUMN_SUBMODULES);
            } catch (JSONException e) {
                throw new IOException(EXCEPTION_START + "array of submodules not found for parent module: " + moduleClass.getName());
            }
            for (int i = 0; i < submodules.length(); i++) {
                JSONObject submoduleObject = submodules.getJSONObject(i);
                IModule submodule = loadModule(context, submoduleObject);
                parentModule.addSubmodules(submodule);
            }
        }

//        if (SimpleParentModule.class.equals(moduleClass)) { // parent module
//            module = loadParentModule(context, moduleObject);
//        } else if (EmptyModule.class.equals(moduleClass)) {
//            module = new EmptyModule();
//        } else if (get(moduleClass) != null) {
//            module = fillModule(get(moduleClass), context, moduleObject);
//        } else {
//            throw new IOException(EXCEPTION_START + "unknown class: " + moduleClass.getName());
//        }
        return module;
    }

    private IModule fillModule(IModule module, Context context, JSONObject moduleObject) throws IOException {
        StringResource titleResource;
        titleResource = getTitle(context, moduleObject);
        if (titleResource == null) {
            titleResource = getTitleFromResource(context, moduleObject);
        }
        if (titleResource != null) {
            module.setTitle(titleResource);
        }
        IconResource iconResource = getIcon(context, moduleObject);
        if (iconResource != null) {
            module.setIcon(iconResource);
        }
        return module;
    }

    private StringResource getTitleFromResource(Context context, JSONObject jsonObject) throws IOException {
        String moduleTitleResName;
        try {
            moduleTitleResName = jsonObject.getString(COLUMN_TITLE_RESOURCE_NAME);
        } catch (JSONException e) {
            return null;
        }
        try {
            String defPackage = jsonObject.getString(COLUMN_TITLE_RESOURCE_PACKAGE);
            String typeName = jsonObject.getString(COLUMN_TITLE_RESOURCE_TYPE);
            int titleResId = context.getResources().getIdentifier(moduleTitleResName, typeName, defPackage);
            if (titleResId == 0) {
                throw new IOException(EXCEPTION_START + "could not find resource for given data: " + defPackage + ":" + typeName + "\\/" + moduleTitleResName);
            }
            return StringResource.fromResourceId(titleResId);
        } catch (JSONException e) {
            throw new IOException(EXCEPTION_START + "illegal state: module has " + COLUMN_TITLE_RESOURCE_NAME + ", but not " + COLUMN_TITLE_RESOURCE_PACKAGE + " or " + COLUMN_TITLE_RESOURCE_TYPE, e);
        }
    }

    private StringResource getTitle(Context context, JSONObject jsonObject) {
        String moduleTitle;
        try {
            moduleTitle = jsonObject.getString(COLUMN_TITLE);
        } catch (JSONException e) {
            return null;
        }
        return StringResource.fromString(moduleTitle);
    }

    private IconResource getIcon(Context context, JSONObject jsonObject) throws IOException {
        String moduleIconResName;
        try {
            moduleIconResName = jsonObject.getString(COLUMN_ICON_RESOURCE_NAME);
        } catch (JSONException e) {
            return null;
        }
        try {
            int iconResId;
            String defPackage = jsonObject.getString(COLUMN_ICON_RESOURCE_PACKAGE);
            String typeName = jsonObject.getString(COLUMN_ICON_RESOURCE_TYPE);
            iconResId = context.getResources().getIdentifier(moduleIconResName, typeName, defPackage);
            if (iconResId == 0) {
                throw new IOException(EXCEPTION_START + "could not find resource for given data: " + defPackage + ":" + typeName + "\\/" + moduleIconResName);
            }
            return IconResource.fromResourceId(iconResId);
        } catch (JSONException e) {
            throw new IOException(EXCEPTION_START + "illegal state: module has " + COLUMN_ICON_RESOURCE_NAME + ", but not " + COLUMN_ICON_RESOURCE_PACKAGE + " or " + COLUMN_ICON_RESOURCE_TYPE, e);
        }
    }

    private Class getClass(Context context, JSONObject jsonObject) throws IOException {
        String moduleClassName = null;
        try {
            moduleClassName = jsonObject.getString(COLUMN_CLASS);
        } catch (JSONException e) {
            throw new IOException(EXCEPTION_START + "no class defined", e);
        }
        Class moduleClass;
        try {
            moduleClass = Class.forName(moduleClassName);
            return moduleClass;
        } catch (ClassNotFoundException e) {
            throw new IOException(EXCEPTION_START + "unknown class: " + moduleClassName, e);
        }
    }

    private JSONObject getParentJsonObject(Context context, IParentModule parentModule) throws JSONException, IOException {
        IParentModule copyParentModyle = null;
        try {
            copyParentModyle = (IParentModule) parentModule.copyDeep();
        } catch (ReflectiveOperationException e) {
            throw new IOException(e);
        }
        copyParentModyle.removeTailEmptyModules();
        JSONObject jsonObject = fillJsonObject(context, new JSONObject(), copyParentModyle);
        JSONArray jsonSubmodulesArray = new JSONArray();
        for (IModule m : copyParentModyle.getSubmodules()) {
            if (m instanceof BackModule) {
                // skip it
            } else if (m instanceof IParentModule) {
                jsonSubmodulesArray.put(getParentJsonObject(context, (IParentModule) m));
            } else {
                jsonSubmodulesArray.put(fillJsonObject(context, new JSONObject(), m));
            }
        }
        jsonObject.put(COLUMN_SUBMODULES, jsonSubmodulesArray);
        return jsonObject;
    }

    private JSONObject fillJsonObject(Context context, JSONObject jsonObject, IModule module) throws JSONException {
        StringResource titleResource = module.getTitle();
        if (titleResource.getResourceId() == 0) {
            jsonObject.put(COLUMN_TITLE, titleResource.getString(context));
        } else {
            jsonObject.put(COLUMN_TITLE_RESOURCE_NAME, context.getResources().getResourceEntryName(titleResource.getResourceId()));
            jsonObject.put(COLUMN_TITLE_RESOURCE_PACKAGE, context.getResources().getResourcePackageName(titleResource.getResourceId()));
            jsonObject.put(COLUMN_TITLE_RESOURCE_TYPE, context.getResources().getResourceTypeName(titleResource.getResourceId()));
        }
        IconResource iconResource = module.getIcon();
        if (iconResource.getResourceId() == 0) {
            throw new UnsupportedOperationException("Icon must have resource ID");
        } else {
            jsonObject.put(COLUMN_ICON_RESOURCE_NAME, context.getResources().getResourceEntryName(iconResource.getResourceId()));
            jsonObject.put(COLUMN_ICON_RESOURCE_PACKAGE, context.getResources().getResourcePackageName(iconResource.getResourceId()));
            jsonObject.put(COLUMN_ICON_RESOURCE_TYPE, context.getResources().getResourceTypeName(iconResource.getResourceId()));
        }
        jsonObject.put(COLUMN_CLASS, module.getClass().getName());
        return jsonObject;
    }

//    private Map<Class, IModule> put(Class clz, IModule module) {
//        map.put(clz, module);
//        return map;
//    }

//    private IModule get(Class clz) {
//        return map.get(clz);
//    }

    private static class SaveAsyncTask extends AsyncTask<SaveMessenger, Void, Void> {

        @Override
        protected Void doInBackground(SaveMessenger... params) {
            Log.d(TAG, "Executing saving task");
            SaveMessenger msg = params[0];
            File file = new File(msg.context.getContext().getFilesDir(), Constants.FILE_PERSONAL_UI);
            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(file));
                ModuleDAO moduleDAO = new ModuleDAO(msg.context);
                String data = moduleDAO.writeParentModule(msg.parentModule);
                Log.d(TAG, "write parent module = " + data);
                bw.write(data);
                bw.close();

                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    Log.d(TAG, "file: " + line);
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return null;
        }


    }

    private static class SaveMessenger {
        public final IModuleContext context;
        public final IParentModule parentModule;

        public SaveMessenger(IModuleContext context, IParentModule parentModule) {
            this.context = context;
            this.parentModule = parentModule;
        }
    }

}
