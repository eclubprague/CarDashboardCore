package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.StringResource;

/**
 * Created by Michael on 13.08.2015.
 * <p/>
 * Module view containing Active (Display) Module .
 */
public class ModuleActiveView extends ModuleView {
    private TextView valueView;
    private TextView unitView;

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

    public ModuleActiveView setUnit(@NonNull StringResource unitResource){
        if(unitView == null){
            unitView = (TextView) findViewById(R.id.module_unit);
        }
        unitResource.setInView(unitView);
        return this;
    }
}
