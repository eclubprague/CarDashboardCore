package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;

/**
 * Created by Michael on 13.08.2015.
 * <p/>
 * Module view containing Active (Display) Module .
 */
public class ModuleActiveView extends ModuleView {
    private TextView valueView;

    public ModuleActiveView(Context context) {
        super(context);
    }

    public ModuleActiveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ModuleActiveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ModuleActiveView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ModuleActiveView setValue(String value) {
        if (valueView == null) {
            valueView = (TextView) findViewById(R.id.module_value);
        }
        valueView.setText(value);
        return this;
    }
}
