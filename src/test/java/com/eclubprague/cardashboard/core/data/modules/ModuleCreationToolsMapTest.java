package com.eclubprague.cardashboard.core.data.modules;

import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.custom.FolderModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Michael on 11.01.2016.
 */
public class ModuleCreationToolsMapTest {

    ModuleCreationToolsMap map;

    @Before
    public void setUp() throws Exception {
        map = ModuleCreationToolsMap.getInstance();
    }

    @Test
    public void testRegister() throws Exception {
        boolean thrown = false;
        try {
            assertNull( map.getLoader( Module.class ) );
            assertNull( map.getCreator( Module.class ) );
        } catch(IllegalArgumentException ex){
            thrown = true;
        }
        assertTrue( thrown );
        map.register( Module.class, ModuleLoader.PARENT, ModuleCreator.DEFAULT );
        assertNotNull( map.getLoader( Module.class ) );
        assertNotNull( map.getCreator( Module.class ) );
    }

    @Test
    public void testGetLoader() throws Exception {
        // tested in testRegister
    }

    @Test
    public void testGetCreator() throws Exception {
        // tested in testRegister
    }

    public class Module extends FolderModule {

    }
}