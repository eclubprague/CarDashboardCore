package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;

/**
 * Created by Michael on 09.09.2015.
 */
public class TextIconListItemView extends TextListItemView {
    private ImageView iconView;

    public TextIconListItemView(Context context) {
        super(context);
    }

    public TextIconListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextIconListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextIconListItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TextIconListItemView setIcon(IconResource iconResource) {
        if (iconView == null) {
            iconView = (ImageView) findViewById(R.id.item_icon);
        }
        iconResource.setInView(iconView);
        return this;
    }
}
