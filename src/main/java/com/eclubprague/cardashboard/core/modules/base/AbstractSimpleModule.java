package com.eclubprague.cardashboard.core.modules.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.views.ModuleView;
import com.eclubprague.cardashboard.core.views.ModuleViewFactory;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Simple implementation of IModule interface.
 */
abstract public class AbstractSimpleModule implements IModule {
    private final ModuleId id;
    private IModuleContext moduleContext;
    private StringResource titleResource;
    private IconResource iconResource;
    private ColorResource bgColorResource;
    private ColorResource fgColorResource;
    private boolean quickMenuActive = false;
    private ModuleView view;
    private ViewGroup holderView;
    private static final String TAG = AbstractSimpleModule.class.getSimpleName();

    public AbstractSimpleModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        this.id = ModuleId.createNew();
        this.titleResource = titleResource;
        this.iconResource = iconResource;
    }

    public AbstractSimpleModule(@NonNull IModuleContext moduleContext, @NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        this.id = ModuleId.createNew();
        this.moduleContext = moduleContext;
        this.titleResource = titleResource;
        this.iconResource = iconResource;
    }

    public AbstractSimpleModule(@NonNull IModuleContext moduleContext, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        this.id = ModuleId.createNew();
        this.moduleContext = moduleContext;
        this.titleResource = titleResource;
        this.iconResource = iconResource;
        this.bgColorResource = bgColorResource;
        this.fgColorResource = fgColorResource;
    }

    @Override
    public IconResource getIcon() {
        if (iconResource == null) {
            throw new IllegalStateException("Icon is null. Please, use setIcon method to set IconResource first.");
        }
        return iconResource;
    }

    @Override
    public StringResource getTitle() {
        if (titleResource == null) {
            throw new IllegalStateException("Title is null. Please, use setTitle method to set title StringResource first.");
        }
        return titleResource;
    }

    @Override
    public ColorResource getBackgroundColor() {
        if (bgColorResource == null) {
            throw new IllegalStateException("Background color is null. Please, use setBackgroundColor method to set background ColorResource first.");
        }
        return bgColorResource;
    }

    @Override
    public ColorResource getForegroundColor() {
        if (fgColorResource == null) {
            throw new IllegalStateException("Foreground color is null. Please, use setForegroundColor method to set foreground ColorResource first.");
        }
        return fgColorResource;
    }

    @Override
    public IModule setModuleContext(@NonNull IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
        return this;
    }

    @Override
    public IModule setTitle(@NonNull StringResource titleResource) {
        this.titleResource = titleResource;
        if (view != null) {
            view.setTitle(titleResource);
        }
        return this;
    }

    @Override
    public IModule setIcon(@NonNull IconResource iconResource) {
        this.iconResource = iconResource;
        if (view != null) {
            view.setIcon(iconResource);
        }
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
    public ModuleView createView(final Context context, ViewGroup parent) {
        view = createNewView(context, parent);
        return view;
    }

    @Override
    public ViewWithHolder<ModuleView> createViewWithHolder(final Context context, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder<ModuleView> viewWithHolder = createNewViewWithHolder(context, holderResourceId, holderParent);
        view = viewWithHolder.view;
        holderView = viewWithHolder.holder;
        return viewWithHolder;
    }


    @Override
    public View createQuickMenuView(Context context, ViewGroup parent) {
        return ModuleViewFactory.createQuickMenu(context, parent, this, getModuleContext());
    }

    @Override
    public ViewWithHolder createQuickMenuViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createQuickMenuWithHolder(context, holderResourceId, holderParent, this, getModuleContext());
    }

    @Override
    public IModuleContext getModuleContext() {
        if (moduleContext == null) {
            throw new IllegalStateException("ModuleContext is null. Please, use setModuleContext method to set IModuleContext first.");
        }
        return moduleContext;
    }

    abstract protected ModuleView createNewView(Context context, ViewGroup parent);

    abstract protected ViewWithHolder<ModuleView> createNewViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent);

    @Override
    public ModuleView getView() {
        if (view == null) {
            throw new IllegalStateException("ModuleView is null. Please, use createNewView or createNewViewWithHolder method to set ModuleView first.");
        }
        return view;
    }

    @Override
    public void setHolder(ViewGroup holder) {
        holderView = holder;
    }

    @Override
    public ViewGroup getHolder() {
        if (holderView == null) {
            throw new IllegalStateException("Holder has not been saved. Please, use setHolder to save ViewGroup holder or use createViewWithHolder to create view including holder.");
        }
        return holderView;
    }

    @Override
    public void onClickEvent(IModuleContext context) {
        context.turnQuickMenusOff();
    }

    @Override
    public void onLongClickEvent(IModuleContext context) {
        context.turnQuickMenusOff();
        quickMenuActive = true;
        context.toggleQuickMenu(this, true);
    }

    @Override
    public void onCancel(IModuleContext moduleContext) {
        if (quickMenuActive) {
            getModuleContext().onModuleEvent(this, ModuleEvent.CANCEL);
            quickMenuActive = false;
        }
    }

    @Override
    public void onDelete(IModuleContext moduleContext) {
        if (quickMenuActive) {
//            getParent().removeSubmodule(this);
            getModuleContext().onModuleEvent(this, ModuleEvent.DELETE);
            quickMenuActive = false;
        }
    }

    @Override
    public void onMove(IModuleContext moduleContext) {
        if (quickMenuActive) {
            getModuleContext().onModuleEvent(this, ModuleEvent.MOVE);
            quickMenuActive = false;
        }
    }

    @Override
    public void onMore(IModuleContext moduleContext) {
        if (quickMenuActive) {
            quickMenuActive = false;
        }
    }

    @Override
    public String toString() {
        return "AbstractSimpleModule{" +
                "id=" + id +
                ", moduleContext=" + moduleContext +
                ", titleResource=" + titleResource.getString(getModuleContext().getContext()) +
                ", iconResource=" + iconResource +
                ", bgColorResource=" + bgColorResource +
                ", fgColorResource=" + fgColorResource +
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

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public boolean isInitialized() {
        return moduleContext != null;
    }
}
