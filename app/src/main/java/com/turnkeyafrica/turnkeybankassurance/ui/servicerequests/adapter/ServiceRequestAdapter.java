package com.turnkeyafrica.turnkeybankassurance.ui.servicerequests.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequest;
import com.turnkeyafrica.turnkeybankassurance.ui.servicerequestdetails.ServiceRequestDetailsActivity;

import java.util.ArrayList;

public class  ServiceRequestAdapter extends RecyclerView.Adapter{

        private ArrayList<ServiceRequestModel> dataSet;
        private Context mContext;
        private int total_types;


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

        public static class ServiceRequestViewHolder extends RecyclerView.ViewHolder {

            TextView ticketNo;
            TextView status;
            TextView subject;
            TextView datePosted;
            Button mbutton;
            CardView card;

            public ServiceRequestViewHolder(View itemView) {
                super(itemView);

                this.ticketNo = itemView.findViewById(R.id.ticketNo);
                this.status = itemView.findViewById(R.id.status);
                this.subject = itemView.findViewById(R.id.subject);
                this.datePosted = itemView.findViewById(R.id.datePosted);
                this.card = itemView.findViewById(R.id.serviceRequestItemCard);
                mbutton = itemView.findViewById(R.id.serviceRequestItemBtn);
            }
        }



        public ServiceRequestAdapter(ArrayList<ServiceRequestModel> data, Context context) {
            this.dataSet = data;
            this.mContext = context;
            total_types = dataSet.size();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            mContext = parent.getContext();

            View view;

            if(viewType == ServiceRequestModel.TITLE_TYPE){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title_layout, parent, false);
                return new TextTypeTitleViewHolder(view);
            }else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_service_request_layout, parent, false);
                return new ServiceRequestViewHolder(view);
            }

        }

        @Override
        public int getItemViewType(int position) {

            switch (dataSet.get(position).type) {
                case 0:
                    return ServiceRequestModel.TITLE_TYPE;
                case 1:
                    return ServiceRequestModel.SERVICEREQUEST;
                default:
                    return -1;
            }
        }


        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int listPosition) {

            ServiceRequestModel object = dataSet.get(listPosition);
            if (object != null) {
                switch (object.type) {
                    case ServiceRequestModel.TITLE_TYPE:
                        ((TextTypeTitleViewHolder) holder).itemTitle.setText(object.text);
                        break;
                    case ServiceRequestModel.SERVICEREQUEST:
                        initializeServiceRequestView((ServiceRequestViewHolder) holder, object.mServiceRequest, listPosition);
                        break;

                }
            }
        }

            private void initializeServiceRequestView(ServiceRequestViewHolder holder, ServiceRequest serviceRequest, int listPosition){

                if( listPosition == total_types-1){
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.card.getLayoutParams();
                    layoutParams.bottomMargin=250;
                    holder.card.setLayoutParams(layoutParams);
                }

                holder.ticketNo.setText(String.format("Ticket No: %s", serviceRequest.getCode().toString()));

                holder.subject.setText(String.format("Subject: %s", serviceRequest.getSubject()));

                holder.status.setText(serviceRequest.getStatus());

                holder.datePosted.setText(serviceRequest.getDateSubmitted());

                if (serviceRequest.getStatus().equalsIgnoreCase("Closed")) {
                    holder.status.setTextColor(holder.itemView.getResources().getColor(R.color.dash_active));
                }else {
                    holder.status.setTextColor(holder.itemView.getResources().getColor(R.color.colorAccent));
                }

                holder.mbutton.setOnClickListener(v -> {
                    Intent intent = ServiceRequestDetailsActivity.newIntent(mContext);
                    Gson gson = new Gson();
                    String request = gson.toJson(serviceRequest);
                    intent.putExtra("s18Uhh*", request);
                    mContext.startActivity(intent);
                });
            }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

}