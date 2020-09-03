package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.policyitem;

import android.content.Intent;

import com.turnkeyafrica.turnkeybankassurance.databinding.ItemPolicyEmptyViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.PolicyEmptyItemViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;

public class EmptyViewHolder extends BaseViewHolder implements PolicyEmptyItemViewModel.PolicyEmptyItemViewModelListener {

    private final ItemPolicyEmptyViewBinding mBinding;

    public EmptyViewHolder(ItemPolicyEmptyViewBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    @Override
    public void onBind(int position) {
        PolicyEmptyItemViewModel emptyItemViewModel = new PolicyEmptyItemViewModel(this);
        mBinding.setViewModel(emptyItemViewModel);
    }


    @Override
    public void getInsured() {
        Intent intent = VehicleInsuranceActivity.newIntent(itemView.getContext());
        itemView.getContext().startActivity(intent);
    }
}