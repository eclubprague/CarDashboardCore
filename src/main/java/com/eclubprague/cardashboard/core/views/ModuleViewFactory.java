package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.modules.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewUtils;

/**
 * Factory class creating new module views.
 *
 * Created by Michael on 14. 7. 2015.
 */
public class ModuleViewFactory {
    public static View createPassive(Context context, ViewGroup parent, IconResource iconResource, StringResource titleResource) {
        View view = LayoutInflater.from(context).inflate(com.eclubprague.cardashboard.core.R.layout.module_passive_icon_title, parent, false);
        return ModuleViewUtils.preparePassive(view, context, iconResource, titleResource);
    }

    public static ViewGroup createPassiveWithHolder(Context context, int holderResourceId, ViewGroup holderParent, IconResource iconResource, StringResource titleResource) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(context).inflate(holderResourceId, holderParent, false);
        holder.addView(createPassive(context, holder, iconResource, titleResource));
        return holder;
    }
}
