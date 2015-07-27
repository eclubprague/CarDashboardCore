package com.eclubprague.cardashboard.core.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IModuleListener;
import com.eclubprague.cardashboard.core.modules.base.IQuickMenuListener;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Utility class containing tools for module views
 * <p>
 * Created by Michael on 14. 7. 2015.
 */
public class ModuleViewUtils {

    public static View preparePassive(View moduleView, IModuleListener listener, IModuleContext moduleContext, IconResource iconResource, StringResource titleResource) {
        TextView titleView = (TextView) moduleView.findViewById(R.id.module_title);
        titleResource.setInView(titleView);
        ImageView iconView = (ImageView) moduleView.findViewById(R.id.module_icon);
        iconResource.setInView(iconView);
        return setModuleListeners(moduleView, listener, moduleContext);
    }

    public static View prepareActive(View moduleView, IModuleListener listener, IModuleContext moduleContext, IconResource iconResource, StringResource titleResource, StringResource unitResource) {
        TextView titleView = (TextView) moduleView.findViewById(R.id.module_title);
        titleResource.setInView(titleView);
        ImageView iconView = (ImageView) moduleView.findViewById(R.id.module_icon);
        iconResource.setInView(iconView);
        TextView unitView = (TextView) moduleView.findViewById(R.id.module_unit);
        unitResource.setInView(unitView);
        return setModuleListeners(moduleView, listener, moduleContext);
    }

    private static View setModuleListeners(View moduleView, final IModuleListener listener, final IModuleContext moduleContext) {
        final ClickHolder clickHolder = new ClickHolder();
        moduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickHolder.longClicked) {
                    clickHolder.longClicked = false;
                } else {
                    listener.onClickEvent(moduleContext);
                }
            }
        });
        moduleView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClickEvent(moduleContext);
                clickHolder.longClicked = true;
                return false;
            }
        });
        return moduleView;
    }

    public static View prepareQuickMenu(View quickMenuView, final IQuickMenuListener listener, final IModuleContext moduleContext) {
        quickMenuView.findViewById(R.id.card_submenu_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel(moduleContext);
            }
        });
        quickMenuView.findViewById(R.id.card_submenu_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMove(moduleContext);
            }
        });
        quickMenuView.findViewById(R.id.card_submenu_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(moduleContext);
            }
        });
        quickMenuView.findViewById(R.id.card_submenu_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMore(moduleContext);
            }
        });
        return quickMenuView;
    }

    private static class ClickHolder {
        boolean longClicked = false;
    }
}
