package com.eclubprague.cardashboard.core.model.validation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Michael on 16.09.2015.
 */
public class GroupTextValidator implements TextWatcher {

    private final List<TextView> textViews;
    private final TextValidationConfig validationConfig;

    public GroupTextValidator(List<TextView> textViews, TextValidationConfig validationConfig) {
        this.textViews = textViews;
        this.validationConfig = validationConfig;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        /* do nothing */
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        /* do nothing */
    }

    @Override
    public void afterTextChanged(Editable s) {
        for (TextView textView : textViews) {
            validationConfig.isValid(textView.getText().toString());
        }
    }
}
