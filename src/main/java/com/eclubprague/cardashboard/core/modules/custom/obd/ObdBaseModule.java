package com.eclubprague.cardashboard.core.modules.custom.obd;

import android.support.annotation.NonNull;
import android.util.Log;

import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.obd.OBDGatewayService;
import com.eclubprague.cardashboard.core.obd.ObdCommandJob;
import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.engine.EngineRPMObdCommand;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Michael on 11.01.2016.
 */
abstract public class ObdBaseModule<T extends ObdCommand> extends AbstractTimedUpdateDisplayModule<GlobalExtraFastUpdateEvent> {

    private final Class<T> clazz;
    private final CommandCreator commandCreator;

    public ObdBaseModule( @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource ) {
        super( titleResource, iconResource, unitResource );
        clazz = ( (Class) ( (ParameterizedType) getClass().getGenericSuperclass() ).getActualTypeArguments()[0] );
        commandCreator = new CommandCreator() {
            @Override
            public ObdCommand createCommand() {
                try {
                    return clazz.newInstance();
                } catch ( InstantiationException e ) {
                } catch ( IllegalAccessException e ) {
                }
                throw new AssertionError( "Unknown class: " + clazz.getName() );
            }
        };
    }

    @Override
    public String getUpdatedValue() {
        OBDGatewayService gatewayService = (OBDGatewayService) OBDGatewayService.getInstance();
        if ( gatewayService != null && gatewayService.isRunning() ) {
            gatewayService.enqueue( new ObdCommandJob( commandCreator.createCommand() ) );
            ObdCommandJob results = gatewayService.getResult( clazz );
            if ( results != null && results.getCommand().getResult() != null ) {
                String value = results.getCommand().getCalculatedResult();
                setLastValue( value );
            }
        }
        return getLastValue();
    }

    private interface CommandCreator<T> {
        ObdCommand createCommand();
    }
}
