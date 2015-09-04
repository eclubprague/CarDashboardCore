package com.eclubprague.cardashboard.core.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.data.modules.ModuleInfo;
import com.eclubprague.cardashboard.core.data.modules.ModuleInfoContainer;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.views.ApplistGroupView;
import com.eclubprague.cardashboard.core.views.ApplistItemView;

import java.util.List;

/**
 * Created by Michael on 13.08.2015.
 */
public class ModuleListAdapter extends BaseExpandableListAdapter {

    private static final IconResource ICON_EXPAND = IconResource.fromResourceId(R.drawable.ic_expand_more_black_24dp);
    private static final IconResource ICON_COLLAPSE = IconResource.fromResourceId(R.drawable.ic_expand_less_black_24dp);

    private final IModuleContext moduleContext;
    private final List<ModuleInfoContainer> moduleInfoContainers;
    private final OnModuleCheckListener onModuleCheckListener;
    private final OnModuleSelectListener onModuleSelectListener;
    private final boolean multiInsert;

    public ModuleListAdapter(IModuleContext moduleContext, OnModuleCheckListener onModuleCheckListener) {
        this.moduleContext = moduleContext;
        this.onModuleCheckListener = onModuleCheckListener;
        this.onModuleSelectListener = null;
        this.multiInsert = true;
        this.moduleInfoContainers = ModuleInfoContainer.getModules();
    }

    public ModuleListAdapter(IModuleContext moduleContext, OnModuleSelectListener onModuleSelectListener) {
        this.moduleContext = moduleContext;
        this.onModuleCheckListener = null;
        this.onModuleSelectListener = onModuleSelectListener;
        this.multiInsert = false;
        this.moduleInfoContainers = ModuleInfoContainer.getModules();
    }

    @Override
    public int getGroupCount() {
        return moduleInfoContainers.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ModuleInfoContainer moduleInfoContainer = moduleInfoContainers.get(groupPosition);
        return moduleInfoContainer.getSize();
    }

    @Override
    public Object getGroup(int groupPosition) {
        ModuleInfoContainer moduleInfoContainer = moduleInfoContainers.get(groupPosition);
        return moduleInfoContainer;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ModuleInfoContainer moduleInfoContainer = (ModuleInfoContainer) getGroup(groupPosition);
        return moduleInfoContainer.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        ModuleInfoContainer moduleInfoContainer = (ModuleInfoContainer) getGroup(groupPosition);
        return moduleInfoContainer.getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        ModuleInfo module = (ModuleInfo) getChild(groupPosition, childPosition);
        return module.getId();
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
        ModuleInfoContainer moduleInfoContainer = (ModuleInfoContainer) getGroup(groupPosition);
        applistGroupView.setText(moduleInfoContainer.getTitle());
        applistGroupView.setLeftIcon(moduleInfoContainer.getIcon());
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
        final ModuleInfo module = (ModuleInfo) getChild(groupPosition, childPosition);
        applistItemView.setText(module.getTitle());
        applistItemView.setLeftIcon(module.getIcon());
        if (multiInsert) {
            applistItemView.setCheckBoxVisible(true);
            applistItemView.setOnCheckListener(new ApplistItemView.OnCheckListener() {
                @Override
                public void onCheck(boolean check) {
                    if (check) {
                        onModuleCheckListener.onInsert(module.getModule());
                    } else {
                        onModuleCheckListener.onRemove(module.getModule());
                    }
                }
            });
        } else {
            applistItemView.setCheckBoxVisible(false);
            applistItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModuleSelectListener.onSelected(module.getModule());
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
        void onInsert(ModuleEnum module);

        void onRemove(ModuleEnum module);
    }

    public interface OnModuleSelectListener {
        void onSelected(ModuleEnum module);
    }

}
