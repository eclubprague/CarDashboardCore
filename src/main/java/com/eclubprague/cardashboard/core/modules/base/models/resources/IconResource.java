package com.eclubprague.cardashboard.core.modules.base.models.resources;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * IconResource class containing icon resource ID.
 */
public class IconResource extends SimpleAbstractResource {

    private final Drawable icon;

    private IconResource(int resourceId) {
        super(resourceId);
        icon = null;
    }

    private IconResource(Drawable icon) {
        super(0);
        this.icon = icon;
    }

    public static IconResource fromResourceId(int resourceId) {
        return new IconResource(resourceId);
    }

    public static IconResource fromDrawable(Drawable icon) {
        return new IconResource(icon);
    }

    public Drawable getIcon(Context context) {
        if (icon != null) {
            return icon;
        } else {
            return context.getResources().getDrawable(getResourceId(), context.getTheme());
        }
    }

    public ImageView setInView(ImageView imageView) {
        if (icon != null) {
            imageView.setImageDrawable(icon);
        } else {
            imageView.setImageResource(getResourceId());
        }
        return imageView;
    }


}
