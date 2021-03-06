package com.eclubprague.cardashboard.core.modules.custom;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.application.GlobalDataProvider;

import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;

public class CompassModule extends AbstractTimedUpdateDisplayModule<GlobalExtraFastUpdateEvent> {
    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_compass_title);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_map_black_24dp);
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_compass_units);
    private static final double NORTH = 0;
    private static final double NORTH_WEST = -Math.PI / 4;
    private static final double WEST = -Math.PI / 2;
    private static final double SOUTH_WEST = -3 * Math.PI / 4;
    private static final double SOUTH = -Math.PI;
    private static final double SOUTH_EAST = 3 * Math.PI / 4;
    private static final double EAST = Math.PI / 2;
    private static final double NORTH_EAST = Math.PI / 4;
    private static final double TOLERANCE = Math.PI / 8;
    private String dir;

    public CompassModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE );
        init();
    }


    private SensorManager mSensorManager;
    Float azimut;

    private void init() {
//        FastEventBus.getInstance().register(this, GlobalExtraFastUpdateEvent.class);
    }

    @Override
    public void onPause(IModuleContext moduleContext) {
        super.onPause(moduleContext);
        getSensorManager().unregisterListener(orientListener);
    }

    @Override
    public void onResume(IModuleContext moduleContext) {
        super.onResume(moduleContext);
        getSensorManager().registerListener(orientListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI);
    }

    private SensorEventListener orientListener = new SensorEventListener() {

        float[] mGravity;
        float[] mGeomagnetic;

        public void onSensorChanged(SensorEvent event) {

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                mGravity = event.values;

            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                mGeomagnetic = event.values;

            if (mGravity != null && mGeomagnetic != null) {
                float R[] = new float[9];
                float I[] = new float[9];

                if (SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic)) {
                    float orientation[] = new float[3];
                    SensorManager.getOrientation(R, orientation);
                    azimut = orientation[0];
                    if (azimut > NORTH - TOLERANCE && azimut < NORTH + TOLERANCE) {
                        dir = "N";
                    } else if (azimut > NORTH_WEST - TOLERANCE && azimut < NORTH_WEST + TOLERANCE) {
                        dir = "NW";
                    } else if (azimut > WEST - TOLERANCE && azimut < WEST + TOLERANCE) {
                        dir = "W";
                    } else if (azimut > SOUTH_WEST - TOLERANCE && azimut < SOUTH_WEST + TOLERANCE) {
                        dir = "SW";
                    } else if (azimut > SOUTH - TOLERANCE && azimut < SOUTH + TOLERANCE) {
                        dir = "S";
                    } else if (azimut > SOUTH_EAST - TOLERANCE && azimut < SOUTH_EAST + TOLERANCE) {
                        dir = "SE";
                    } else if (azimut > EAST - TOLERANCE && azimut < EAST + TOLERANCE) {
                        dir = "E";
                    } else if (azimut > NORTH_EAST - TOLERANCE && azimut < NORTH_EAST + TOLERANCE) {
                        dir = "NE";
                    }
                    currentValue = dir;
                }
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    private String currentValue = "-";

    @Override
    public String getUpdatedValue() {
        if(true){
            return "SE";
        }
        getSensorManager();
        return currentValue;
    }

    private SensorManager getSensorManager() {
        Context context = GlobalDataProvider.getInstance().getContext();
        if (mSensorManager == null && context != null) {
            mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
                mSensorManager.registerListener(orientListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
                mSensorManager.registerListener(orientListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        return mSensorManager;
    }
}
