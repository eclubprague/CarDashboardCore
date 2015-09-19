package com.eclubprague.cardashboard.core.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.model.validation.SimpleTextValidator;
import com.eclubprague.cardashboard.core.model.validation.TextValidationConfig;

/**
 * Created by Michael on 19.09.2015.
 */
public class RenameDialogFragment extends DialogFragment {

    private static final TextValidationConfig titleValidationConfig = TextValidationConfig.Builder()
            .required(StringResource.fromResourceId(R.string.validation_field_not_empty))
            .build();

    private OnTitleEnteredListener onTitleEnteredListener;

    public static RenameDialogFragment newInstance(OnTitleEnteredListener listener) {
        RenameDialogFragment f = new RenameDialogFragment();
        f.setOnTitleEnteredListener(listener);
        return f;
    }

    public void setOnTitleEnteredListener(OnTitleEnteredListener onTitleEnteredListener) {
        this.onTitleEnteredListener = onTitleEnteredListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View dialogView = inflater.inflate(R.layout.fragment_dialog_rename, container, false);

        final EditText titleText = (EditText) dialogView.findViewById(R.id.dialog_shortcut_title);
        TextView cancelButtonTextView = (TextView) dialogView.findViewById(R.id.dialog_button_cancel);
        final TextView okButtonTextView = (TextView) dialogView.findViewById(R.id.dialog_button_ok);
        okButtonTextView.setEnabled(false);

        titleText.addTextChangedListener(new SimpleTextValidator(titleText, titleValidationConfig, new SimpleTextValidator.OnValidListener() {
            @Override
            public void onValid(TextView validTextView) {
                okButtonTextView.setEnabled(true);
            }

            @Override
            public void onInvalid(TextView invalidTextView) {
                okButtonTextView.setEnabled(false);
            }
        }));

        cancelButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        okButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleEnteredListener.onTitleEntered(titleText.getText().toString());
                dismiss();
            }
        });


        return dialogView;
    }

    public interface OnTitleEnteredListener {
        void onTitleEntered(String title);
    }
}
