package com.eclubprague.cardashboard.core.modules.models;

/**
 * Created by Michael on 9. 7. 2015.
 * <p>
 * IconResource class containing icon resource ID.
 */
public class IconResource extends SimpleAbstractResource {

    public IconResource(int resourceId) {
        super(resourceId);
    }

    public static IconResource fromResourceId(int resourceId) {
        return new IconResource(resourceId);
    }

}
