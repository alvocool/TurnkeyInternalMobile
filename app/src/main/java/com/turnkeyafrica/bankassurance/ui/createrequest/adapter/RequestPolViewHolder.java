package com.turnkeyafrica.bankassurance.ui.createrequest.adapter;

import android.view.View;

import com.turnkeyafrica.bankassurance.databinding.ItemCreateRequestViewBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.bankassurance.ui.createrequest.CreateRequestItemViewModel;

import java.util.List;

public class RequestPolViewHolder extends BaseViewHolder implements View.OnClickListener {

    private final ItemCreateRequestViewBinding mBinding;

    private List<CreateRequestItemViewModel> mCreateRequestItemViewModels;

    public RequestPolViewHolder(ItemCreateRequestViewBinding binding, List<CreateRequestItemViewModel> createRequestItemViewModels) {
        super(binding.getRoot());
        this.mBinding = binding;
        this.mCreateRequestItemViewModels = createRequestItemViewModels;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onBind(int position) {
        final CreateRequestItemViewModel mCreateRequestItemViewModel = mCreateRequestItemViewModels.get(position);
        mBinding.setViewModel(mCreateRequestItemViewModel);
        mBinding.executePendingBindings();
    }
}
