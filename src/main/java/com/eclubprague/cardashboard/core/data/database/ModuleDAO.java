package com.eclubprague.cardashboard.core.data.database;

import com.eclubprague.cardashboard.core.data.ModuleSupplier;
import com.eclubprague.cardashboard.core.modules.base.IModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 18.08.2015.
 */
public class ModuleDAO {
    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_SUBMODULES = "submodules";

    private Map<Class, IModule> map = new HashMap<>();

    public ModuleDAO() {
        for (IModule module : ModuleSupplier.getBaseInstance().getAll()) {
            put(module.getClass(), module);
        }
    }

    public List<IModule> readModules(String data) throws IOException {
        try {
            JSONObject homeScreenModule;
            homeScreenModule = new JSONObject(data);
            String hsClass = homeScreenModule.getString(COLUMN_CLASS);
            JSONArray submodules = homeScreenModule.getJSONArray(COLUMN_SUBMODULES);
            for (int i = 0; i < submodules.length(); i++) {
                JSONObject moduleObject = submodules.getJSONObject(i);
                String moduleClassName = moduleObject.getString(COLUMN_CLASS);
                Class moduleClass = Class.forName(moduleClassName);
//                if(moduleClass.)
            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage(), e.getCause());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage(), e.getCause());
        }
        return null;
    }

    private Map<Class, IModule> put(Class clz, IModule module) {
        map.put(clz, module);
        return map;
    }

    private IModule get(Class clz) {
        IModule module = map.get(clz);
        if (module == null) {

        }
        return null;
    }

}
