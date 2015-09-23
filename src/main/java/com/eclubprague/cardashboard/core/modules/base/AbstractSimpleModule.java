package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.application.GlobalDataProvider;
import com.eclubprague.cardashboard.core.data.ModuleSupplier;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.fragments.RenameDialogFragment;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Simple implementation of IModule interface.
 */
abstract public class AbstractSimpleModule implements IModule {
    private final ModuleId id;
    private final ModuleEnum moduleEnum;
    private StringResource titleResource;
    private IconResource iconResource;
    private ColorResource bgColorResource;
    private ColorResource fgColorResource;
    private ModuleView view;
    private ViewGroup holderView;
    private static final String TAG = AbstractSimpleModule.class.getSimpleName();

    public AbstractSimpleModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        this.moduleEnum = moduleEnum;
        this.id = ModuleId.createNew();
        this.titleResource = titleResource;
        this.iconResource = iconResource;
    }

    public AbstractSimpleModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        this.moduleEnum = moduleEnum;
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
        view.setModule(moduleContext, this);
        return view;
    }

    @Override
    public ViewWithHolder<ModuleView> createViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder<ModuleView> viewWithHolder = createNewViewWithHolder(moduleContext, holderResourceId, holderParent);
        Log.d(TAG, this + ": setting view: " + viewWithHolder.view);
        view = viewWithHolder.view;
        Log.d(TAG, this + ": setting holder: " + viewWithHolder.holder);
        holderView = viewWithHolder.holder;
        view.setModule(moduleContext, this);
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
    public ModuleView getView() {
        return view;
    }
//    @Override
//    public List<ModuleView> getViews(IModuleContext context) {
//        List<ModuleView> moduleViews = viewMap.get(context);
//        if (moduleViews == null) {
//            moduleViews = Collections.emptyList();
//        }
//        return moduleViews;
//    }

//    @Override
//    public IModule removeViews(IModuleContext context) {
//        viewMap.remove(context);
//        return this;
//    }

//    @Override
//    public IModule removeView(IModuleContext moduleContext, ModuleView moduleView) {
//        List<ModuleView> moduleViews = viewMap.get(moduleContext);
//        if (moduleViews != null) {
//            moduleViews.remove(moduleView);
//        }
//        return this;
//    }

//    @Override
//    public IModule removeView(ModuleView moduleView) {
//        for(List<ModuleView> views : viewMap.values()){
//            views.remove(moduleView);
//        }
//        return this;
//    }

//    @Override
//    public IModule addView(IModuleContext context, ModuleView view) {
//        List<ModuleView> moduleViews = viewMap.get(context);
//        if (moduleViews == null) {
//            moduleViews = new ArrayList<>();
//            viewMap.put(context, moduleViews);
//        }
//        moduleViews.add(view);
//        return this;
//    }

    @Override
    public void setHolder(ViewGroup holder) {
        holderView = holder;
    }

    @Override
    public ViewGroup getHolder() {
        return holderView;
    }

    @Override
    public void onClickEvent(IModuleContext context) {
        context.turnQuickMenusOff();
    }

    @Override
    public void onLongClickEvent(IModuleContext context) {
        context.turnQuickMenusOff();
        context.toggleQuickMenu(this, true);
    }

    @Override
    public void onEvent(ModuleEvent event, final IModuleContext moduleContext) {
        moduleContext.onModuleEvent(this, event);
        switch (event) {
            case DELETE:
                ModuleSupplier.getPersonalInstance().remove(this);
                break;
            case RENAME:
                RenameDialogFragment.newInstance(getTitle().getString(moduleContext.getContext())
                        , new RenameDialogFragment.OnTitleEnteredListener() {
                    @Override
                    public void onTitleEntered(String title) {
                        setTitle(StringResource.fromString(title));
                    }
                }).show(moduleContext.getActivity().getFragmentManager(), "rename");
                moduleContext.turnQuickMenusOff();
                break;
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", titleResource=" + titleResource.getString(GlobalDataProvider.getInstance().getModuleContext().getContext()) +
                "}";
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
    public void onPause(IModuleContext moduleContext) {

    }

    @Override
    public void onResume(IModuleContext moduleContext) {

    }

    @Override
    public void onStart(IModuleContext moduleContext) {

    }

    @Override
    public void onStop(IModuleContext moduleContext) {

    }

    @Override
    public void onDestroy(IModuleContext moduleContext) {
//        viewMap.remove(moduleContext);
    }

    @Override
    public List<ModuleEvent> getAvailableActions() {
        return Arrays.asList(ModuleEvent.CANCEL, ModuleEvent.DELETE, ModuleEvent.RENAME);
    }

    public boolean hasForegroundColor() {
        return fgColorResource != null;
    }

    public boolean hasBackgroundColor() {
        return bgColorResource != null;
    }

    @Override
    public ModuleEnum getModuleEnum() {
        return moduleEnum;
    }
}
