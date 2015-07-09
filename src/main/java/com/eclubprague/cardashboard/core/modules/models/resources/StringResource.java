package com.eclubprague.cardashboard.core.modules.models.resources;

/**
 * Created by Michael on 9. 7. 2015.
 *
 * StringResource class containing string resource ID.
 */
public class StringResource extends SimpleAbstractResource {

    private StringResource(int resourceId) {
        super(resourceId);
    }

    public static StringResource fromResourceId(int resourceId) {
        return new StringResource(resourceId);
    }

}
