package com.eclubprague.cardashboard.core.modules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleId;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 22. 7. 2015.
 * <p/>
 * Testing submenu, will be deleted
 */
public class TestSubmenuModule implements IModule {


    private final ModuleId id;
    private IModuleContext moduleContext;
    private IParentModule parent;
    private StringResource titleResource;
    private IconResource iconResource;
    private ColorResource bgColorResource;
    private ColorResource fgColorResource;

    public TestSubmenuModule(IModuleContext moduleContext, IParentModule parent) {
        this.id = ModuleId.createNew();
        this.moduleContext = moduleContext;
        this.parent = parent;
    }

    public TestSubmenuModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource) {
        this.id = ModuleId.createNew();
        this.moduleContext = moduleContext;
        this.parent = parent;
        this.titleResource = titleResource;
        this.iconResource = iconResource;
    }

    public TestSubmenuModule(IModuleContext moduleContext, IParentModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        this.id = ModuleId.createNew();
        this.moduleContext = moduleContext;
        this.parent = parent;
        this.titleResource = titleResource;
        this.iconResource = iconResource;
        this.bgColorResource = bgColorResource;
        this.fgColorResource = fgColorResource;
    }

    @Override
    public ModuleId getId() {
        return id;
    }

    @Override
    public void onClickEvent(Context context) {

    }

    @Override
    public void onLongClickEvent(Context context) {

    }

    @Override
    public View createView(Context context, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.module_submenu, parent, false);
    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public ViewWithHolder createViewWithHolder(Context context, int holderResourceId, ViewGroup holderParent) {
        ViewGroup holder = (ViewGroup) LayoutInflater.from(context).inflate(holderResourceId, holderParent, false);
        View view = createView(context, holder);
        holder.addView(view);
        return new ViewWithHolder(view, holder);
    }

    @Override
    public IModuleContext getModuleContext() {
        return moduleContext;
    }

    @Override
    public IModule setModuleContext(IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
        return this;
    }

    @Override
    public IParentModule getParent() {
        return parent;
    }

    @Override
    public IModule setParent(IParentModule parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public StringResource getTitle() {
        return titleResource;
    }

    @Override
    public IModule setTitle(StringResource titleResource) {
        this.titleResource = titleResource;
        return this;
    }

    @Override
    public IconResource getIcon() {
        return iconResource;
    }

    @Override
    public IModule setIcon(IconResource iconResource) {
        this.iconResource = iconResource;
        return this;
    }

    @Override
    public ColorResource getBackgroundColor() {
        return bgColorResource;
    }

    @Override
    public IModule setBackgroundColor(ColorResource bgColorResource) {
        this.bgColorResource = bgColorResource;
        return this;
    }

    @Override
    public ColorResource getForegroundColor() {
        return fgColorResource;
    }

    @Override
    public IModule setForegroundColor(ColorResource fgColorResource) {
        this.fgColorResource = fgColorResource;
        return this;
    }
}
