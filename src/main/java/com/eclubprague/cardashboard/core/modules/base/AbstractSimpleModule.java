package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Simple implementation of IModule interface.
 */
abstract public class AbstractSimpleModule implements IModule {
    private final ModuleId id;
    private IModuleContext moduleContext;
    private IParentModule parent;
    private StringResource titleResource;
    private IconResource iconResource;
    private ColorResource bgColorResource;
    private ColorResource fgColorResource;
    private boolean longClicked = false;
    private View view;
    private static final String TAG = AbstractSimpleModule.class.getSimpleName();

    public AbstractSimpleModule(StringResource titleResource, IconResource iconResource) {
        this.id = ModuleId.createNew();
        this.titleResource = titleResource;
        this.iconResource = iconResource;
    }

    public AbstractSimpleModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource) {
        this.id = ModuleId.createNew();
        this.moduleContext = moduleContext;
        this.parent = parent;
        this.titleResource = titleResource;
        this.iconResource = iconResource;
    }

    public AbstractSimpleModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        this.id = ModuleId.createNew();
        this.moduleContext = moduleContext;
        this.parent = parent;
        this.titleResource = titleResource;
        this.iconResource = iconResource;
        this.bgColorResource = bgColorResource;
        this.fgColorResource = fgColorResource;
    }

    @Override
    public IconResource getIcon() {
        if (iconResource == null) {
            throw new IllegalStateException("Icon requested, but is null.");
        }
        return iconResource;
    }

    @Override
    public StringResource getTitle() {
        if (titleResource == null) {
            throw new IllegalStateException("Title requested, but is null.");
        }
        return titleResource;
    }

    @Override
    public ColorResource getBackgroundColor() {
        if (bgColorResource == null) {
            throw new IllegalStateException("Background color requested, but is null.");
        }
        return bgColorResource;
    }

    @Override
    public ColorResource getForegroundColor() {
        if (fgColorResource == null) {
            throw new IllegalStateException("Foreground color requested, but is null.");
        }
        return fgColorResource;
    }

    @Override
    public IParentModule getParent() {
        if (parent == null) {
            throw new IllegalStateException("Parent requested, but is null.");
        }
        return parent;
    }

    @Override
    public IModule setModuleContext(@NonNull IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
        return this;
    }

    @Override
    public IModule setParent(@NonNull IParentModule parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public IModule setTitle(@NonNull StringResource titleResource) {
        this.titleResource = titleResource;
        return this;
    }

    @Override
    public IModule setIcon(@NonNull IconResource iconResource) {
        this.iconResource = iconResource;
        return this;
    }

    @Override
    public IModule setBackgroundColor(@NonNull ColorResource bgColorResource) {
        this.bgColorResource = bgColorResource;
        return this;
    }

    @Override
    public IModule setForegroundColor(@NonNull ColorResource fgColorResource) {
        this.fgColorResource = fgColorResource;
        return this;
    }

    @Override
    public ModuleId getId() {
        return id;
    }

    @Override
    public View createView(final Context context, ViewGroup parent) {
        view = createNewView(context, parent);
        setListeners(context, view);
        return view;
    }

    @Override
    public ViewWithHolder createViewWithHolder(final Context context, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder viewWithHolder = createNewViewWithHolder(context, holderResourceId, holderParent);
        view = viewWithHolder.view;
        setListeners(context, view);
        return viewWithHolder;
    }

    private void setListeners(final Context context, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (longClicked) {
                    longClicked = false;
                    Log.d(TAG, "absorbed click");
                } else {
                    Log.d(TAG, "click");
                    onClickEvent(context);
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongClickEvent(context);
                longClicked = true;
                Log.d(TAG, "long click");
                return false;
            }
        });
    }

    @Override
    public IModuleContext getModuleContext() {
        return moduleContext;
    }

    abstract protected View createNewView(Context context, ViewGroup parent);

    abstract protected ViewWithHolder createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent);

    @Override
    public View getView() {
        return view;
    }

    @Override
    public String toString() {
        return "AbstractSimpleModule{" +
                "id=" + id +
                ", moduleContext=" + moduleContext +
                ", parent=" + parent +
                ", titleResource=" + titleResource +
                ", iconResource=" + iconResource +
                ", bgColorResource=" + bgColorResource +
                ", fgColorResource=" + fgColorResource +
                ", longClicked=" + longClicked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSimpleModule that = (AbstractSimpleModule) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
