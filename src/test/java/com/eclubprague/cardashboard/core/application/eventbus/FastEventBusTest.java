package com.eclubprague.cardashboard.core.application.eventbus;

import com.eclubprague.cardashboard.core.model.eventbus.interfaces.Event;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.EventReceiver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Michael on 11.01.2016.
 */
public class FastEventBusTest implements EventReceiver<FastEventBusTest.TestEvent> {
    private FastEventBus eventBus;
    private boolean eventReceived;

    @Before
    public void setUp() throws Exception {
        eventBus = FastEventBus.getInstance();
        eventReceived = false;
    }

    @Test
    public void testRegister() throws Exception {
        eventReceived = false;
        eventBus.register( this, TestEvent.class );
        eventBus.post( new TestEvent() );
        assertTrue( eventReceived );
    }

    @Test
    public void testUnregister() throws Exception {
        eventReceived = false;
        eventBus.register( this, TestEvent.class );
        eventBus.post( new TestEvent() );
        assertTrue( eventReceived );
        eventReceived = false;
        eventBus.unregister( this, TestEvent.class );
        eventBus.post( new TestEvent() );
        assertFalse( eventReceived );
    }

    @Test
    public void testPost() throws Exception {
        eventReceived = false;
        eventBus.register( this, TestEvent.class );
        eventBus.post( new TestEvent() );
        assertTrue( eventReceived );
    }

    @Override
    public void onEventReceived( TestEvent event ) {
        eventReceived = true;
    }

    public class TestEvent implements Event {

    }
}