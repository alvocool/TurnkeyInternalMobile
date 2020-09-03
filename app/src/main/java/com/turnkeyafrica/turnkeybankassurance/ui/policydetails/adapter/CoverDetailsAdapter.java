package com.turnkeyafrica.turnkeybankassurance.ui.policydetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.turnkeyafrica.turnkeybankassurance.R;

import java.util.ArrayList;

public class CoverDetailsAdapter extends RecyclerView.Adapter{



        private ArrayList<Model> dataSet;
        Context mContext;
        int total_types;

        public static class TextTypeTitleViewHolder extends RecyclerView.ViewHolder {

            TextView itemTitle;

            public TextTypeTitleViewHolder(View itemView) {
                super(itemView);

                this.itemTitle = itemView.findViewById(R.id.itemTitle);
            }
        }

        public static class TextTypeDetailsViewHolder extends RecyclerView.ViewHolder {

            TextView textTitle;
            TextView textValue;

            public TextTypeDetailsViewHolder(View itemView) {
                super(itemView);

                this.textTitle = itemView.findViewById(R.id.list2item);
                this.textValue = itemView.findViewById(R.id.list2item2);
            }
        }



        public CoverDetailsAdapter(ArrayList<Model>data, Context context) {
            this.dataSet = data;
            this.mContext = context;
            total_types = dataSet.size();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;

            if(viewType == Model.TITLE_TYPE){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title_layout, parent, false);
                return new TextTypeTitleViewHolder(view);
            }else{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout2, parent, false);
                return new TextTypeDetailsViewHolder(view);
            }

        }

        @Override
        public int getItemViewType(int position) {

            switch (dataSet.get(position).type) {
                case 0:
                    return Model.TITLE_TYPE;
                case 1:
                    return Model.DETAILS_TYPE;
                default:
                    return -1;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int listPosition) {

            Model object = dataSet.get(listPosition);
            if (object != null) {
                switch (object.type) {
                    case Model.TITLE_TYPE:
                        ((TextTypeTitleViewHolder) holder).itemTitle.setText(object.text);
                        break;
                    case Model.DETAILS_TYPE:
                        ((TextTypeDetailsViewHolder) holder).textTitle.setText(object.text);
                        ((TextTypeDetailsViewHolder) holder).textValue.setText(object.text2);
                        break;

                }
            }
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

}