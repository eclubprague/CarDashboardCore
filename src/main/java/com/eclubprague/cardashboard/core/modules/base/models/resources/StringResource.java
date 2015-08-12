package com.eclubprague.cardashboard.core.modules.base.models.resources;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * StringResource class containing string resource ID or string itself.
 */
public class StringResource extends SimpleAbstractResource {

    private final String string;

    private StringResource(int resourceId) {
        super(resourceId);
        string = null;
    }

    private StringResource(String string) {
        super(0);
        this.string = string;
    }

    public static StringResource fromResourceId(int resourceId) {
        return new StringResource(resourceId);
    }

    public static StringResource fromString(String string) {
        return new StringResource(string);
    }

    public String getString(Context context) {
        if (string != null) {
            return string;
        } else {
            return context.getResources().getString(getResourceId());
        }
    }

    public TextView setInView(TextView textView) {
        if (string != null) {
            textView.setText(string);
        } else {
            textView.setText(getResourceId());
        }
        return textView;
    }
}
