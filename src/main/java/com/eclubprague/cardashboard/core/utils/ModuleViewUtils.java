package com.eclubprague.cardashboard.core.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Utility class containing tools for module views
 *
 * Created by Michael on 14. 7. 2015.
 */
public class ModuleViewUtils {

    public static View preparePassive(View moduleView, Context context, IconResource iconResource, StringResource titleResource){
        TextView titleView = (TextView) moduleView.findViewById(R.id.module_title);
        titleResource.setInView(titleView);
        ImageView iconView = (ImageView) moduleView.findViewById(R.id.module_icon);
        iconResource.setInView(iconView);
        return moduleView;
    }
}
