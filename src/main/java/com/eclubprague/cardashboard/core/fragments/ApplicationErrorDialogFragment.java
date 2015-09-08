package com.eclubprague.cardashboard.core.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.StringResource;

/**
 * Created by Michael on 08.09.2015.
 */
public class ApplicationErrorDialogFragment extends DialogFragment {
    private StringResource shortMessage;
    private StringResource longMessage;
    private StringResource hintMessage;

    public static ApplicationErrorDialogFragment newInstance(StringResource shortMessage, StringResource longMessage, StringResource hintMessage) {
        ApplicationErrorDialogFragment df = new ApplicationErrorDialogFragment();
        df.setHintMessage(hintMessage);
        df.setLongMessage(longMessage);
        df.setShortMessage(shortMessage);
        return df;
    }

    public void setHintMessage(StringResource hintMessage) {
        this.hintMessage = hintMessage;
    }

    public void setLongMessage(StringResource longMessage) {
        this.longMessage = longMessage;
    }

    public void setShortMessage(StringResource shortMessage) {
        this.shortMessage = shortMessage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View dialogView = inflater.inflate(R.layout.fragment_dialog_error_application, container, false);
        TextView shortMessageTextView = (TextView) dialogView.findViewById(R.id.dialog_message_short);
        shortMessage.setInView(shortMessageTextView);
        final TextView longMessageTextView = (TextView) dialogView.findViewById(R.id.dialog_message_long);
        longMessage.setInView(longMessageTextView);
        final TextView hintTextView = (TextView) dialogView.findViewById(R.id.dialog_message_hint);
        hintMessage.setInView(hintTextView);
        final TextView moreButtonTextView = (TextView) dialogView.findViewById(R.id.dialog_button_more);
        moreButtonTextView.setOnClickListener(new View.OnClickListener() {
            private boolean isExpanded = false;

            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    moreButtonTextView.setText(R.string.dialog_more);
                    longMessageTextView.setVisibility(View.GONE);
                    isExpanded = false;
                } else {
                    moreButtonTextView.setText(R.string.dialog_less);
                    longMessageTextView.setVisibility(View.VISIBLE);
                    isExpanded = true;
                }
            }
        });
        TextView okButtonTextView = (TextView) dialogView.findViewById(R.id.dialog_button_ok);
        okButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialogView;
    }
}
