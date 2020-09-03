package com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;

public interface InsuranceQuotesItemViewModelListener {

    void onItemClick(ObservableField<InsuranceQuoteResponce> responceObservableField);
}