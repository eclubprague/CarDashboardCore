package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.R;

import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleView;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.params.HttpConnectionParams;

import java.io.IOException;

/**
 * Created by michael on 11/20/15.
 */
abstract public class AbstractHttpModule extends AbstractSimpleModule {

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_http_default);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_exit_to_app_black_24dp);

    public AbstractHttpModule() {
    }

    public AbstractHttpModule( @NonNull StringResource titleResource, @NonNull IconResource iconResource ) {
        super( titleResource, iconResource );
    }

    @Override
    public void onClickEvent(IModuleContext context) {
        super.onClickEvent(context);
        handleClickEvent(context);
    }

    abstract public void handleClickEvent(IModuleContext context);


    @Override
    protected ModuleView createNewView(IModuleContext context, ViewGroup parent) {
        return ModuleViewFactory.createPassive(context, parent, this, getIcon(), getTitle());
    }

    @Override
    protected ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext context, int holderResourceId, ViewGroup holderParent) {
        return ModuleViewFactory.createPassiveWithHolder(context, holderResourceId, holderParent, this, getIcon(), getTitle());
    }

    public static class HttpData {
        private final MediaType mediaType;
        private final String url;
        private final String body;

        public HttpData(MediaType mediaType, String url, String body) {
            this.mediaType = mediaType;
            this.url = url;
            this.body = body;
        }

        public MediaType getMediaType() {
            return mediaType;
        }

        public String getUrl() {
            return url;
        }

        public String getBody() {
            return body;
        }
    }
}
