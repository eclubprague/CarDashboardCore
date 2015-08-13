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
import com.eclubprague.cardashboard.core.views.ApplistGroupView;
import com.eclubprague.cardashboard.core.views.ApplistItemView;

/**
 * Created by Michael on 13.08.2015.
 */
public class ModuleListAdapter extends BaseExpandableListAdapter {

    private final IModuleContext moduleContext;
    private final ModuleSupplier moduleSupplier;
    private final IParentModule baseModule;

    public ModuleListAdapter(IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
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
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(moduleContext.getContext()).inflate(R.layout.applist_item, null);
        }
        ApplistItemView applistItemView = (ApplistItemView) convertView;
        IModule module = (IModule) getChild(groupPosition, childPosition);
        applistItemView.setText(module.getTitle());
        applistItemView.setLeftIcon(module.getIcon());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
