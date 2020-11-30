package com.turnkeyafrica.bankassurance.ui.dashboard.claims.viewHolders;

import android.widget.LinearLayout;

import com.turnkeyafrica.bankassurance.databinding.ItemClaimsViewBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.bankassurance.ui.dashboard.claims.ClaimsItemViewModel;

import java.util.List;

public class ClaimsViewHolder extends BaseViewHolder {

    private final ItemClaimsViewBinding mBinding;

    private final List<ClaimsItemViewModel> mClaimsResponseList;

    public ClaimsViewHolder(ItemClaimsViewBinding binding, List<ClaimsItemViewModel> claimsResponseList) {
        super(binding.getRoot());
        this.mBinding = binding;
        mClaimsResponseList = claimsResponseList;
    }

    @Override
    public void onBind(int position) {

        if(position == mClaimsResponseList.size()-1){
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mBinding.claimCardId.getLayoutParams();
            layoutParams.bottomMargin=250;
            this.mBinding.claimCardId.setLayoutParams(layoutParams);
        }

            final ClaimsItemViewModel mClaimsItemViewModel = mClaimsResponseList.get(position);
            mBinding.setViewModel(mClaimsItemViewModel);
            mBinding.executePendingBindings();

    }

}