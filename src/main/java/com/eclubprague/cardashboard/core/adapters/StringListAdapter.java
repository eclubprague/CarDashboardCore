package com.eclubprague.cardashboard.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.views.TextListItemView;

import java.util.List;

/**
 * Created by Michael on 09.09.2015.
 */
public class StringListAdapter extends BaseAdapter {

    private final List<StringResource> list;
    private final Context context;

    public StringListAdapter(Context context, List<StringResource> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_text, null);
        }
        TextListItemView applistItemView = (TextListItemView) convertView;
        applistItemView.setText((StringResource) getItem(position));
        return convertView;
    }
}
