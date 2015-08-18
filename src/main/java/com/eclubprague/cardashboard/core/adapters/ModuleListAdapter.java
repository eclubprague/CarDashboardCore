package com.eclubprague.cardashboard.core.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.data.ModuleSupplier;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.views.ApplistGroupView;
import com.eclubprague.cardashboard.core.views.ApplistItemView;

/**
 * Created by Michael on 13.08.2015.
 */
public class ModuleListAdapter extends BaseExpandableListAdapter {

    private static final IconResource ICON_EXPAND = IconResource.fromResourceId(R.drawable.ic_expand_more_black_24dp);
    private static final IconResource ICON_COLLAPSE = IconResource.fromResourceId(R.drawable.ic_expand_less_black_24dp);

    private final IModuleContext moduleContext;
    private final ModuleSupplier moduleSupplier;
    private final IParentModule baseModule;
    private final OnModuleCheckListener onModuleCheckListener;
    private final OnModuleSelectListener onModuleSelectListener;
    private final boolean multiInsert;

    public ModuleListAdapter(IModuleContext moduleContext, OnModuleCheckListener onModuleCheckListener) {
        this.moduleContext = moduleContext;
        this.onModuleCheckListener = onModuleCheckListener;
        this.onModuleSelectListener = null;
        this.multiInsert = true;
        this.moduleSupplier = ModuleSupplier.getBaseInstance();
        this.baseModule = this.moduleSupplier.getHomeScreenModule(moduleContext);
    }

    public ModuleListAdapter(IModuleContext moduleContext, OnModuleSelectListener onModuleSelectListener) {
        this.moduleContext = moduleContext;
        this.onModuleCheckListener = null;
        this.onModuleSelectListener = onModuleSelectListener;
        this.multiInsert = false;
        this.moduleSupplier = ModuleSupplier.getBaseInstance();
        this.baseModule = this.moduleSupplier.getHomeScreenModule(moduleContext);
    }

    @Override
    public int getGroupCount() {
        return baseModule.getSubmodules().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        IParentModule parentModule = (IParentModule) getGroup(groupPosition);
        return parentModule.getSubmodules().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        IParentModule parentModule = (IParentModule) baseModule.getSubmodules().get(groupPosition);
        return parentModule;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        IParentModule parentModule = (IParentModule) getGroup(groupPosition);
        return parentModule.getSubmodules().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        IParentModule parentModule = (IParentModule) getGroup(groupPosition);
        return parentModule.getId().hashCode();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        IModule module = (IModule) getChild(groupPosition, childPosition);
        return module.getId().hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(moduleContext.getContext()).inflate(R.layout.applist_group, null);
        }
        ApplistGroupView applistGroupView = (ApplistGroupView) convertView;
        IParentModule parentModule = (IParentModule) getGroup(groupPosition);
        applistGroupView.setText(parentModule.getTitle());
        applistGroupView.setLeftIcon(parentModule.getIcon());
        if (isExpanded) {
            applistGroupView.setRightIcon(ICON_EXPAND);
        } else {
            applistGroupView.setRightIcon(ICON_COLLAPSE);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(moduleContext.getContext()).inflate(R.layout.applist_item, null);
        }
        final ApplistItemView applistItemView = (ApplistItemView) convertView;
        final IModule module = (IModule) getChild(groupPosition, childPosition);
        applistItemView.setText(module.getTitle());
        applistItemView.setLeftIcon(module.getIcon());
        if (multiInsert) {
            applistItemView.setCheckBoxVisible(true);
            applistItemView.setOnCheckListener(new ApplistItemView.OnCheckListener() {
                @Override
                public void onCheck(boolean check) {
                    if (check) {
                        onModuleCheckListener.onInsert(module);
                    } else {
                        onModuleCheckListener.onRemove(module);
                    }
                }
            });
        } else {
            applistItemView.setCheckBoxVisible(false);
            applistItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModuleSelectListener.onSelected(module);
                }
            });
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface OnModuleCheckListener {
        void onInsert(IModule module);

        void onRemove(IModule module);
    }

    public interface OnModuleSelectListener {
        void onSelected(IModule module);
    }

}
