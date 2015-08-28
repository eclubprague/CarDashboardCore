package com.eclubprague.cardashboard.core.modules.predefined;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.AbstractParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 20. 7. 2015.
 * <p/>
 * Simple implementation of submenu module.
 */
public class SimpleParentModule extends AbstractParentModule {

    public SimpleParentModule() {
        super(StringResource.fromResourceId(R.string.module_others_folder_new), IconResource.fromResourceId(R.drawable.ic_folder_black_24dp));
    }

    public SimpleParentModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        super(titleResource, iconResource);
    }

    public SimpleParentModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(titleResource, iconResource, bgColorResource, fgColorResource);
    }
}
