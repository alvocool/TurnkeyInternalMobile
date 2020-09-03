package com.turnkeyafrica.turnkeybankassurance.ui.enterrequestdetails.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequestType;

import java.util.List;

public class ServiceTypesAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<ServiceRequestType> serviceRequestTypes;

    public ServiceTypesAdapter(Context applicationContext, List<ServiceRequestType> serviceRequestTypes) {
        this.context = applicationContext;
        this.serviceRequestTypes = serviceRequestTypes;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return serviceRequestTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceRequestTypes.get(position);
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
        item.setText(serviceRequestTypes.get(i).getName());
        item.setHint(serviceRequestTypes.get(i).getDescription());
        item.setId(serviceRequestTypes.get(i).getCode().intValueExact());
        return view;

    }
}