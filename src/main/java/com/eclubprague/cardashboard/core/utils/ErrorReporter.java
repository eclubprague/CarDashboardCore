package com.eclubprague.cardashboard.core.utils;

import android.app.Activity;
import android.app.AlertDialog;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 03.09.2015.
 */
public class ErrorReporter {
    public static final StringResource ERROR_INTERNAL = StringResource.fromResourceId(R.string.error_internal);

    public static void reportApplicationError(Activity activity, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {
        new AlertDialog.Builder(activity).show();
    }

    public static void reportConnectivityError(Activity activity, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {

    }

    public static void reportIncompatibleStateError(Activity activity, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {

    }

    public static void reportPermissionError(Activity activity, StringResource shortMessage, OnPermissionDetailsProvidedListener listener) {

    }

    public interface OnPermissionDetailsProvidedListener {
        void onPermissionDetailsProvided(String username, String password);
    }
}
