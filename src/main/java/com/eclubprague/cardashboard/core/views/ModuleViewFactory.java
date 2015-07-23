package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewUtils;

/**
 * Factory class creating new module views.
 *
 * Created by Michael on 14. 7. 2015.
 */
public class ModuleViewFactory {
    public static View createPassive(Context context, ViewGroup parent, IconResource iconResource, StringResource titleResource) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_passive_icon_title, parent, false);
        return ModuleViewUtils.preparePassive(view, context, iconResource, titleResource);
    }

    public static ViewWithHolder createPassiveWithHolder(Context context, int holderResourceId, ViewGroup holderParent, IconResource iconResource, StringResource titleResource) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(context).inflate(holderResourceId, holderParent, false);
        View view = createPassive(context, holder, iconResource, titleResource);
        holder.addView(view);
        return new ViewWithHolder(view, holder);
    }

    public static View createActive(Context context, ViewGroup parent, IconResource iconResource, StringResource titleResource, StringResource unitResource) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_active_icon, parent, false);
        return ModuleViewUtils.prepareActive(view, context, iconResource, titleResource, unitResource);
    }


    public static ViewWithHolder createActiveWithHolder(Context context, int holderResourceId, ViewGroup holderParent, IconResource iconResource, StringResource titleResource, StringResource unitResource) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(context).inflate(holderResourceId, holderParent, false);
        View view = createActive(context, holder, iconResource, titleResource, unitResource);
        holder.addView(view);
        return new ViewWithHolder(view, holder);
    }
}
