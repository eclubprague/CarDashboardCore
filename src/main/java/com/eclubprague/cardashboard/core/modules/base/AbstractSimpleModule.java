package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Simple implementation of IModule interface.
 */
abstract public class AbstractSimpleModule implements IModule {
    private final IModule parent;
    private final StringResource titleResource;
    private final IconResource iconResource;
    private final ColorResource bgColorResource;
    private final ColorResource fgColorResource;

    public AbstractSimpleModule(IModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        this.parent = parent;
        this.titleResource = titleResource;
        this.iconResource = iconResource;
        this.bgColorResource = bgColorResource;
        this.fgColorResource = fgColorResource;
    }

    @Override
    public IconResource getIcon() {
        return iconResource;
    }

    @Override
    public StringResource getTitle() {
        return titleResource;
    }

    @Override
    public ColorResource getBackgroundColor() {
        return bgColorResource;
    }

    @Override
    public ColorResource getForegroundColor() {
        return fgColorResource;
    }

    @Override
    public IModule getParent() {
        return parent;
    }

    @Override
    public View createView(final Context context, ViewGroup parent) {
        View view = createNewView(context, parent);
        setListeners(context, view);
        return view;
    }

    @Override
    public ViewGroup createViewWithHolder(final Context context, int holderResourceId, ViewGroup holderParent) {
        ViewGroup viewGroup = createNewViewWithHolder(context, holderResourceId, holderParent);
        setListeners(context, viewGroup);
        return viewGroup;
    }

    private void setListeners(final Context context, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AbstractSimpleModule", "clicked");
                onClickEvent(context);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongClickEvent(context);
                return false;
            }
        });
    }

    abstract protected View createNewView(Context context, ViewGroup parent);

    abstract protected ViewGroup createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent);
}
