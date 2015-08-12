package com.eclubprague.cardashboard.core.modules;

import android.os.AsyncTask;
import android.util.Log;

import com.eclubprague.cardashboard.core.modules.base.AbstractDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.util.Random;

import de.greenrobot.event.EventBus;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Simple display module implementation for testing.
 */
public class TestDisplayModule extends AbstractDisplayModule {
    private UpdateTask task;


    public TestDisplayModule(IParentModule parent, StringResource titleResource, IconResource iconResource, StringResource unitResource) {
        super(parent, titleResource, iconResource, unitResource);
    }

    @Override
    public void onClickEvent(IModuleContext moduleContext) {
        super.onClickEvent(moduleContext);
//        new AlertDialog.Builder(context).setMessage("SHORT CLICK").create().show();
        try {
            Integer i = Integer.parseInt(getValue());
            updateValue(Integer.toString(i + 100));
        } catch (NumberFormatException ex) {
            // no value, do nothing then
        }
        Log.d("TestDisplayModule", "clicked");
    }

    private class UpdateTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Random rnd = new Random();
            int time = 0;
            int speed = 50;
            while (time < 600) {
                time++;
                speed += rnd.nextInt(10) - 4;
                EventBus.getDefault().post(new ModuleUpdateEvent(Integer.toString(speed)));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            updateValue(Integer.toString(values[0]));
        }
    }
}
