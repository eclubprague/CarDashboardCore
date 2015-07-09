package com.eclubprague.cardashboard.core.modules.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 9. 7. 2015.
 *
 * Icon class containing icon resource ID.
 */
public class Icon {
    private final int resourceId;

    private static final Map<Integer, Icon> iconMap = new HashMap<>();

    private Icon(int resourceId){
        this.resourceId = resourceId;
    }

    public static Icon fromResourceId(int resourceId){
        Icon icon = iconMap.get(resourceId);
        if(icon == null){
            icon = new Icon(resourceId);
            iconMap.put(resourceId, icon);
        }
        return icon;
    }

    public int getResourceId(){
        return resourceId;
    }

}
