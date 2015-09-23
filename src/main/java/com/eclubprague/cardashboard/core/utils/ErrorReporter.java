package com.eclubprague.cardashboard.core.utils;

import android.app.Dialog;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.views.ApplicationErrorDialog;
import com.eclubprague.cardashboard.core.views.SnackbarView;

/**
 * Created by Michael on 03.09.2015.
 */
public class ErrorReporter {
    public static final StringResource ERROR_INTERNAL = StringResource.fromResourceId(R.string.error_internal);

    public static void reportApplicationError(IModuleContext moduleContext, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setTitle("Application error").setMessage(shortMessage.getString()).setIcon(R.drawable.ic_error_black_24dp).show();
//        ApplicationErrorDialogFragment errorDialogFragment = ApplicationErrorDialogFragment.newInstance(
//                shortMessage,
//                detailedMessage,
//                hint
//        );
//        errorDialogFragment.show(activity.getFragmentManager(), "Application error");

        Dialog errorDialog = new ApplicationErrorDialog(
                moduleContext.getContext(),
                shortMessage,
                detailedMessage,
                hint
        );
        errorDialog.show();
    }

    public static void reportApplicationNonCriticalError(IModuleContext moduleContext, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {
//        Toast.makeText(moduleContext.getContext(), shortMessage.getString(moduleContext.getContext()), Toast.LENGTH_LONG)
//                .show();
        SnackbarView.make(moduleContext.getContext())
                .setText(shortMessage)
                .show(moduleContext.getSnackbarHolder());
    }

    public static void reportConnectivityError(IModuleContext moduleContext, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {

    }

    public static void reportIncompatibleStateError(IModuleContext moduleContext, StringResource shortMessage, StringResource detailedMessage, StringResource hint) {

    }

    public static void reportPermissionError(IModuleContext moduleContext, StringResource shortMessage, OnPermissionDetailsProvidedListener listener) {

    }

    public interface OnPermissionDetailsProvidedListener {
        void onPermissionDetailsProvided(String username, String password);
    }
}
