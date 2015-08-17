package com.eclubprague.cardashboard.core.views;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.adapters.ModuleListAdapter;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;

/**
 * Created by Michael on 13.08.2015.
 */
public class ModuleListDialogFragment extends DialogFragment {

    private IModuleContext moduleContext;

    public static ModuleListDialogFragment newInstance(IModuleContext moduleContext) {
        ModuleListDialogFragment f = new ModuleListDialogFragment();
        f.setModuleContext(moduleContext);
        return f;
    }

    public void setModuleContext(IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listView = inflater.inflate(R.layout.fragment_application_list, container, false);
        ExpandableListView list = (ExpandableListView) listView.findViewById(R.id.applist_list_view);
//        list.setGroupIndicator(null);
        list.setAdapter(new ModuleListAdapter(moduleContext));

        int width = getResources().getDisplayMetrics().widthPixels;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            list.setIndicatorBounds(width - getPixelValue(40), width - getPixelValue(10));
        } else {
            list.setIndicatorBoundsRelative(width - getPixelValue(40), width - getPixelValue(10));
        }

        return listView;
    }

    public int getPixelValue(int dp) {
        final float scale = moduleContext.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
