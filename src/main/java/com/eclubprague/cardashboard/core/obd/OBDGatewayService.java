package com.eclubprague.cardashboard.core.obd;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.eclubprague.cardashboard.core.application.GlobalDataProvider;
import com.eclubprague.cardashboard.core.preferences.SettingsFragment;
import com.github.pires.obd.commands.protocol.EchoOffObdCommand;
import com.github.pires.obd.commands.protocol.LineFeedOffObdCommand;
import com.github.pires.obd.commands.protocol.ObdResetCommand;
import com.github.pires.obd.commands.protocol.SelectProtocolObdCommand;
import com.github.pires.obd.commands.protocol.TimeoutObdCommand;
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureObdCommand;
import com.github.pires.obd.enums.ObdProtocols;
import com.github.pires.obd.exceptions.UnsupportedCommandException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class OBDGatewayService extends IntentService {
    private static final String TAG = OBDGatewayService.class.getName();
    protected BlockingQueue<ObdCommandJob> jobsQueue = new LinkedBlockingDeque<>();
    Map<Class, ObdCommandJob> executedCommands = new HashMap<>();
    //AsyncTask asyncTask = new ObdGatewayTask(this);
    private static IntentService instance;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(GlobalDataProvider.getInstance().getContext());

    private BluetoothDevice dev = null;
    private BluetoothSocket sock;
    private BluetoothSocket sockFallback = null;
    private boolean isRunning = false;
    private Long queueCounter = 0L;


    public BluetoothSocket getSock() {
        return sock;
    }

    public static IntentService getInstance() {
        return instance;
    }

    public OBDGatewayService(String name) {
        super("OBDGatewayService");
    }

    public OBDGatewayService() {
        super("OBDGatewayService");
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
        instance = this;
        t.start();
        try {
            startService();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void enqueue(ObdCommandJob job) {
        queueCounter++;
        Log.d(TAG, "Adding job[" + queueCounter + "] to queue..");

        job.setId(queueCounter);
        try {
            jobsQueue.put(job);
            Log.d(TAG, "Job queued successfully.");
        } catch (InterruptedException e) {
            job.setState(ObdCommandJobState.QUEUE_ERROR);
            Log.e(TAG, "Failed to queue job.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //t.interrupt();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
    }

    public void startService() throws IOException {
        Log.d(TAG, "Starting service..");

        // get the remote Bluetooth device
        final String remoteDevice = prefs.getString(SettingsFragment.BLUETOOTH_LIST_KEY, null);
        if (remoteDevice == null || "".equals(remoteDevice)) {

            // log error
            Log.e(TAG, "No Bluetooth device has been selected.");

            // TODO kill this service gracefully
            stopService();
            throw new IOException();
        } else {

            final BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
            dev = btAdapter.getRemoteDevice(remoteDevice);
            Log.d(TAG, "Stopping Bluetooth discovery.");
            btAdapter.cancelDiscovery();

            try {
                startObdConnection();
            } catch (Exception e) {
                Log.e(
                        TAG,
                        "There was an error while establishing connection. -> "
                                + e.getMessage()
                );

                // in case of failure, stop this service.
                stopService();
                throw new IOException();
            }
        }
    }

    public void stopService() {
        Log.d(TAG, "Stopping service..");
        jobsQueue.removeAll(jobsQueue);
        isRunning = false;

        if (sock != null)
            try {
                sock.close();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        stopSelf();
    }

    private void startObdConnection() throws IOException {
        Log.d(TAG, "Starting OBD connection..");
        isRunning = true;
        try {
            // Instantiate a BluetoothSocket for the remote device and connect it.
            sock = dev.createRfcommSocketToServiceRecord(MY_UUID);
            sock.connect();
        } catch (Exception e1) {
            Log.e(TAG, "There was an error while establishing Bluetooth connection. Falling back..", e1);
            Class<?> clazz = sock.getRemoteDevice().getClass();
            Class<?>[] paramTypes = new Class<?>[]{Integer.TYPE};
            try {
                Method m = clazz.getMethod("createRfcommSocket", paramTypes);
                Object[] params = new Object[]{Integer.valueOf(1)};
                sockFallback = (BluetoothSocket) m.invoke(sock.getRemoteDevice(), params);
                sockFallback.connect();
                sock = sockFallback;
            } catch (Exception e2) {
                Log.e(TAG, "Couldn't fallback while establishing Bluetooth connection. Stopping app..", e2);
                stopService();
                throw new IOException();
            }
        }

        // Let's configure the connection.
        Log.d(TAG, "Queueing jobs for connection configuration..");
        enqueue(new ObdCommandJob(new ObdResetCommand()));
        enqueue(new ObdCommandJob(new EchoOffObdCommand()));

    /*
     * Will send second-time based on tests.
     *
     * TODO this can be done w/o having to queue jobs by just issuing
     * command.run(), command.getResult() and validate the result.
     */
        enqueue(new ObdCommandJob(new EchoOffObdCommand()));
        enqueue(new ObdCommandJob(new LineFeedOffObdCommand()));
        enqueue(new ObdCommandJob(new TimeoutObdCommand(62)));

        // Get protocol from preferences
        //final String protocol = prefs.getString(SettingsFragment.PROTOCOLS_LIST_KEY, "AUTO");
        final String protocol = "AUTO";
        enqueue(new ObdCommandJob(new SelectProtocolObdCommand(ObdProtocols.valueOf(protocol))));

        // Job for returning dummy data
        enqueue(new ObdCommandJob(new AmbientAirTemperatureObdCommand()));


        Log.d(TAG, "Initialization jobs queued.");


    }


    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                executeQueue();
            } catch (InterruptedException e) {
                t.interrupt();
            }
        }
    });


    protected void executeQueue() throws InterruptedException {
        Log.d(TAG, "Executing queue..");
        while (!Thread.currentThread().isInterrupted()) {
            ObdCommandJob job = null;
            try {
                job = jobsQueue.take();

                // log job
                Log.d(TAG, "Taking job[" + job.getId() + "] from queue..");

                if (job.getState().equals(ObdCommandJobState.NEW)) {
                    Log.d(TAG, "Job state is NEW. Run it..");
                    job.setState(ObdCommandJobState.RUNNING);
                    job.getCommand().run(sock.getInputStream(), sock.getOutputStream());
                } else
                    // log not new job
                    Log.e(TAG,
                            "Job state was not new, so it shouldn't be in queue. BUG ALERT!");
            } catch (InterruptedException i) {
                Thread.currentThread().interrupt();
            } catch (UnsupportedCommandException u) {
                if (job != null) {
                    job.setState(ObdCommandJobState.NOT_SUPPORTED);
                }
                Log.d(TAG, "Command not supported. -> " + u.getMessage());
            } catch (Exception e) {
                if (job != null) {
                    job.setState(ObdCommandJobState.EXECUTION_ERROR);
                }
                Log.e(TAG, "Failed to run command. -> " + e.getMessage());
            }

            if (job != null) {
                putResult(job.getCommand().getClass(), job);
            }
        }
    }


}
