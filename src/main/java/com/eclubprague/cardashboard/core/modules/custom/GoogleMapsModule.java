package com.eclubprague.cardashboard.core.modules.custom;

import android.content.Intent;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 27. 7. 2015.
 * <p/>
 * Default google maps shortcut.
 */
public class GoogleMapsModule extends AbstractShortcutModule {
    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_maps_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_map_black_24dp);
    private static final Intent INTENT = new Intent(Intent.ACTION_VIEW);
    private static final StringResource ERROR_MESSAGE = StringResource.fromResourceId(R.string.module_maps_error);

    static {
        INTENT.setPackage("com.google.android.apps.maps");
    }

    public GoogleMapsModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, INTENT, ERROR_MESSAGE);
    }

    public GoogleMapsModule(IModuleContext moduleContext, IParentModule parent) {
        super(moduleContext, parent, TITLE_RESOURCE, ICON_RESOURCE, INTENT, ERROR_MESSAGE);
    }

    public GoogleMapsModule(IModuleContext moduleContext, IParentModule parent, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, parent, TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, INTENT, ERROR_MESSAGE);
    }
}
