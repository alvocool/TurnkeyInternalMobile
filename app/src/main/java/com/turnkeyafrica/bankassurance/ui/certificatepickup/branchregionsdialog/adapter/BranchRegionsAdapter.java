package com.turnkeyafrica.bankassurance.ui.certificatepickup.branchregionsdialog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.data.model.api.BranchRegionResponce;

import java.util.List;

public class BranchRegionsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<BranchRegionResponce> branchRegionResponces;

    public BranchRegionsAdapter(Context applicationContext, List<BranchRegionResponce> branchRegionResponces) {
        this.context = applicationContext;
        this.branchRegionResponces = branchRegionResponces;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return branchRegionResponces.size();
    }

    @Override
    public Object getItem(int position) {
        return branchRegionResponces.get(position);
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
        item.setText(branchRegionResponces.get(i).getRegName());
        return view;

    }
}