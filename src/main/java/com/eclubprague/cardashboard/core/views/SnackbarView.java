package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.StringResource;

/**
 * Created by Michael on 09.09.2015.
 */
public class SnackbarView {

    public static final long DURATION_SHORT = 1500;
    public static final long DURATION_LONG = 3000;

    private Context context;
    private StringResource messageTextResource;
    private StringResource buttonTextResource;
    private View.OnClickListener buttonClickListener;

    private SnackbarView() {

    }

    public static SnackbarView make(Context context) {
        SnackbarView snackbarView = new SnackbarView();
        snackbarView.context = context;
        return snackbarView;
    }

    public SnackbarView setText(StringResource messageTextResource) {
        this.messageTextResource = messageTextResource;
        return this;
    }

    public SnackbarView setButton(StringResource buttonTextResource, View.OnClickListener buttonClickListener) {
        this.buttonTextResource = buttonTextResource;
        this.buttonClickListener = buttonClickListener;
        return this;
    }

    public void show(final ViewGroup container) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.snackbar_text, container);
        TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
        messageTextResource.setInView(textView);
//        container.addView(view);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                container.removeView(view);
            }
        }, DURATION_LONG);
    }
}
