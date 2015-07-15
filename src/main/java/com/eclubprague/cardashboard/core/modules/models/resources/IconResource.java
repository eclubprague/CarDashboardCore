package com.eclubprague.cardashboard.core.modules.models.resources;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by Michael on 9. 7. 2015.
 * <p>
 * IconResource class containing icon resource ID.
 */
public class IconResource extends SimpleAbstractResource {

    private IconResource(int resourceId) {
        super(resourceId);
    }

    public static IconResource fromResourceId(int resourceId) {
        return new IconResource(resourceId);
    }

    public Drawable getIcon(Context context){
        return context.getResources().getDrawable(getResourceId(), context.getTheme());
    }

}
