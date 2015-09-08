package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;

/**
 * Created by Michael on 13.08.2015.
 */
public class ApplistGroupView extends ApplistView {
    private ImageView rightIconView;

    public ApplistGroupView(Context context) {
        super(context);
    }

    public ApplistGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ApplistGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ApplistGroupView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ApplistView setRightIcon(IconResource iconResource) {
        if (rightIconView == null) {
            rightIconView = (ImageView) findViewById(R.id.applist_item_right_icon);
        }
        iconResource.setInView(rightIconView);
        return this;
    }
}
