package com.eclubprague.cardashboard.core.views;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.adapters.ModuleListAdapter;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 13.08.2015.
 */
public class ModuleListDialogFragment extends DialogFragment {

    private static final String TAG = ModuleListDialogFragment.class.getSimpleName();

    private IModuleContext moduleContext;
    private final List<IModule> insertModules = new ArrayList<>();
    private boolean multiInsert = false;
    private OnMultiAddModuleListener onMultiAddModuleListener = null;
    private OnAddModuleListener onAddModuleListener = null;

    public static ModuleListDialogFragment newInstance(IModuleContext moduleContext, OnMultiAddModuleListener onMultiAddModuleListener) {
        ModuleListDialogFragment f = new ModuleListDialogFragment();
        f.setModuleContext(moduleContext);
        f.multiInsert = true;
        f.onMultiAddModuleListener = onMultiAddModuleListener;
        return f;
    }

    public static ModuleListDialogFragment newInstance(IModuleContext moduleContext, OnAddModuleListener onAddModuleListener) {
        ModuleListDialogFragment f = new ModuleListDialogFragment();
        f.setModuleContext(moduleContext);
        f.multiInsert = false;
        f.onAddModuleListener = onAddModuleListener;
        return f;
    }

    public void setModuleContext(IModuleContext moduleContext) {
        this.moduleContext = moduleContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View listView = inflater.inflate(R.layout.fragment_application_list, container, false);
        final ExpandableListView list = (ExpandableListView) listView.findViewById(R.id.applist_list_view);
        list.setGroupIndicator(null);

        ModuleListAdapter adapter;
        if (multiInsert) {
            adapter = new ModuleListAdapter(moduleContext, new ModuleListAdapter.OnModuleCheckListener() {
                @Override
                public void onInsert(IModule module) {
                    insertModules.add(module);
                }

                @Override
                public void onRemove(IModule module) {
                    insertModules.remove(module);
                }
            });
        } else {
            adapter = new ModuleListAdapter(moduleContext, new ModuleListAdapter.OnModuleSelectListener() {

                @Override
                public void onSelected(IModule module) {
                    onAddModuleListener.addModule(module);
                }
            });
        }
        list.setAdapter(adapter);
        TextView cancelView = (TextView) listView.findViewById(R.id.applist_button_cancel);
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        TextView addView = (TextView) listView.findViewById(R.id.applist_button_add);
        if (multiInsert) {
            addView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMultiAddModuleListener.addModules(insertModules);
                }
            });
        } else {
            addView.setVisibility(View.GONE);
        }

//        int width = getResources().getDisplayMetrics().widthPixels;
//        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            list.setIndicatorBounds(width - getPixelValue(40), width - getPixelValue(10));
//        } else {
//            list.setIndicatorBoundsRelative(width - getPixelValue(40), width - getPixelValue(10));
//        }

//        Log.d(TAG, "width = " + listView.getWidth());
//        listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            private boolean isSet = false;
//
//            @Override
//            public void onGlobalLayout() {
//                if(!isSet){
////                    int width = listView.getWidth();
////                    int padding = (int) moduleContext.getContext().getResources().getDimension(R.dimen.applist_right_icon_padding_horizontal);
////                    int iconSize = (int) moduleContext.getContext().getResources().getDimension(R.dimen.applist_right_icon_size);
////                    list.setIndicatorBoundsRelative(width - padding - iconSize, width - padding);
//                    isSet = true;
//                }
////                listView.getHeight(); //height is ready
////                Log.d(TAG, "width = " + listView.getWidth());
//            }
//        });
//        setGroupIndicatorToRight(list);
        return listView;
    }

    private ExpandableListView setGroupIndicatorToRight(ExpandableListView expListView) {
        /* Get the screen width */
        int width = moduleContext.getContext().getResources().getDisplayMetrics().widthPixels;

        expListView.setIndicatorBoundsRelative(width - getDipsFromPixel(80), width
                - getDipsFromPixel(60));
        return expListView;
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale);
    }

    public int getPixelValue(int dp) {
        final float scale = moduleContext.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public interface OnMultiAddModuleListener {
        void addModules(List<IModule> modules);
    }

    public interface OnAddModuleListener {
        void addModule(IModule module);
    }
}
