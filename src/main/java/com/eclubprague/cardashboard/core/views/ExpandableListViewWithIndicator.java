package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by Michael on 18.08.2015.
 */
public class ExpandableListViewWithIndicator extends ExpandableListView {

    public ExpandableListViewWithIndicator(Context context) {
        super(context);
        init(context);
    }

    public ExpandableListViewWithIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExpandableListViewWithIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ExpandableListViewWithIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
//        setOnGroupCollapseListener(new OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                group
//            }
//        });
//        setOnGroupExpandListener(new OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//
//            }
//        });
    }
}
