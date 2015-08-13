package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 13.08.2015.
 */
public class ApplistView extends LinearLayout {
    private ImageView leftIconView;
    private ImageView rightIconView;
    private TextView textView;

    public ApplistView(Context context) {
        super(context);
    }

    public ApplistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ApplistView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ApplistView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ApplistView setText(StringResource textResource) {
        if (textView == null) {
            textView = (TextView) findViewById(R.id.applist_item_text);
        }
        textResource.setInView(textView);
        return this;
    }

    public ApplistView setLeftIcon(IconResource iconResource) {
        if (leftIconView == null) {
            leftIconView = (ImageView) findViewById(R.id.applist_item_left_icon);
        }
        iconResource.setInView(leftIconView);
        return this;
    }

    public ApplistView setRightIcon(IconResource iconResource) {
        if (rightIconView == null) {
            rightIconView = (ImageView) findViewById(R.id.applist_item_right_icon);
        }
        iconResource.setInView(rightIconView);
        return this;
    }
}
