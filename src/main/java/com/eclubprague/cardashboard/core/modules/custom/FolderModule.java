package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.AbstractParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 20.08.2015.
 */
public class FolderModule extends AbstractParentModule {

    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_folder_new);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_folder_black_24dp);


    public FolderModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE);
    }

    public FolderModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource);
    }
}
