package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy;

public class PolicyItemViewModel {

    private final PolicyItemViewModelListener mListener;

    public PolicyItemViewModel(PolicyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void getInsured() {
        mListener.getInsured();
    }

    public interface PolicyItemViewModelListener {

        void getInsured();
    }
}
