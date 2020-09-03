package com.turnkeyafrica.turnkeybankassurance.ui.insuranceproducts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.turnkeyafrica.turnkeybankassurance.R;

import java.util.ArrayList;

public class InsuranceProductsAdapter extends RecyclerView.Adapter{

        private ArrayList<InsuranceProductsModel> dataSet;
        Context mContext;
        int total_types;

        public static class TextTypeTitleViewHolder extends RecyclerView.ViewHolder {

            TextView itemTitle;
            View itemTitleUnderline;

            public TextTypeTitleViewHolder(View itemView) {
                super(itemView);

                this.itemTitle = itemView.findViewById(R.id.itemTitle);
                this.itemTitleUnderline = itemView.findViewById(R.id.itemTitleUnderline);
                this.itemTitleUnderline.setVisibility(View.INVISIBLE);
            }
        }

        public static class TextTypeDetailsViewHolder extends RecyclerView.ViewHolder {

            TextView textTitle;

            public TextTypeDetailsViewHolder(View itemView) {
                super(itemView);

                this.textTitle =  itemView.findViewById(R.id.item);
            }
        }



        public InsuranceProductsAdapter(ArrayList<InsuranceProductsModel>data, Context context) {
            this.dataSet = data;
            this.mContext = context;
            total_types = dataSet.size();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;

            if(viewType == InsuranceProductsModel.TITLE_TYPE){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title_layout, parent, false);
                return new TextTypeTitleViewHolder(view);
            }else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_layout, parent, false);
                return new TextTypeDetailsViewHolder(view);
            }
        }

        @Override
        public int getItemViewType(int position) {

            switch (dataSet.get(position).type) {
                case 0:
                    return InsuranceProductsModel.TITLE_TYPE;
                case 1:
                    return InsuranceProductsModel.DETAILS_TYPE;
                default:
                    return -1;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int listPosition) {

            InsuranceProductsModel object = dataSet.get(listPosition);
            if (object != null) {
                switch (object.type) {
                    case InsuranceProductsModel.TITLE_TYPE:
                        ((TextTypeTitleViewHolder) holder).itemTitle.setText(object.text);
                        ((TextTypeTitleViewHolder) holder).itemTitle.setPadding(0,object.paddingTop,0,15);
                        break;
                    case InsuranceProductsModel.DETAILS_TYPE:
                        ((TextTypeDetailsViewHolder) holder).textTitle.setText(object.text);
                        break;

                }
            }
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

}