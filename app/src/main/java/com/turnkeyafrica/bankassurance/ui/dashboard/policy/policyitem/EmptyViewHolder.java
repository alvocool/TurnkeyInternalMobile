package com.turnkeyafrica.bankassurance.ui.dashboard.policy.policyitem;

import android.content.Intent;

import com.turnkeyafrica.bankassurance.databinding.ItemPolicyEmptyViewBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.bankassurance.ui.dashboard.policy.PolicyEmptyItemViewModel;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;

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