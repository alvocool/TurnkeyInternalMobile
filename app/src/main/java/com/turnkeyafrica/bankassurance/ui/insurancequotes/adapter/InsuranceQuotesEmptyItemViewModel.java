package com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter;


public class InsuranceQuotesEmptyItemViewModel {

    private InsuranceQuotesEmptyItemViewModelListener mListener;

    public InsuranceQuotesEmptyItemViewModel(InsuranceQuotesEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }


}
