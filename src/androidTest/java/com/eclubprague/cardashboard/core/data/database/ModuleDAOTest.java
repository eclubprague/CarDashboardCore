package com.eclubprague.cardashboard.core.data.database;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.content.Context;
import android.content.Intent;
import android.test.InstrumentationTestCase;

import com.eclubprague.cardashboard.core.modules.base.IActivityStateChangeListener;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.ModuleEvent;
import com.eclubprague.cardashboard.core.model.resources.StringResource;

import java.lang.Override;

import static org.junit.Assert.*;

/**
 * Created by Michael on 20.08.2015.
 */
public class ModuleDAOTest extends InstrumentationTestCase {

    IModuleContext moduleContext;
    ModuleDAO moduleDAO;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        moduleContext = new IModuleContext() {
            @Override
            public void goToSubmodules(IParentModule parentModule) {

            }

            @Override
            public void goBackFromSubmodules(IParentModule previousParentModule) {

            }

            @Override
            public void toggleQuickMenu(IModule module, boolean activate) {

            }

            @Override
            public void turnQuickMenusOff() {

            }

            @Override
            public void launchIntent(Intent intent, StringResource errorMessage) {

            }

            @Override
            public void swapModules(IModule oldModule, IModule newModule, boolean animate) {

            }

            @Override
            public Context getContext() {
                return getInstrumentation().getContext();
            }

            @Override
            public void addListener(IActivityStateChangeListener listener) {

            }

            @Override
            public void removeListener(IActivityStateChangeListener listener) {

            }

            @Override
            public void onModuleEvent(IModule module, ModuleEvent event) {

            }
        };
        moduleDAO = new ModuleDAO(moduleContext);
    }

    @org.junit.Test
    public void testLoadParentModule() throws Exception {
        IParentModule parentModule = moduleDAO.loadParentModule();
        assertEquals("", parentModule.toString());
    }

    @org.junit.Test
    public void testReadParentModule() throws Exception {

    }

    @org.junit.Test
    public void testSaveParentModule() throws Exception {

    }

    @org.junit.Test
    public void testWriteParentModule() throws Exception {

    }
}