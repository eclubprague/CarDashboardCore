package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.eclubprague.cardashboard.core.R;

/**
 * Created by Michael on 24.08.2015.
 */
public class QuickMenuView extends LinearLayout {
    private final QuickMenuButton BUTTON_TOP_LEFT;
    private final QuickMenuButton BUTTON_TOP_RIGHT;
    private final QuickMenuButton BUTTON_BOTTOM_LEFT;
    private final QuickMenuButton BUTTON_BOTTOM_RIGHT;

    public QuickMenuView(Context context) {
        super(context);
        BUTTON_TOP_LEFT = (QuickMenuButton) findViewById(R.id.card_submenu_top_left);
        BUTTON_TOP_RIGHT = (QuickMenuButton) findViewById(R.id.card_submenu_top_right);
        BUTTON_BOTTOM_LEFT = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_left);
        BUTTON_BOTTOM_RIGHT = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_right);
    }

    public QuickMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BUTTON_TOP_LEFT = (QuickMenuButton) findViewById(R.id.card_submenu_top_left);
        BUTTON_TOP_RIGHT = (QuickMenuButton) findViewById(R.id.card_submenu_top_right);
        BUTTON_BOTTOM_LEFT = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_left);
        BUTTON_BOTTOM_RIGHT = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_right);
    }

    public QuickMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        BUTTON_TOP_LEFT = (QuickMenuButton) findViewById(R.id.card_submenu_top_left);
        BUTTON_TOP_RIGHT = (QuickMenuButton) findViewById(R.id.card_submenu_top_right);
        BUTTON_BOTTOM_LEFT = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_left);
        BUTTON_BOTTOM_RIGHT = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_right);
    }

    public QuickMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        BUTTON_TOP_LEFT = (QuickMenuButton) findViewById(R.id.card_submenu_top_left);
        BUTTON_TOP_RIGHT = (QuickMenuButton) findViewById(R.id.card_submenu_top_right);
        BUTTON_BOTTOM_LEFT = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_left);
        BUTTON_BOTTOM_RIGHT = (QuickMenuButton) findViewById(R.id.card_submenu_bottom_right);
    }

    public QuickMenuButton getButtonBottomLeft() {
        return BUTTON_BOTTOM_LEFT;
    }

    public QuickMenuButton getButtonBottomRight() {
        return BUTTON_BOTTOM_RIGHT;
    }

    public QuickMenuButton getButtonTopLeft() {
        return BUTTON_TOP_LEFT;
    }

    public QuickMenuButton getButtonTopRight() {
        return BUTTON_TOP_RIGHT;
    }
}
