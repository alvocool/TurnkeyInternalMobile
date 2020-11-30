package com.turnkeyafrica.bankassurance.ui.dashboard.policy.policyitem.adapter;

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
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.ui.findvaluer.FindValuerActivity;
import com.turnkeyafrica.bankassurance.ui.policydetails.PolicyDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.qoutedetails.QuoteDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.vehiclevaluation.VehicleValuationActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter{

        private ArrayList<DashboardModel> dataSet;
        private Context mContext;
        private int total_types;

        private DataManager mDataManager;

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

        public static class PolicyDetailsViewHolder extends RecyclerView.ViewHolder {

            TextView polStatus;
            TextView polInsurer;
            TextView polCoverType;
            TextView riskId;

            CardView card;
            View polDivider;

            LinearLayout polBtnContainer;

            Button mbutton;
            Button mbutton2;
            Button mbutton3;

            public PolicyDetailsViewHolder(View itemView) {
                super(itemView);

                this.polStatus = itemView.findViewById(R.id.policyStatus);
                this.polInsurer = itemView.findViewById(R.id.policyInsurer);
                this.polCoverType = itemView.findViewById(R.id.policyCoverType);
                this.polDivider = itemView.findViewById(R.id.policyDivider);
                this.riskId= itemView.findViewById(R.id.riskId);
                this.card = itemView.findViewById(R.id.polCardId);

                polBtnContainer = itemView.findViewById(R.id.polBtnContainer);

                mbutton = itemView.findViewById(R.id.dashPolicyItemBtn);
                mbutton2 = itemView.findViewById(R.id.uploadEvaluationBtn);
                mbutton3 = itemView.findViewById(R.id.findValuatorBtn);
            }
        }

        public static class QuoteDetailsViewHolder extends RecyclerView.ViewHolder {

            TextView quoteInsurer;
            TextView quoteCoverType;
            TextView quotePrice;
            TextView quoteEndDate;
            Button button;
            CardView card;


            public QuoteDetailsViewHolder(View itemView) {
                super(itemView);

                this.quoteInsurer =  itemView.findViewById(R.id.quoteInsurer);
                this.quoteCoverType =  itemView.findViewById(R.id.quoteCoverType);
                this.quotePrice =  itemView.findViewById(R.id.quotePrice);
                this.quoteEndDate =  itemView.findViewById(R.id.quoteEndDate);
                this.button = itemView.findViewById(R.id.quoteItemBtn);
                this.card = itemView.findViewById(R.id.quoteCardId);
            }
        }


        public DashboardAdapter(ArrayList<DashboardModel>data, Context context, DataManager dataManager) {
            this.dataSet = data;
            this.mContext = context;
            this.mDataManager = dataManager;
            total_types = dataSet.size();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            mContext = parent.getContext();

            View view;

            if(viewType == DashboardModel.TITLE_TYPE){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title_layout, parent, false);
                return new TextTypeTitleViewHolder(view);
            }else if(viewType == DashboardModel.POLICY_TYPE){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_policy_layout, parent, false);
                return new PolicyDetailsViewHolder(view);
            }else{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_quote_layout, parent, false);
                return new QuoteDetailsViewHolder(view);
            }

        }

        @Override
        public int getItemViewType(int position) {

            switch (dataSet.get(position).type) {
                case 0:
                    return DashboardModel.TITLE_TYPE;
                case 1:
                    return DashboardModel.POLICY_TYPE;
                case 2:
                    return DashboardModel.QUOTE_TYPE;
                default:
                    return -1;
            }
        }


        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int listPosition) {

            DashboardModel object = dataSet.get(listPosition);
            if (object != null) {
                switch (object.type) {
                    case DashboardModel.TITLE_TYPE:
                        ((TextTypeTitleViewHolder) holder).itemTitle.setText(object.text);
                        break;
                    case DashboardModel.POLICY_TYPE:
                      initializePolicyView((PolicyDetailsViewHolder) holder, object.mPolicyResponce, listPosition);
                        break;
                    case DashboardModel.QUOTE_TYPE:

                    initializeQuoteView( (QuoteDetailsViewHolder) holder, object.mQouteResponce, listPosition);

                        break;

                }
            }
        }

        private void initializePolicyView(PolicyDetailsViewHolder holder, PolicyResponce mPolicyResponce, int listPosition) {

            if( listPosition == total_types-1){
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.card.getLayoutParams();
                layoutParams.bottomMargin=250;
                holder.card.setLayoutParams(layoutParams);
            }
        holder.polInsurer.setText(mPolicyResponce.getPolWebBindName());
        if(CommonUtils.ObjectIsNotNull(mPolicyResponce.getRisks()) && !mPolicyResponce.getRisks().isEmpty()) {
            holder.polCoverType.setText(mPolicyResponce.getRisks().get(0).getCovtShtDesc());
            holder.riskId.setText(mPolicyResponce.getRisks().get(0).getPropertyId());
        }
         holder.polStatus.setText(mPolicyResponce.getCurrentStatus());
            if(mPolicyResponce.getCurrentStatus().equalsIgnoreCase("Active")){
                holder.polStatus.setTextColor(holder.itemView.getResources().getColor(R.color.dash_active));
                holder.polDivider.setBackgroundColor(holder.itemView.getResources().getColor(R.color.white));
            }else {
                holder.polStatus.setTextColor(holder.itemView.getResources().getColor(R.color.colorHighlight));
                holder.polDivider.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorHighlight));
            }
         holder.polBtnContainer.setVisibility(isActive(mPolicyResponce.getRisks().get(0).getValuationDone(), holder.mbutton));
         holder.mbutton.setOnClickListener(v -> {
             Intent intent = PolicyDetailsActivity.newIntent(mContext);
             Gson gson = new Gson();
             String quote = gson.toJson(mPolicyResponce);
             intent.putExtra("n12qb9*",quote);
             mContext.startActivity(intent);
         });
         holder.mbutton2.setOnClickListener(v -> {
            Intent intent = VehicleValuationActivity.newIntent(mContext);
            String policy = mPolicyResponce.getPolicyNo();
            intent.putExtra("7s12a$",policy);
            mContext.startActivity(intent);
        });
         holder.mbutton3.setOnClickListener(v -> {
            Intent intent = FindValuerActivity.newIntent(mContext);
            String batchNo = mPolicyResponce.getBatchNo().toString();
            intent.putExtra("*aspp99|",batchNo);
            mContext.startActivity(intent);
        });
    }

        private void initializeQuoteView(QuoteDetailsViewHolder holder, InsuranceQuoteResponce insuranceQuoteResponce, int listPosition) {

            if( listPosition == total_types-1){
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.card.getLayoutParams();
                layoutParams.bottomMargin=250;
                holder.card.setLayoutParams(layoutParams);
            }

            if (!CommonUtils.StringIsEmpty(insuranceQuoteResponce.getInsuranceQuotation().getAgnName())){
                holder.quoteInsurer.setText(insuranceQuoteResponce.getInsuranceQuotation().getAgnName());
             }
            if (!CommonUtils.StringIsEmpty(insuranceQuoteResponce.getInsuranceQuotation().getCoverType())){
                holder.quoteCoverType.setText(insuranceQuoteResponce.getInsuranceQuotation().getCoverType());
            }
            if (CommonUtils.ObjectIsNotNull(insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount())){
                holder.quotePrice.setText(String.format("%s %s %s", holder.itemView.getResources().getString(R.string.currency_kenya), insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount(), holder.itemView.getResources().getString(R.string.perYear)));
            }
            if (!CommonUtils.StringIsEmpty(insuranceQuoteResponce.getInsuranceQuotation().getQuotExpiryDate())){
                holder.quoteEndDate.setText(String.format("%s %s", holder.itemView.getResources().getString(R.string.quote_validity), insuranceQuoteResponce.getInsuranceQuotation().getQuotExpiryDate()));
            }
            if (!CommonUtils.StringIsEmpty(insuranceQuoteResponce.getInsuranceQuotation().getAgnName())){
                holder.quoteInsurer.setText(insuranceQuoteResponce.getInsuranceQuotation().getAgnName());
            }

             holder.button.setOnClickListener(v -> {
                 Intent intent = QuoteDetailsActivity.newIntent(mContext);
                 Gson gson = new Gson();
                 String quote = gson.toJson(insuranceQuoteResponce);
                 mDataManager.setInsuranceQuote(quote);
                 mContext.startActivity(intent);
             });
        }

        private int isActive(String polEvalutionStatus,Button mButton){

            if (!CommonUtils.StringIsEmpty(polEvalutionStatus)) {
                if (polEvalutionStatus.equalsIgnoreCase("Y") || polEvalutionStatus.equalsIgnoreCase("YES")) {
                    mButton.setVisibility(View.VISIBLE);
                    return View.GONE;
                }
            }
            mButton.setVisibility(View.GONE);
            return View.VISIBLE;
        }


        @Override
        public int getItemCount() {
            return dataSet.size();
        }

}