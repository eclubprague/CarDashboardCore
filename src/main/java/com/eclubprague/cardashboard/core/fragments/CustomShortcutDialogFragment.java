package com.eclubprague.cardashboard.core.fragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;

import java.net.URISyntaxException;

/**
 * Created by Michael on 09.09.2015.
 */
public class CustomShortcutDialogFragment extends DialogFragment {
    private OnShortcutCreatedListener onShortcutCreatedListener;

    public static CustomShortcutDialogFragment newInstance(OnShortcutCreatedListener listener) {
        CustomShortcutDialogFragment f = new CustomShortcutDialogFragment();
        f.setOnShortcutCreatedListener(listener);
        return f;
    }

    public void setOnShortcutCreatedListener(OnShortcutCreatedListener onShortcutCreatedListener) {
        this.onShortcutCreatedListener = onShortcutCreatedListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View dialogView = inflater.inflate(R.layout.fragment_dialog_shortcut_custom, container, false);
        final EditText titleText = (EditText) dialogView.findViewById(R.id.dialog_shortcut_title);
        final EditText intentText = (EditText) dialogView.findViewById(R.id.dialog_shortcut_intent);
        TextView cancelButtonTextView = (TextView) dialogView.findViewById(R.id.dialog_button_cancel);
        cancelButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        TextView okButtonTextView = (TextView) dialogView.findViewById(R.id.dialog_button_ok);
        okButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    intent = Intent.parseUri(intentText.getText().toString(), 0);
                } catch (URISyntaxException e) {
                    intentText.setError("Incorrect uri syntax");
                    return;
                }
                onShortcutCreatedListener.onShortcutCreated(titleText.getText().toString(), intent);
                dismiss();
            }
        });
        return dialogView;
    }

    public interface OnShortcutCreatedListener {
        void onShortcutCreated(String title, Intent intent);
    }

}
