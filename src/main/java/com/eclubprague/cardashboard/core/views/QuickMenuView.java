package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.eclubprague.cardashboard.core.R;

/**
 * Created by Michael on 24.08.2015.
 */
public class QuickMenuView extends LinearLayout {
    private QuickMenuButton buttonTopLeft;
    private QuickMenuButton buttonTopRight;
    private QuickMenuButton buttonBottomLeft;
    private QuickMenuButton buttonBottomRight;

    public QuickMenuView(Context context) {
        super(context);
    }

    public QuickMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuickMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public QuickMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        buttonBottomLeft = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_left);
    }

    public QuickMenuButton getButtonBottomLeft() {
        if (buttonBottomLeft == null) {
            buttonBottomLeft = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_left);
        }
        return buttonBottomLeft;
    }

    public QuickMenuButton getButtonBottomRight() {
        if (buttonBottomRight == null) {
            buttonBottomRight = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_right);
        }
        return buttonBottomRight;
    }

    public QuickMenuButton getButtonTopLeft() {
        if (buttonTopLeft == null) {
            buttonTopLeft = (QuickMenuButton) findViewById(R.id.card_submenu_top_left);
        }
        return buttonTopLeft;
    }

    public QuickMenuButton getButtonTopRight() {
        if (buttonTopRight == null) {
            buttonTopRight = (QuickMenuButton) findViewById(R.id.card_submenu_top_right);
        }
        return buttonTopRight;
    }
}
