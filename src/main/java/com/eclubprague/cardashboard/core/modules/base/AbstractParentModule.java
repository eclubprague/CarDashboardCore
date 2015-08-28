package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.predefined.EmptyModule;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Base implementation of submenu module.
 * Submenu module leads to "submenu", meaning it launches another menu with a different set of modules.
 */
abstract public class AbstractParentModule extends AbstractSimpleModule implements IParentModule {
    private final List<IModule> submodules = new ArrayList<>();

    public AbstractParentModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource) {
        super(titleResource, iconResource);
    }

    public AbstractParentModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(titleResource, iconResource, bgColorResource, fgColorResource);
    }

    @Override
    public IParentModule addSubmodules(IModule... modules) {
        submodules.addAll(Arrays.asList(modules));
        return this;
    }

    @Override
    public IParentModule addSubmodules(List<IModule> modules) {
        submodules.addAll(modules);
        return this;
    }

    @Override
    public IParentModule removeSubmodule(IModule module) {
        submodules.remove(module);
        return this;
    }

    @Override
    public List<IModule> getSubmodules() {
        return submodules;
    }

    @Override
    public IParentModule removeAllSubmodules() {
        submodules.clear();
        return this;
    }

    @Override
    public IParentModule removeTailEmptyModules() {
        for (int i = submodules.size() - 1; i >= 0; i--) {
            if (submodules.get(i) instanceof EmptyModule) {
                submodules.remove(i);
            } else {
                break;
            }
        }
        return this;
    }

    @Override
    public ModuleView createNewView(IModuleContext moduleContext, ViewGroup parent) {
        return ModuleViewFactory.createPassive(moduleContext, parent, this, getIcon(), getTitle());
    }

    @Override
    public ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(moduleContext, holderResourceId, holderParent, this, getIcon(), getTitle());
    }

    @Override
    public void onClickEvent(IModuleContext moduleContext) {
        super.onClickEvent(moduleContext);
//        Log.d("SubmenuModule", "setting modules: " + getSubmodules().size());
        moduleContext.goToSubmodules(this);
    }

    @Override
    public String toString() {
        return "AbstractParentModule{" +
                super.toString() + ", " +
                "submodules=" + submodules.size() +
                '}';
    }

    @Override
    public IModule onCopy(IModule newInstance) throws ReflectiveOperationException {
        IParentModule newParent = (IParentModule) newInstance;
        newParent.addSubmodules(getSubmodules());
        return newParent;
    }

    @Override
    public IModule onDeepCopy(IModule newInstance) throws ReflectiveOperationException {
        IParentModule newParent = (IParentModule) newInstance;
        for (IModule m : getSubmodules()) {
//            if (m instanceof IParentModule) {
//                IParentModule parentModule = (IParentModule) m;
//                newParent.addSubmodules(parentModule.copyDeep());
//            } else {
            newParent.addSubmodules(m.copyDeep());
//            }
        }
        return newParent;
    }
}
