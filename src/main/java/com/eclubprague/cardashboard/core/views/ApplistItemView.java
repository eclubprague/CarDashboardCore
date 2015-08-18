package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
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
        init(context);
    }

    public ApplistItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ApplistItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ApplistItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.rightCheckBox = (CheckBox) findViewById(R.id.applist_item_right_icon);
    }

    public void setOnCheckListener(final OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
        this.rightCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onCheckListener.onCheck(isChecked);
            }
        });
    }

    public interface OnCheckListener {
        void onCheck(boolean check);
    }
}
