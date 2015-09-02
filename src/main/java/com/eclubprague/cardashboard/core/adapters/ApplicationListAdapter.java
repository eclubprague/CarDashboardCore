package com.eclubprague.cardashboard.core.adapters;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.fragments.ApplicationListDialogFragment;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ApplistView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 02.09.2015.
 */
public class ApplicationListAdapter extends BaseAdapter {
    public static final String TAG = ApplicationListAdapter.class.getSimpleName();
    private final IModuleContext moduleContext;
    private final ApplicationListDialogFragment.OnApplicationSelectedListener onApplicationSelectedListener;
    private final List<ApplicationInfo> applications;
    private final PackageManager packageManager;

    public ApplicationListAdapter(IModuleContext moduleContext, ApplicationListDialogFragment.OnApplicationSelectedListener onApplicationSelectedListener) {
        this.moduleContext = moduleContext;
        this.onApplicationSelectedListener = onApplicationSelectedListener;
        packageManager = moduleContext.getContext().getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

//        for (ApplicationInfo applicationInfo : apps) {
//            Log.d(TAG, "Installed package :" + applicationInfo.packageName);
//            Log.d(TAG, "Source dir : " + applicationInfo.sourceDir);
//            Log.d(TAG, "Launch Activity :" + packageManager.getLaunchIntentForPackage(applicationInfo.packageName));
//        }
        this.applications = new ArrayList<>();
        for (ApplicationInfo appInfo : apps) {
            if (appInfo.loadLabel(packageManager) == null) {
                Log.d(TAG, "name is null");
            } else if (packageManager.getLaunchIntentForPackage(appInfo.packageName) == null) {
                Log.d(TAG, "intent is null");
            } else {
                Log.d(TAG, "Adding application: " + appInfo.toString());
                applications.add(appInfo);
            }
        }
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @Override
    public Object getItem(int position) {
        return applications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(moduleContext.getContext()).inflate(R.layout.item_application_list, null);
        }
        final ApplistView applistItemView = (ApplistView) convertView;
        final ApplicationInfo appInfo = (ApplicationInfo) getItem(position);
        Log.d(TAG, appInfo.toString());
        applistItemView.setText(StringResource.fromString(appInfo.loadLabel(packageManager).toString()));
        applistItemView.setLeftIcon(IconResource.fromDrawable(appInfo.loadIcon(packageManager)));
        applistItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onApplicationSelectedListener.onApplicationSelected(appInfo);
            }
        });
        return convertView;
    }
}
