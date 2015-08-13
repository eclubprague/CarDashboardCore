package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 13.08.2015.
 * <p/>
 * Module view containing a single Module.
 */
public class ModuleView extends RelativeLayout {
    private ImageView iconView;
    private TextView titleView;

    public ModuleView(Context context) {
        super(context);
    }

    public ModuleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ModuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ModuleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ModuleView setIcon(@NonNull IconResource iconResource) {
        if (iconView == null) {
            iconView = (ImageView) findViewById(R.id.module_icon);
        }
        iconResource.setInView(iconView);
        return this;
    }

    public ModuleView setTitle(@NonNull StringResource titleResource) {
        if (titleView == null) {
            titleView = (TextView) findViewById(R.id.module_title);
        }
        titleResource.setInView(titleView);
        return this;
    }
}
