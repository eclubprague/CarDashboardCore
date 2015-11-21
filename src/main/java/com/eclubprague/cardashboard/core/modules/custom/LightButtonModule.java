package com.eclubprague.cardashboard.core.modules.custom;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
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

import java.io.IOException;

/**
 * Created by michael on 11/20/15.
 */
public class LightButtonModule extends AbstractHttpModule {

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_http_light_button );
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_exit_to_app_black_24dp );

    private static final MediaType MEDIA_TYPE = MediaType.parse( "application/x-www-form-urlencoded" );
    private static final String URL = "http://zettor.sin.cvut.cz:1337/servers/00000000/devices/4ec556ea-c638-496e-aa5d-b4751a08ca66";
    private static final String BODY = "action=turn-on";

    public LightButtonModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE);
    }


    @Override
    public void handleClickEvent( IModuleContext context ) {
        new HttpRequestTask().execute( new HttpData( MEDIA_TYPE, URL, null ) );
    }

    private class HttpRequestTask extends AsyncTask<AbstractHttpModule.HttpData, Void, Void> {

        @Override
        protected Void doInBackground( AbstractHttpModule.HttpData... params ) {
            AbstractHttpModule.HttpData httpData = params[0];
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create( httpData.getMediaType(), "action=turn-on" );
            Request request = new Request.Builder()
                    .url( httpData.getUrl() )
                    .post( requestBody )
                    .build();
            try {
                Response response = client.newCall( request ).execute();
                //Log.d("AbstractHttpModule", "response = " + response);
                if ( response.code() != 200 ) {
                    requestBody = RequestBody.create( httpData.getMediaType(), "action=turn-off" );
                    request = new Request.Builder()
                            .url( httpData.getUrl() )
                            .post( requestBody )
                            .build();
                    response = client.newCall( request ).execute();
                    if ( response.code() != 200 ) {
                        Log.d( "AbstractHttpModule", "response = " + response );
                    }
                }
            } catch ( IOException e ) {
                Log.d( "AbstractHttpModule", e.toString() );
                e.printStackTrace();
            }
            return null;
        }
    }
}
