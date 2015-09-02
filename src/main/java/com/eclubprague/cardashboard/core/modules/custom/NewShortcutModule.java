package com.eclubprague.cardashboard.core.modules.custom;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.eclubprague.cardashboard.core.fragments.ApplicationListDialogFragment;
import com.eclubprague.cardashboard.core.fragments.ModuleListDialogFragment;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.predefined.SimpleShortcutModule;

/**
 * Created by Michael on 02.09.2015.
 */
public class NewShortcutModule extends SimpleShortcutModule {
    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_shortcuts_new);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_exit_to_app_black_24dp);

    public NewShortcutModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE);
    }

    public NewShortcutModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource);
    }

    public void createNew(final ModuleListDialogFragment.OnAddModuleListener onAddModuleListener) {
        final IModuleContext moduleContext = GlobalApplication.getInstance().getModuleContext();
        ApplicationListDialogFragment.newInstance(moduleContext, new ApplicationListDialogFragment.OnApplicationSelectedListener() {
            @Override
            public void onApplicationSelected(ApplicationInfo applicationInfo) {
                PackageManager pm = moduleContext.getContext().getPackageManager();
                StringResource titleResource = StringResource.fromString(applicationInfo.loadLabel(pm).toString());
                IconResource iconResource = IconResource.fromDrawable(applicationInfo.loadIcon(pm));
                SimpleShortcutModule module = new SimpleShortcutModule(titleResource, iconResource);
                Intent intent = pm.getLaunchIntentForPackage(applicationInfo.packageName);
                module.setIntent(intent);
                onAddModuleListener.addModule(module);
            }
        }).show(moduleContext.getActivity().getFragmentManager(), "Application list");
    }
}
