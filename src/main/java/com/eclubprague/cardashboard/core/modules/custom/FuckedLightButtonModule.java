package com.eclubprague.cardashboard.core.modules.custom;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.JsonReader;
import android.util.Log;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractHttpModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by michael on 11/20/15.
 */
public class FuckedLightButtonModule extends AbstractHttpModule {

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_http_light_button);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_exit_to_app_black_24dp);


    public FuckedLightButtonModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE);
    }

    @Override
    public void handleClickEvent(IModuleContext context) {
        Log.d("FUCKED", "onClik");
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

            RequestBody requestBodyTurnOn = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "action=GPIO+ALL&value=10");
            RequestBody requestBodyTurnOff = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "action=GPIO+ALL&value=11");

            String url = "http://zettor.sin.cvut.cz:1337/servers/00000001/devices/4DC37";
            try {
                Request request = new Request.Builder().url(url).get().build();
                Response response = client.newCall(request).execute();

                if (response.code() != 200) {
                    throw new IOException();
                }

                JSONObject responseJson = new JSONObject(response.body().string());
                String status = responseJson.getJSONObject("properties").getString("gpio1");
                Request actionRequest = null;
                if (status.equals("1")) {
                    actionRequest = new Request.Builder().url(url).post(requestBodyTurnOn).build();
                } else {
                    actionRequest = new Request.Builder().url(url).post(requestBodyTurnOff).build();
                }


                response = client.newCall(actionRequest).execute();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
