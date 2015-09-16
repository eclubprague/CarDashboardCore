package com.eclubprague.cardashboard.core.model.validation;

import android.widget.TextView;

/**
 * Created by Michael on 16.09.2015.
 */
public class SimpleTextValidator extends TextValidator {

    private final TextValidationConfig validationConfig;
    private final OnValidListener onValidListener;

    public SimpleTextValidator(TextView textView, TextValidationConfig validationConfig) {
        super(textView);
        this.validationConfig = validationConfig;
        this.onValidListener = null;
        validate(textView, textView.getText().toString());
    }

    public SimpleTextValidator(TextView textView, TextValidationConfig validationConfig, OnValidListener onValidListener) {
        super(textView);
        this.validationConfig = validationConfig;
        this.onValidListener = onValidListener;
        validate(textView, textView.getText().toString());
    }

    @Override
    public void validate(TextView textView, String text) {
        if (!validationConfig.isValid(text)) {
            textView.setError(validationConfig.getErrorMessage().getString());
            if (onValidListener != null) {
                onValidListener.onInvalid(textView);
            }
        } else {
            textView.setError(null);
            if (onValidListener != null) {
                onValidListener.onValid(textView);
            }
        }
    }

    public static interface OnValidListener {
        void onValid(TextView validTextView);

        void onInvalid(TextView invalidTextView);
    }
}
