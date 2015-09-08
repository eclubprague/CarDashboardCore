package com.eclubprague.cardashboard.core.fragments;

import android.app.DialogFragment;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.adapters.ApplicationListAdapter;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;

/**
 * Created by Michael on 02.09.2015.
 */
public class ApplicationListDialogFragment extends DialogFragment {
    private IModuleContext moduleContext;
    private OnApplicationSelectedListener onApplicationSelectedListener;

    public static ApplicationListDialogFragment newInstance(IModuleContext moduleContext, OnApplicationSelectedListener onApplicationSelectedListener) {
        ApplicationListDialogFragment f = new ApplicationListDialogFragment();
        f.setModuleContext(moduleContext);
        f.setOnApplicationSelectedListener(onApplicationSelectedListener);
        return f;
    }


    public void setModuleContext(IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
    }

    public void setOnApplicationSelectedListener(OnApplicationSelectedListener onApplicationSelectedListener) {
        this.onApplicationSelectedListener = onApplicationSelectedListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View listView = inflater.inflate(R.layout.fragment_application_list, container, false);
        ListView list = (ListView) listView.findViewById(R.id.applist_list_view);

        ApplicationListAdapter adapter = new ApplicationListAdapter(moduleContext, onApplicationSelectedListener);
        list.setAdapter(adapter);
        TextView cancelView = (TextView) listView.findViewById(R.id.applist_button_cancel);
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        TextView addView = (TextView) listView.findViewById(R.id.applist_button_add);
        addView.setVisibility(View.GONE);
        return listView;
    }

    public interface OnApplicationSelectedListener {
        void onApplicationSelected(ApplicationInfo applicationInfo);
    }
}
