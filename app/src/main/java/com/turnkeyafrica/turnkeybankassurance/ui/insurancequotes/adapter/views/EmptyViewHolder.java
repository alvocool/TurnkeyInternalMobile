package com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.views;

import com.turnkeyafrica.turnkeybankassurance.databinding.ItemInsuranceQuotesEmptyViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.InsuranceQuotesAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.InsuranceQuotesEmptyItemViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.InsuranceQuotesEmptyItemViewModelListener;

public class EmptyViewHolder extends BaseViewHolder implements InsuranceQuotesEmptyItemViewModelListener {

    private ItemInsuranceQuotesEmptyViewBinding mBinding;
    private InsuranceQuotesAdapter.InsuranceQuotesAdapterListener mListener;

    public EmptyViewHolder(ItemInsuranceQuotesEmptyViewBinding binding, InsuranceQuotesAdapter.InsuranceQuotesAdapterListener listener) {
        super(binding.getRoot());
        this.mBinding = binding;
        this.mListener = listener;
    }


    @Override
    public void onBind(int position) {
        InsuranceQuotesEmptyItemViewModel emptyItemViewModel = new InsuranceQuotesEmptyItemViewModel(this);
        mBinding.setViewModel(emptyItemViewModel);
    }

    @Override
    public void onRetryClick() {
        mListener.onRetryClick();
    }
}