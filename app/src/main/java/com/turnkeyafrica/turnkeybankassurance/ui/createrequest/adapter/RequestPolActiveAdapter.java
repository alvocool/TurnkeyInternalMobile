package com.turnkeyafrica.turnkeybankassurance.ui.createrequest.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.turnkeyafrica.turnkeybankassurance.databinding.ItemCreateClaimsViewBinding;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemCreateRequestViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.createrequest.CreateRequestItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class RequestPolActiveAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<CreateRequestItemViewModel> mCreateRequestItemViewModel;
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreateRequestViewBinding createRequestViewBinding = ItemCreateRequestViewBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RequestPolViewHolder(createRequestViewBinding, mCreateRequestItemViewModel);
    }

    public RequestPolActiveAdapter() {
        this.mCreateRequestItemViewModel = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mCreateRequestItemViewModel.size();
    }

    public void addClaimsPolActive(List<CreateRequestItemViewModel> createRequestItemViewModels){
        mCreateRequestItemViewModel.addAll(createRequestItemViewModels);
        notifyDataSetChanged();
    }

    public void clearClaimsPolActive(){
        mCreateRequestItemViewModel.clear();
        notifyDataSetChanged();
    }
}
