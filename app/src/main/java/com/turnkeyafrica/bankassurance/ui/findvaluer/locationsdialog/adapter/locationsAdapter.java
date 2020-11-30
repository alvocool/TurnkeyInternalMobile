package com.turnkeyafrica.bankassurance.ui.findvaluer.locationsdialog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turnkeyafrica.bankassurance.R;

import java.util.List;

public class locationsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<String> locations;

    public locationsAdapter(Context applicationContext, List<String> locations) {
        this.context = applicationContext;
        this.locations = locations;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public Object getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_layout, null);
        TextView item = view.findViewById(R.id.item);
        item.setText(locations.get(i));
        return view;

    }
}