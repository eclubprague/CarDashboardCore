package com.eclubprague.cardashboard.core.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.StringResource;

/**
 * Created by Michael on 08.09.2015.
 */
public class ApplicationErrorDialog extends Dialog {
    private final StringResource shortMessage;
    private final StringResource longMessage;
    private final StringResource hintMessage;

    public ApplicationErrorDialog(Context context, StringResource shortMessage, StringResource longMessage, StringResource hintMessage) {
        super(context);
        this.hintMessage = hintMessage;
        this.shortMessage = shortMessage;
        this.longMessage = longMessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_dialog_error_application);
        TextView shortMessageTextView = (TextView) findViewById(R.id.dialog_message_short);
        shortMessage.setInView(shortMessageTextView);
        final TextView longMessageTextView = (TextView) findViewById(R.id.dialog_message_long);
        longMessage.setInView(longMessageTextView);
        final TextView hintTextView = (TextView) findViewById(R.id.dialog_message_hint);
        hintMessage.setInView(hintTextView);
        final TextView moreButtonTextView = (TextView) findViewById(R.id.dialog_button_more);
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
        TextView okButtonTextView = (TextView) findViewById(R.id.dialog_button_ok);
        okButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
