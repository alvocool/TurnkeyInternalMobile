package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims;


public class ClaimsEmptyItemViewModel {

    private final ClaimsEmptyItemViewModelListener mListener;

    public ClaimsEmptyItemViewModel(ClaimsEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void newClaim(){
        mListener.createClaim();
    }

    public interface ClaimsEmptyItemViewModelListener {
        void createClaim();
    }
}
