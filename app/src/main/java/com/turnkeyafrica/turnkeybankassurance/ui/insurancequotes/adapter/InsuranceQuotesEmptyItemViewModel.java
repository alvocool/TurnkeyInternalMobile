package com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter;


public class InsuranceQuotesEmptyItemViewModel {

    private InsuranceQuotesEmptyItemViewModelListener mListener;

    public InsuranceQuotesEmptyItemViewModel(InsuranceQuotesEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }


}
