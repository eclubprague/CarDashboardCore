package com.eclubprague.cardashboard.core.utils;

import android.view.View;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 26.08.2015.
 */
public class ErrorReporter {
    public static final StringResource ERROR_INTERNAL = StringResource.fromResourceId(R.string.error_internal);

    public static void reportApplicationError(View parent, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {

    }

    public static void reportConnectivityError(View parent, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {

    }

    public static void reportIncompatibleStateError(View parent, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {

    }

    public static void reportPermissionError(View parent, StringResource shortMessage, OnPermissionDetailsProvidedListener listener) {

    }

    public interface OnPermissionDetailsProvidedListener {
        void onPermissionDetailsProvided(String username, String password);
    }
}
