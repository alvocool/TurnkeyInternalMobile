package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.policyitem;


import android.content.Intent;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemPolicyViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.PolicyItemViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;

public class policyViewHolder extends BaseViewHolder  implements PolicyItemViewModel.PolicyItemViewModelListener  {

    private final ItemPolicyViewBinding mBinding;

    public policyViewHolder(ItemPolicyViewBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    @Override
    public void onBind(int position) {
        PolicyItemViewModel policyItemViewModel = new PolicyItemViewModel(this);
        mBinding.setViewModel(policyItemViewModel);
    }


    @Override
    public void getInsured() {
        Intent intent = VehicleInsuranceActivity.newIntent(itemView.getContext());
        itemView.getContext().startActivity(intent);
    }
}