package com.eclubprague.cardashboard.core.utils;

import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.custom.ClockModule;
import com.eclubprague.cardashboard.core.modules.custom.ClockSecondsModule;
import com.eclubprague.cardashboard.core.modules.custom.CompassModule;
import com.eclubprague.cardashboard.core.modules.custom.FolderModule;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Michael on 11.01.2016.
 */
public class ModuleUtilsTest {

    @Test
    public void testForEach() throws Exception {
        IParentModule parent = new FolderModule();
        IParentModule subparent = new FolderModule();
        IParentModule subsubparent = new FolderModule();
        IParentModule subsubsubparent = new FolderModule();
        List<IModule> clockModules = new ArrayList<>();
        for ( int i = 0; i < 20; i++ ) {
            clockModules.add( new ClockModule() );
        }
        int counter = 0;
        parent.addSubmodules( subparent, clockModules.get( counter++ ), clockModules.get( counter++ ) );
        subparent.addSubmodules( clockModules.get( counter++ ), clockModules.get( counter++ ), subsubparent, clockModules.get( counter++ ) );
        subsubparent.addSubmodules( subsubsubparent );
        ModuleUtils.forEach( parent, new ModuleUtils.Action() {
            @Override
            public IModule performAction( IModule module ) {
                return module.setTitle( StringResource.fromString( "testForEach" ) );
            }
        }, ClockModule.class );
        for ( int i = 0; i < counter; i++ ) {
            assertEquals( "testForEach", clockModules.get( i ).getTitle().getString(null) );
        }
    }

    @Test
    public void testForEachDeepCopy() throws Exception {
        IParentModule parent = new FolderModule();
        IParentModule subparent = new FolderModule();
        IParentModule subsubparent = new FolderModule();
        IParentModule subsubsubparent = new FolderModule();
        List<IModule> clockModules = new ArrayList<>();
        for ( int i = 0; i < 20; i++ ) {
            clockModules.add( new ClockModule() );
        }
        int counter = 0;
        parent.addSubmodules( subparent, clockModules.get( counter++ ), clockModules.get( counter++ ) );
        subparent.addSubmodules( clockModules.get( counter++ ), clockModules.get( counter++ ), subsubparent, clockModules.get( counter++ ) );
        subsubparent.addSubmodules( subsubsubparent );
        ModuleUtils.forEach( parent, new ModuleUtils.Action() {
            @Override
            public IModule performAction( IModule module ) {
                return module.setTitle( StringResource.fromString( "testForEach" ) );
            }
        }, ClockModule.class );
        for ( int i = 0; i < counter; i++ ) {
            assertEquals( "testForEach", clockModules.get( i ).getTitle().getString(null) );
        }
        for(IModule m : parent.getSubmodules()){
            if(m instanceof IParentModule){
//                assertNotEquals( subparent, m ); // ERROR, FIX
                break;
            }
        }
    }
}