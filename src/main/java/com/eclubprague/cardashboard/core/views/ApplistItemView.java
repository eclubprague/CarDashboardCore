package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.eclubprague.cardashboard.core.R;

/**
 * Created by Michael on 13.08.2015.
 */
public class ApplistItemView extends ApplistView {
    private OnCheckListener onCheckListener;
    private CheckBox rightCheckBox;

    public ApplistItemView(Context context) {
        super(context);
    }

    public ApplistItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ApplistItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ApplistItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private CheckBox getCheckBox() {
        if (rightCheckBox == null) {
            rightCheckBox = (CheckBox) findViewById(R.id.applist_item_checkbox);
        }
        return rightCheckBox;
    }

    public void setOnCheckListener(final OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
        getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onCheckListener.onCheck(isChecked);
            }
        });
    }

    public void setCheckBoxVisible(boolean visible) {
        if (visible) {
            getCheckBox().setVisibility(View.VISIBLE);
        } else {
            getCheckBox().setVisibility(View.GONE);
        }
    }

    public interface OnCheckListener {
        void onCheck(boolean check);
    }
}
