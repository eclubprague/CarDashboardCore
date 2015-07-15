package com.eclubprague.cardashboard.core.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.modules.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.models.resources.StringResource;

/**
 * Utility class containing tools for module views
 *
 * Created by Michael on 14. 7. 2015.
 */
public class ModuleViewUtils {

    public static View preparePassive(View moduleView, Context context, IconResource iconResource, StringResource titleResource){
        TextView titleView = (TextView) moduleView.findViewById(com.eclubprague.cardashboard.core.R.id.module_title);
        titleView.setText(titleResource.getString(context));
        return moduleView;
    }
}
