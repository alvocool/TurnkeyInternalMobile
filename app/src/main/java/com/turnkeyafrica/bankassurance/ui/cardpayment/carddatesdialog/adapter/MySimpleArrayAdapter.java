package com.turnkeyafrica.bankassurance.ui.cardpayment.carddatesdialog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MySimpleArrayAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<String> strings;

    public MySimpleArrayAdapter(Context applicationContext, List<String> strings) {
        this.context = applicationContext;
        this.strings = strings;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView item = view.findViewById(android.R.id.text1);
        item.setText(strings.get(i));
        return view;

    }
}