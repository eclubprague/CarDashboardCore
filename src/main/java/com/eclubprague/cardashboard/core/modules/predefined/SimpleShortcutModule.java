package com.eclubprague.cardashboard.core.modules.predefined;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.modules.base.AbstractShortcutModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 02.09.2015.
 */
public class SimpleShortcutModule extends AbstractShortcutModule {

    public SimpleShortcutModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        super(titleResource, iconResource);
    }

    public SimpleShortcutModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(titleResource, iconResource, bgColorResource, fgColorResource);
    }

    public SimpleShortcutModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, @NonNull Intent intent, @NonNull StringResource errorMessage) {
        super(titleResource, iconResource, bgColorResource, fgColorResource, intent, errorMessage);
    }

    public SimpleShortcutModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull Intent intent, @NonNull StringResource errorMessage) {
        super(titleResource, iconResource, intent, errorMessage);
    }
}
