package com.turnkeyafrica.turnkeybankassurance.ui.createclaim.adapter;

import com.turnkeyafrica.turnkeybankassurance.databinding.ItemCreateClaimsViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.createclaim.CreateClaimItemViewModel;

import java.util.List;

public class ClaimsPolViewHolder  extends BaseViewHolder {

    private final ItemCreateClaimsViewBinding mBinding;

    private List<CreateClaimItemViewModel> mCreateClaimItemViewModels;

    public ClaimsPolViewHolder(ItemCreateClaimsViewBinding binding,List<CreateClaimItemViewModel> createClaimItemViewModels ) {
        super(binding.getRoot());
        this.mBinding = binding;
        this.mCreateClaimItemViewModels = createClaimItemViewModels;
    }

    @Override
    public void onBind(int position) {
        final CreateClaimItemViewModel mCreateClaimItemViewModel = mCreateClaimItemViewModels.get(position);
        mBinding.setViewModel(mCreateClaimItemViewModel);
        mBinding.executePendingBindings();
    }
}
