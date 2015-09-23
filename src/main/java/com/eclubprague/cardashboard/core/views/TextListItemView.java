package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.StringResource;

/**
 * Created by Michael on 09.09.2015.
 */
public class TextListItemView extends RelativeLayout {
    private TextView textView;

    public TextListItemView(Context context) {
        super(context);
    }

    public TextListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextListItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TextListItemView setText(StringResource textResource) {
        if (textView == null) {
            textView = (TextView) findViewById(R.id.item_text);
        }
        textResource.setInView(textView);
        return this;
    }
}
