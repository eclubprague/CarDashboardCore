package com.eclubprague.cardashboard.core.data.database;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.data.Constants;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.data.modules.ModuleType;
import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ErrorReporter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

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
    public static final String COLUMN_INTENT = "intent";

    public static final String EXCEPTION_START = "JSON file error: ";

    private ModuleDAO() {
    }

    public static void saveParentModuleAsync(Context context, IParentModule parentModule) throws IOException {
        new SaveAsyncTask().execute(new SaveMessenger(context, parentModule));
    }

    public static IParentModule loadParentModule(Context context) throws IOException {
        LoadDAO loadDAO = new LoadDAO(context);
        return loadDAO.loadParentModule();
    }

    private static class SaveDAO {
        private final Context context;

        public SaveDAO(Context context) {
            this.context = context;
        }

        public void saveParentModule(IParentModule parentModule) throws IOException {
            new SaveAsyncTask().execute(new SaveMessenger(context, parentModule));
        }

        public String writeParentModule(IParentModule parentModule) throws IOException {
            try {
                JSONObject jsonObject = getParentJsonObject(context, parentModule);
//            Log.d(TAG, jsonObject.toString(4));
                return jsonObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                throw new IOException(e.getMessage(), e.getCause());
            }
        }

        private JSONObject getParentJsonObject(Context context, IParentModule parentModule) throws JSONException {
            JSONObject jsonObject = fillJsonObject(context, new JSONObject(), parentModule);
            JSONArray jsonSubmodulesArray = new JSONArray();
            for (IModule m : parentModule.getSubmodules()) {
                switch (m.getModuleEnum().getModuleType()) {
                    case DEFINED:
                    case SHORTCUT:
                        jsonSubmodulesArray.put(fillJsonObject(context, new JSONObject(), m));
                        break;
                    case PARENT:
                        jsonSubmodulesArray.put(getParentJsonObject(context, (IParentModule) m));
                        break;
                    case SUPPORT_SKIP: // skip
                        break;
                    default:
                        throw new AssertionError("Unknown ModuleType." + m.getModuleEnum().getModuleType().name());
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
                if (module.getModuleEnum().getModuleType().equals(ModuleType.SHORTCUT)) {
                } else {
                    throw new UnsupportedOperationException("Icon must have resource ID");
                }
            } else {
                jsonObject.put(COLUMN_ICON_RESOURCE_NAME, context.getResources().getResourceEntryName(iconResource.getResourceId()));
                jsonObject.put(COLUMN_ICON_RESOURCE_PACKAGE, context.getResources().getResourcePackageName(iconResource.getResourceId()));
                jsonObject.put(COLUMN_ICON_RESOURCE_TYPE, context.getResources().getResourceTypeName(iconResource.getResourceId()));
            }
            if (module.getModuleEnum().getModuleType().equals(ModuleType.SHORTCUT)) {
                jsonObject.put(COLUMN_INTENT, ((AbstractShortcutModule) module).getIntent().toUri(0));
            }
            jsonObject.put(COLUMN_CLASS, module.getModuleEnum().name());
            return jsonObject;
        }
    }

    private static class LoadDAO {
        private final Context context;

        public LoadDAO(Context context) {
            this.context = context;
        }

        public IParentModule loadParentModule() throws IOException {
            File file = new File(context.getFilesDir(), Constants.FILE_PERSONAL_UI);
            if (!file.exists()) {
                throw new IOException(EXCEPTION_START + "save file does not exist");
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return readParentModule(sb.toString());
        }

        public IParentModule readParentModule(String data) throws IOException {
            try {
                JSONObject jsonObject = new JSONObject(data);
                return (IParentModule) loadModule(context, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
                throw new IOException(EXCEPTION_START + e.getMessage(), e.getCause());
            }
        }

        private IModule loadModule(Context context, JSONObject moduleObject) throws IOException, JSONException {
            ModuleEnum moduleEnum = getModuleEnum(context, moduleObject);
            switch (moduleEnum.getModuleType()) {
                case DEFINED:
                    return moduleEnum.newInstance(getTitle(context, moduleObject), getIcon(context, moduleObject));
                case PARENT:
                    IParentModule parentModule = (IParentModule) moduleEnum.newInstance(getTitle(context, moduleObject), getIcon(context, moduleObject));
                    JSONArray submodules = getJsonArray(context, moduleObject);
                    for (int i = 0; i < submodules.length(); i++) {
                        IModule module = loadModule(context, submodules.getJSONObject(i));
                        parentModule.addSubmodules(module);
                    }
                    return parentModule;
                case SHORTCUT:
                    StringResource titleResource = getTitle(context, moduleObject);
                    IconResource iconResource = getIcon(context, moduleObject);
                    Intent intent = getIntent(context, moduleObject);
                    if (iconResource == null) {
                        try {
                            iconResource = IconResource.fromDrawable(context.getPackageManager().getApplicationIcon(intent.getPackage()));
                        } catch (PackageManager.NameNotFoundException e) {
                            iconResource = IconResource.fromResourceId(R.drawable.ic_exit_to_app_black_24dp);
                        }
                    }
                    return moduleEnum.newInstance(titleResource, iconResource, intent);
                default:
                    throw new IOException(EXCEPTION_START + "unknown ModuleEnum: " + moduleEnum.name());
            }
        }

        private StringResource getTitle(Context context, JSONObject jsonObject) throws IOException {
            String moduleTitle;
            try {
                moduleTitle = jsonObject.getString(COLUMN_TITLE);
            } catch (JSONException e) {
                String moduleTitleResName;
                try {
                    moduleTitleResName = jsonObject.getString(COLUMN_TITLE_RESOURCE_NAME);
                } catch (JSONException ex) {
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
                } catch (JSONException ex) {
                    throw new IOException(EXCEPTION_START + "illegal state: module has " + COLUMN_TITLE_RESOURCE_NAME + ", but not " + COLUMN_TITLE_RESOURCE_PACKAGE + " or " + COLUMN_TITLE_RESOURCE_TYPE, ex);
                }
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

        private ModuleEnum getModuleEnum(Context context, JSONObject jsonObject) throws IOException {
            String moduleEnumName = null;
            try {
                moduleEnumName = jsonObject.getString(COLUMN_CLASS);
            } catch (JSONException e) {
                throw new IOException(EXCEPTION_START + "no ModuleEnum defined", e);
            }
            try {
                return ModuleEnum.valueOf(moduleEnumName);
            } catch (IllegalArgumentException e) {
                throw new IOException(EXCEPTION_START + "unknown ModuleEnum: " + moduleEnumName, e);
            }
        }

        private JSONArray getJsonArray(Context context, JSONObject jsonObject) throws IOException {
            try {
                return jsonObject.getJSONArray(COLUMN_SUBMODULES);
            } catch (JSONException e) {
                throw new IOException(EXCEPTION_START + "array of submodules not found for parent module");
            }
        }

        private Intent getIntent(Context context, JSONObject jsonObject) throws IOException {
            String intentString;
            try {
                intentString = jsonObject.getString(COLUMN_INTENT);
            } catch (JSONException e) {
                throw new IOException(EXCEPTION_START + "no intent defined", e);
            }
            try {
                return Intent.parseUri(intentString, 0);
            } catch (URISyntaxException e) {
                throw new IOException(EXCEPTION_START + "wrong URI format", e);
            }
        }
    }

    public static class SaveAsyncTask extends AsyncTask<SaveMessenger, Void, Void> {

        @Override
        protected Void doInBackground(SaveMessenger... params) {
            Log.d(TAG, "Executing saving task");
            SaveMessenger msg = params[0];
            SaveRunnable sr = new SaveRunnable(msg.context, msg.parentModule);
            sr.run();
            return null;
        }
    }

    public static class SaveRunnable implements Runnable {
        private final Context context;
        private final IParentModule parentModule;

        public SaveRunnable(Context context, IParentModule parentModule) {
            this.context = context;
            this.parentModule = parentModule;
        }

        @Override
        public void run() {
            File file = new File(context.getFilesDir(), Constants.FILE_PERSONAL_UI);
            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(file));
                SaveDAO saveDAO = new SaveDAO(context);
                String data = saveDAO.writeParentModule(parentModule);
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
                ErrorReporter.reportApplicationError(null, null, null, null);
            }
        }
    }

    public static class SaveMessenger {
        public final Context context;
        public final IParentModule parentModule;

        public SaveMessenger(Context context, IParentModule parentModule) {
            this.context = context;
            this.parentModule = parentModule;
        }
    }
}
