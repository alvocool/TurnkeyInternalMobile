package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.data.model.api.CoverTypesResponce;
import java.util.List;

public class InsuranceTypesAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<CoverTypesResponce> coverTypesResponcesList;

    public InsuranceTypesAdapter(Context applicationContext, List<CoverTypesResponce> coverTypesResponceList) {
        this.context = applicationContext;
        this.coverTypesResponcesList = coverTypesResponceList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return coverTypesResponcesList.size();
    }

    @Override
    public Object getItem(int position) {
        return coverTypesResponcesList.get(position);
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
        item.setText(coverTypesResponcesList.get(i).getDesc());
        item.setHint(coverTypesResponcesList.get(i).getShtDesc());
        item.setId(coverTypesResponcesList.get(i).getCode().intValueExact());
        return view;

    }
}