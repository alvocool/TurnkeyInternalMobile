package com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.views;

import android.content.Intent;

import androidx.databinding.ObservableField;

import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemInsuranceQuotesViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.InsuranceQuotesItemViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.InsuranceQuotesItemViewModelListener;
import com.turnkeyafrica.turnkeybankassurance.ui.qoutedetails.QuoteDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.AppLogger;

import java.util.List;

public class InsuranceQuotesViewHolder extends BaseViewHolder implements InsuranceQuotesItemViewModelListener {

    private ItemInsuranceQuotesViewBinding mBinding;

    private InsuranceQuotesItemViewModel mInsuranceQuotesItemViewModel;

    private List<InsuranceQuoteResponce> mInsuranceQuoteResponceList;

    private DataManager dataManager;

    public InsuranceQuotesViewHolder(ItemInsuranceQuotesViewBinding binding, List<InsuranceQuoteResponce> list, DataManager dataManager) {
        super(binding.getRoot());
        this.mBinding = binding;
        this.mInsuranceQuoteResponceList = list;
        this.dataManager = dataManager;
    }

    @Override
    public void onBind(int position) {

        final InsuranceQuoteResponce insuranceQuote = mInsuranceQuoteResponceList.get(position);
        mInsuranceQuotesItemViewModel = new InsuranceQuotesItemViewModel(insuranceQuote, this);
        mBinding.setViewModel(mInsuranceQuotesItemViewModel);
        mBinding.executePendingBindings();
    }

    @Override
    public void onItemClick(ObservableField<InsuranceQuoteResponce> insuranceQuoteResponceObservableField) {

        if (insuranceQuoteResponceObservableField != null) {
            try {

                Intent intent = new Intent(itemView.getContext(), QuoteDetailsActivity.class);

                InsuranceQuoteResponce  insuranceQuoteResponce = insuranceQuoteResponceObservableField.get();

                Gson gson = new Gson();

                dataManager.setInsuranceQuote(gson.toJson(insuranceQuoteResponce));

                itemView.getContext().startActivity(intent);
            } catch (Exception e) {
                AppLogger.d("Invalid Input");
            }
        }

    }
}