package com.eclubprague.cardashboard.core.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.eclubprague.cardashboard.core.application.GlobalDataProvider;

import java.util.UUID;

/**
 * Created by Lukáš on 31.12.2015.
 */
public class DeviceInfoUtils {

    public static String getDeviceID(){
        final TelephonyManager tm = (TelephonyManager) GlobalDataProvider.getInstance().getActivity().getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(GlobalDataProvider.getInstance().getActivity().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();

    }
}
