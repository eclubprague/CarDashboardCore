package com.eclubprague.cardashboard.core.modules;

import android.content.Context;
import android.widget.Toast;

import com.eclubprague.cardashboard.core.modules.base.SimpleAbstractModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 15. 7. 2015.
 */
public class TestModule extends SimpleAbstractModule {

    public TestModule(StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(titleResource, iconResource, bgColorResource, fgColorResource);
    }

    @Override
    public void onClick(Context context) {
        Toast.makeText(context, "short click", Toast.LENGTH_SHORT);
    }

    @Override
    public void onLongClick(Context context) {
        Toast.makeText(context, "long click", Toast.LENGTH_SHORT);
    }
}
