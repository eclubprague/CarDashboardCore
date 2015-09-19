package com.eclubprague.cardashboard.core.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IQuickMenuListener;
import com.eclubprague.cardashboard.core.modules.base.ModuleEvent;

/**
 * Created by Michael on 24.08.2015.
 */
public class QuickMenuButton extends RelativeLayout {
    private ImageView iconView;

    public QuickMenuButton(Context context) {
        super(context);
    }

    public QuickMenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuickMenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public QuickMenuButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public QuickMenuButton setIcon(@NonNull IconResource iconResource) {
        if (iconView == null) {
            iconView = (ImageView) findViewById(R.id.card_submenu_icon_1);
            if (iconView == null) {
                iconView = (ImageView) findViewById(R.id.card_submenu_icon_2);
            }
            if (iconView == null) {
                iconView = (ImageView) findViewById(R.id.card_submenu_icon_3);
            }
            if (iconView == null) {
                iconView = (ImageView) findViewById(R.id.card_submenu_icon_4);
            }
//            if(iconView == null){
//                Log.d("QuickMenuButton", getChildCount() + "");
//                for(int i = 0 ; i < getChildCount(); i++){
//                    View childView = getChildAt(i);
//                    Log.d("QuickMenuButton", childView + " with id " + childView.getId());
//                    if(childView instanceof ViewGroup){
//                        ViewGroup viewGroup = (ViewGroup) childView;
//                        for(int j = 0; j < viewGroup.getChildCount(); j++){
//                            View v = viewGroup.getChildAt(j);
//                            Log.d("QuickMenuButton",  "grp: " + v + " with id " + v.getId());
//                        }
//                    }
//                }
//            }
        }
        iconResource.setInView(iconView);
        return this;
    }

    public QuickMenuButton setEvent(final IQuickMenuListener listener, final IModuleContext moduleContext, final ModuleEvent moduleEvent) {
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEvent(moduleEvent, moduleContext);
            }
        });
//        Log.d("QuickMenuButton", moduleEvent.name() + " title: " + moduleEvent.getTitleResource().getString());
        setIcon(moduleEvent.getIcon());
        return this;
    }

}
