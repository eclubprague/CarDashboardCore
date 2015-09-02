package com.eclubprague.cardashboard.core.obd;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class ObdGatewayTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    OBDGatewayService OBDGatewayService;

    public ObdGatewayTask(OBDGatewayService s) {
        OBDGatewayService = s;
    }

    @Override
    protected Result doInBackground(Params... params) {
        while (true) {
            try {
                if (OBDGatewayService.isQueueEmpty()) {
                    Thread.sleep(100);
                    Log.d("Task", "sleeping");
                } else {
                    Log.d("Task", "still running");
                    ObdCommandJob job = OBDGatewayService.dequeue();
                    if (job.getState().equals(ObdCommandJobState.NEW)) {
                        job.setState(ObdCommandJobState.RUNNING);
                        try {
                            job.getCommand().run(
                                    OBDGatewayService.getSock().getInputStream(),
                                    OBDGatewayService.getSock().getOutputStream());
                            OBDGatewayService.putResult(job.getCommand().getClass(), job);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.e("Task", job.getCommand().getName());
                        }

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
