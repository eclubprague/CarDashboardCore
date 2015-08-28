package com.eclubprague.cardashboard.core.obd;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.eclubprague.cardashboard.core.application.GlobalApplication;
import com.github.pires.obd.commands.protocol.EchoOffObdCommand;

import java.util.LinkedList;
import java.util.Queue;

public class DummyGatewayService extends IntentService {
    private static final String TAG = DummyGatewayService.class.getName();
    protected Queue<ObdCommandJob> jobsQueue = new LinkedList<>();
    AsyncTask asyncTask = new ObdGatewayTask(this);


    public DummyGatewayService(String name) {
        super("DummyGatewayService");
        GlobalApplication.getInstance().setObdService(this);
    }

    public DummyGatewayService() {
        super("DummyGatewayService");
        GlobalApplication.getInstance().setObdService(this);
    }

    protected synchronized void enqueue(ObdCommandJob job) {
        jobsQueue.add(job);
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
