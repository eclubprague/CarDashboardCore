package com.eclubprague.cardashboard.core.preferences;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.Toast;

import com.eclubprague.cardashboard.core.R;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    public static final String ENABLE_BT_KEY = "enable_bluetooth_preference";
    public static final String BLUETOOTH_LIST_KEY = "bluetooth_list_preference";
    public static final String LOGGING_ENABLED = "logging_enabled_preference";
    public static final String IMPERIAL_UNITS_KEY = "imperial_units_preference";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        ArrayList<CharSequence> pairedDeviceStrings = new ArrayList<>();
        ArrayList<CharSequence> vals = new ArrayList<>();
        ListPreference listBtDevices = (ListPreference) getPreferenceScreen()
                .findPreference(BLUETOOTH_LIST_KEY);

        final BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null) {
            listBtDevices
                    .setEntries(pairedDeviceStrings.toArray(new CharSequence[0]));
            listBtDevices.setEntryValues(vals.toArray(new CharSequence[0]));
            Toast.makeText(this.getActivity(), "This device does not support Bluetooth.", Toast.LENGTH_LONG).show();
            return;
        }

        final Activity thisActivity = this.getActivity();
        listBtDevices.setEntries(new CharSequence[1]);
        listBtDevices.setEntryValues(new CharSequence[1]);
        listBtDevices.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                if (mBtAdapter == null || !mBtAdapter.isEnabled()) {
                    Toast.makeText(thisActivity,
                            "This device does not support Bluetooth or it is disabled.",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }
        });


        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                pairedDeviceStrings.add(device.getName() + "\n" + device.getAddress());
                vals.add(device.getAddress());
            }
        }
        listBtDevices.setEntries(pairedDeviceStrings.toArray(new CharSequence[0]));
        listBtDevices.setEntryValues(vals.toArray(new CharSequence[0]));

        EditTextPreference myPref = (EditTextPreference) findPreference("logging_preference_upload_logs");
        myPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                new UploadLogs().execute();
                return false;

            }
        });


    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }


    private class UploadLogs extends AsyncTask<Void, Void, Void> {
        ProgressDialog pg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg = new ProgressDialog(getActivity());
            pg.setTitle("Sending Data");
            pg.setMessage("Please wait, data is sending");
            pg.setCancelable(false);
            pg.setIndeterminate(true);
            pg.show();


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pg.dismiss();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
