package com.eclubprague.cardashboard.core.modules.models.resources;

/**
 * Created by Michael on 9. 7. 2015.
 *
 * ColorResource class containing color resource ID.
 */
public class ColorResource extends SimpleAbstractResource {
    private ColorResource(int resourceId) {
        super(resourceId);
    }

    public static ColorResource fromResourceId(int resourceId) {
        return new ColorResource(resourceId);
    }
}
