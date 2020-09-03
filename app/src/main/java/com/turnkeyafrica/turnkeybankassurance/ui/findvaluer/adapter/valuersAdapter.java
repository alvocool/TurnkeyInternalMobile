package com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ValuersResponce;

import java.util.List;

public class valuersAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<ValuersResponce> mValuersResponcesList;

    public valuersAdapter(Context applicationContext, List<ValuersResponce>  valuersResponcesList) {
        this.context = applicationContext;
        this.mValuersResponcesList = valuersResponcesList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mValuersResponcesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mValuersResponcesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.location_layout, null);
        TextView placeTitle = view.findViewById(R.id.placeTitle);
        TextView placeSubTitle = view.findViewById(R.id.placeSubTitle);
        placeTitle.setText(mValuersResponcesList.get(i).getSprName());
        placeTitle.setId(mValuersResponcesList.get(i).getSprCode().intValueExact());
        placeSubTitle.setText(mValuersResponcesList.get(i).getSprPhysicalAddress());
        return view;

    }
}