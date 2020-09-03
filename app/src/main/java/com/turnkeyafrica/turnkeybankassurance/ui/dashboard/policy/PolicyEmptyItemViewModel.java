package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy;


public class PolicyEmptyItemViewModel {

    private final PolicyEmptyItemViewModelListener mListener;

    public PolicyEmptyItemViewModel(PolicyEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void getInsured() {
        mListener.getInsured();
    }

    public interface PolicyEmptyItemViewModelListener {

        void getInsured();
    }
}
