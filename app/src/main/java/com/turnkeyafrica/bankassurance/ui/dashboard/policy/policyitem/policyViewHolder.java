package com.turnkeyafrica.bankassurance.ui.dashboard.policy.policyitem;


import android.content.Intent;
import com.turnkeyafrica.bankassurance.databinding.ItemPolicyViewBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.bankassurance.ui.dashboard.policy.PolicyItemViewModel;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;

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