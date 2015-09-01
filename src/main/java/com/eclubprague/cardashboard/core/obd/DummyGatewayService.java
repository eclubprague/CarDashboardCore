package com.eclubprague.cardashboard.core.obd;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.eclubprague.cardashboard.core.preferences.SettingsActivity;
import com.eclubprague.cardashboard.core.preferences.SettingsFragment;
import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.engine.EngineRPMObdCommand;
import com.github.pires.obd.commands.protocol.EchoOffObdCommand;
import com.github.pires.obd.commands.protocol.LineFeedOffObdCommand;
import com.github.pires.obd.commands.protocol.ObdResetCommand;
import com.github.pires.obd.commands.protocol.SelectProtocolObdCommand;
import com.github.pires.obd.commands.protocol.TimeoutObdCommand;
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureObdCommand;
import com.github.pires.obd.enums.ObdProtocols;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

public class DummyGatewayService extends IntentService {
    private static final String TAG = DummyGatewayService.class.getName();
    protected Queue<ObdCommandJob> jobsQueue = new LinkedList<>();
    Map<Class, ObdCommandJob> executedCommands = new HashMap<>();
    AsyncTask asyncTask = new ObdGatewayTask(this);

    public DummyGatewayService(String name) {
        super("DummyGatewayService");

    }

    public DummyGatewayService() {
        super("DummyGatewayService");
    }

    public synchronized void enqueue(ObdCommandJob job) {
        executedCommands.put(job.getClass(), job);
        jobsQueue.add(job);
    }

    protected synchronized void putResult(Class commandClass, ObdCommandJob commandInstance) {
        executedCommands.put(commandClass, commandInstance);
    }

    public synchronized ObdCommandJob getResult(Class commandClass) {
        return executedCommands.get(commandClass);
    }

    protected synchronized ObdCommandJob dequeue() {
        return jobsQueue.poll();
    }

    protected synchronized boolean isQueueEmpty() {
        return jobsQueue.isEmpty();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        asyncTask.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
    }


}
