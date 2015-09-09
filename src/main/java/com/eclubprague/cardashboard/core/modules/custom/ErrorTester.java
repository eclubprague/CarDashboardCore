package com.eclubprague.cardashboard.core.modules.custom;

import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.utils.ErrorReporter;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;

/**
 * Created by Michael on 08.09.2015.
 */
public class ErrorTester extends AbstractSimpleModule {
    public static final StringResource TITLE_RESOURCE = StringResource.fromString("ErrorTester");
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_error_black_24dp);


    public ErrorTester() {
        super(ModuleEnum.TEST, TITLE_RESOURCE, ICON_RESOURCE);
    }

    @Override
    public void onClickEvent(IModuleContext context) {
//        ErrorReporter.reportApplicationError(context,
//                StringResource.fromString("Testing short message"),
//                StringResource.fromString("Testing long long long long message with some Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
//                StringResource.fromString("Turn it off and on again")
//        );
        ErrorReporter.reportApplicationNonCriticalError(context,
                StringResource.fromString("Testing short message"),
                StringResource.fromString("Testing long long long long message with some Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
                StringResource.fromString("Turn it off and on again")
        );
    }

    @Override
    public ModuleView createNewView(IModuleContext moduleContext, ViewGroup parent) {
        return ModuleViewFactory.createPassive(moduleContext, parent, this, getIcon(), getTitle());
    }

    @Override
    public ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(moduleContext, holderResourceId, holderParent, this, getIcon(), getTitle());
    }
}
