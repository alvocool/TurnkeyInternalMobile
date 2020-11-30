package com.turnkeyafrica.bankassurance.ui.dashboard.claims.viewHolders;

import android.content.Intent;

import com.turnkeyafrica.bankassurance.databinding.ItemClaimsEmptyViewBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.bankassurance.ui.createclaim.CreateClaimActivity;
import com.turnkeyafrica.bankassurance.ui.dashboard.claims.ClaimsEmptyItemViewModel;


public class EmptyClaimsViewHolder extends BaseViewHolder implements ClaimsEmptyItemViewModel.ClaimsEmptyItemViewModelListener {

    private final ItemClaimsEmptyViewBinding mBinding;

    public EmptyClaimsViewHolder(ItemClaimsEmptyViewBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    @Override
    public void onBind(int position) {
        ClaimsEmptyItemViewModel emptyItemViewModel = new ClaimsEmptyItemViewModel(this);
        mBinding.setViewModel(emptyItemViewModel);
    }

    @Override
    public void createClaim() {
        Intent intent = CreateClaimActivity.newIntent(itemView.getContext());
        itemView.getContext().startActivity(intent);
    }
}