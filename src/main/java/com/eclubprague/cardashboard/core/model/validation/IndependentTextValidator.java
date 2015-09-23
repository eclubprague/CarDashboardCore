package com.eclubprague.cardashboard.core.model.validation;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Michael on 16.09.2015.
 */
public abstract class IndependentTextValidator implements TextWatcher {

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
        validate();
    }

    public abstract void validate();
}
