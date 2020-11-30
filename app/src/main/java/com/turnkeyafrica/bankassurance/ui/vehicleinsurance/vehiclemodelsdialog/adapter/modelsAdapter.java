package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehiclemodelsdialog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.data.model.api.VehicleMakesResponce;

import java.util.List;

public class modelsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<VehicleMakesResponce> vehicleMakesResponceList;

    public modelsAdapter(Context applicationContext, List<VehicleMakesResponce> vehicleMakesResponceList) {
        this.context = applicationContext;
        this.vehicleMakesResponceList = vehicleMakesResponceList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return vehicleMakesResponceList.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicleMakesResponceList.get(position);
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
        item.setText(vehicleMakesResponceList.get(i).getVmMake());
        item.setId(vehicleMakesResponceList.get(i).getVmCode().intValueExact());
        return view;

    }
}