package com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;

public interface InsuranceQuotesItemViewModelListener {

    void onItemClick(ObservableField<InsuranceQuoteResponce> responceObservableField);
}