package com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.RiskResponce;
import java.util.List;

public class RisksModelsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<RiskResponce> riskResponces;

    public RisksModelsAdapter(Context applicationContext, List<RiskResponce> riskResponces) {
        this.context = applicationContext;
        this.riskResponces = riskResponces;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return riskResponces.size();
    }

    @Override
    public Object getItem(int position) {
        return riskResponces.get(position);
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
        item.setText(riskResponces.get(i).getPropertyId());
        item.setId(riskResponces.get(i).getId().intValueExact());
        return view;

    }
}