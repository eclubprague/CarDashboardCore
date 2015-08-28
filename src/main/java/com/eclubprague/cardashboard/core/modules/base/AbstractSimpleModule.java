package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Simple implementation of IModule interface.
 */
abstract public class AbstractSimpleModule implements IModule {
    private final ModuleId id;
    private StringResource titleResource;
    private IconResource iconResource;
    private ColorResource bgColorResource;
    private ColorResource fgColorResource;
    private ModuleView view;
    private HashMap<IModuleContext, List<ModuleView>> viewMap = new HashMap<>();
    private ViewGroup holderView;
    private static final String TAG = AbstractSimpleModule.class.getSimpleName();

    public AbstractSimpleModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        this.id = ModuleId.createNew();
        this.titleResource = titleResource;
        this.iconResource = iconResource;
    }

    public AbstractSimpleModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        this.id = ModuleId.createNew();
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
    public ModuleView createView(IModuleContext moduleContext, ViewGroup parent) {
        view = createNewView(moduleContext, parent);
        addView(moduleContext, view);
        view.setModule(this);
        return view;
    }

    @Override
    public ViewWithHolder<ModuleView> createViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder<ModuleView> viewWithHolder = createNewViewWithHolder(moduleContext, holderResourceId, holderParent);
        view = viewWithHolder.view;
        addView(moduleContext, view);
        holderView = viewWithHolder.holder;
        view.setModule(this);
        view.setViewHolder(holderView);
        return viewWithHolder;
    }


    @Override
    public View createQuickMenuView(IModuleContext moduleContext, ViewGroup parent) {
        return ModuleViewFactory.createQuickMenu(moduleContext, parent, this);
    }

    @Override
    public ViewWithHolder createQuickMenuViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createQuickMenuWithHolder(moduleContext, holderResourceId, holderParent, this);
    }

    abstract protected ModuleView createNewView(IModuleContext context, ViewGroup parent);

    abstract protected ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext context, int holderResourceId, ViewGroup holderParent);

    @Override
    public List<ModuleView> getViews(IModuleContext context) {
        List<ModuleView> moduleViews = viewMap.get(context);
        if (moduleViews == null) {
            moduleViews = Collections.emptyList();
        }
        return moduleViews;
    }

    @Override
    public IModule removeViews(IModuleContext context) {
        viewMap.remove(context);
        return this;
    }

    private void addView(IModuleContext context, ModuleView view) {
        List<ModuleView> moduleViews = viewMap.get(context);
        if (moduleViews == null) {
            moduleViews = new ArrayList<>();
            viewMap.put(context, moduleViews);
        }
        moduleViews.add(view);
    }

    @Override
    public void setHolder(ViewGroup holder) {
        holderView = holder;
    }

    @Override
    public ViewGroup getHolder() {
        return holderView;
    }

    @Override
    public void onClickEvent(IModuleContext context, ModuleView moduleView) {
        context.turnQuickMenusOff();
    }

    @Override
    public void onLongClickEvent(IModuleContext context, ModuleView moduleView) {
        context.turnQuickMenusOff();
        context.toggleQuickMenu(this, moduleView, true);
    }

    @Override
    public void onEvent(ModuleEvent event, ModuleView moduleView, IModuleContext moduleContext) {
        moduleContext.onModuleEvent(this, moduleView, event);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", titleResource=" + titleResource.getString(GlobalApplication.getInstance().getModuleContext().getContext()) +
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

    @Override
    public Set<ModuleEvent> getAvailableActions() {
        return EnumSet.of(ModuleEvent.CANCEL, ModuleEvent.DELETE);
    }

    public boolean hasForegroundColor() {
        return fgColorResource != null;
    }

    public boolean hasBackgroundColor() {
        return bgColorResource != null;
    }
}
