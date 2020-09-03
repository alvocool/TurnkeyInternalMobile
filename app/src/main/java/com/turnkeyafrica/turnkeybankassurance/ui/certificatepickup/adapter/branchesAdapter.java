package com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.BranchResponce;

import java.util.List;

public class branchesAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<BranchResponce> mBranchResponcesList;

    public branchesAdapter(Context applicationContext, List<BranchResponce>  BranchResponcesList) {
        this.context = applicationContext;
        this.mBranchResponcesList = BranchResponcesList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mBranchResponcesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBranchResponcesList.get(position);
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
        placeTitle.setText(mBranchResponcesList.get(i).getDesc());
        placeTitle.setId(mBranchResponcesList.get(i).getCode().intValueExact());
        placeSubTitle.setText(mBranchResponcesList.get(i).getPhysicalAddress());
        return view;

    }
}