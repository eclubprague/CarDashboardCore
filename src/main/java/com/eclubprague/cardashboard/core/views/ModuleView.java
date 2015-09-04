package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.ViewId;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 13.08.2015.
 * <p/>
 * Module view containing a single Module.
 */
public class ModuleView extends RelativeLayout {
    private IModule module;
    private IModuleContext moduleContext;
    private ImageView iconView;
    private TextView titleView;
    private ViewGroup viewHolder;
    private final ViewId viewId = ViewId.createNew();

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

    public IModule getModule() {
        return module;
    }

    public void setModule(final IModuleContext moduleContext, final IModule module) {
        this.moduleContext = moduleContext;
        this.module = module;
//        final ModuleView thisView = this;
//        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
//            @Override
//            public void onViewAttachedToWindow(View v) {
//                module.addView(moduleContext, thisView);
//            }
//
//            @Override
//            public void onViewDetachedFromWindow(View v) {
//                module.removeView(moduleContext, thisView);
//            }
//        });
    }

    public ViewId getViewId() {
        return viewId;
    }

    public ViewGroup getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(ViewGroup viewHolder) {
        this.viewHolder = viewHolder;
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

    @Override
    public String toString() {
        return "ModuleView{" +
                "viewId=" + viewId +
                ", module=" + module +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleView that = (ModuleView) o;

        return !(viewId != null ? !viewId.equals(that.viewId) : that.viewId != null);

    }

    @Override
    public int hashCode() {
        return viewId != null ? viewId.hashCode() : 0;
    }
}
