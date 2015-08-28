package com.eclubprague.cardashboard.core.obd;

import android.app.Service;
import android.os.AsyncTask;
import android.util.Log;

public class ObdGatewayTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    DummyGatewayService dummyGatewayService;

    public ObdGatewayTask(DummyGatewayService s) {
        dummyGatewayService = s;
    }

    @Override
    protected Result doInBackground(Params... params) {
        while(true){
            try {
                Thread.sleep(1000);
                Log.d("OGT","sleeping");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
