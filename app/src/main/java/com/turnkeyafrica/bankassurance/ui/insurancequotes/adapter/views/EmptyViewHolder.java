package com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter.views;

import com.turnkeyafrica.bankassurance.databinding.ItemInsuranceQuotesEmptyViewBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter.InsuranceQuotesAdapter;
import com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter.InsuranceQuotesEmptyItemViewModel;
import com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter.InsuranceQuotesEmptyItemViewModelListener;

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