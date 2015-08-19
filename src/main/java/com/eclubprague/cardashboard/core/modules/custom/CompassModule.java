package com.eclubprague.cardashboard.core.modules.custom;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.eventbus.FastEventBus;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.MainThreadReceiver;
import com.eclubprague.cardashboard.core.modules.base.AbstractDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.util.Arrays;

public class CompassModule extends AbstractDisplayModule implements MainThreadReceiver<GlobalExtraFastUpdateEvent> {
    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_compass_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_map_black_24dp);
    private static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_compass_units);
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
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        init();
    }

    public CompassModule(@NonNull IModuleContext moduleContext) {
        super(moduleContext, TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        init();
    }

    public CompassModule(@NonNull IModuleContext moduleContext, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(moduleContext, TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);
        init();
    }

    private SensorManager mSensorManager;
    Float azimut;

    private void init() {
        FastEventBus.getInstance().register(this, GlobalExtraFastUpdateEvent.class);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(orientListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(orientListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI);
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

                    // orientation contains azimut, pitch and roll
                    float orientation[] = new float[3];
                    SensorManager.getOrientation(R, orientation);

                    azimut = orientation[0];

                    Log.d("azimut", "" + azimut);
                    Log.d("north", "" + NORTH);
                    Log.d("tolerance", "" + TOLERANCE);
                    Log.d("PI", "" + Math.PI);
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
    public void onEventMainThread(GlobalExtraFastUpdateEvent event) {
        if (mSensorManager == null && isInitialized()) {
            mSensorManager = (SensorManager) this.getModuleContext().getContext().getSystemService(Context.SENSOR_SERVICE);
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
                mSensorManager.registerListener(orientListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
                mSensorManager.registerListener(orientListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        updateValue(currentValue);
    }
}
