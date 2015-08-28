package com.eclubprague.cardashboard.core.obd;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class AbstractGatewayService extends Service {
    public static final int NOTIFICATION_ID = 1;
    private final IBinder binder = new AbstractGatewayServiceBinder();
    protected NotificationManager notificationManager;
    protected boolean isRunning = false;
    protected BlockingQueue<ObdCommandJob> jobsQueue = new LinkedBlockingDeque<>();
    protected Long queueCounter = 0L;
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        t.start();
    }

    @Override
    public void onDestroy() {
        notificationManager.cancel(NOTIFICATION_ID);
        t.interrupt();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean queueEmpty() {
        return jobsQueue.isEmpty();
    }

    public void queueJob(ObdCommandJob job) {
        queueCounter++;
        job.setId(queueCounter);
        try {
            jobsQueue.put(job);
        } catch (InterruptedException e) {
            job.setState(ObdCommandJobState.QUEUE_ERROR);
        }
    }


    abstract protected void executeQueue() throws InterruptedException;

    abstract public void startService() throws IOException;

    abstract public void stopService();

    public class AbstractGatewayServiceBinder extends Binder {
        public AbstractGatewayService getService() {
            return AbstractGatewayService.this;
        }
    }
}
