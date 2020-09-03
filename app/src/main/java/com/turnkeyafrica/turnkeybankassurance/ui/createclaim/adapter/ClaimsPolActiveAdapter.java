package com.turnkeyafrica.turnkeybankassurance.ui.createclaim.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemCreateClaimsViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.createclaim.CreateClaimItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClaimsPolActiveAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<CreateClaimItemViewModel> mCreateClaimItemViewModel;
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreateClaimsViewBinding createClaimsViewBinding = ItemCreateClaimsViewBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ClaimsPolViewHolder(createClaimsViewBinding,mCreateClaimItemViewModel);
    }

    public ClaimsPolActiveAdapter() {
        this.mCreateClaimItemViewModel = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mCreateClaimItemViewModel.size();
    }

    public void addClaimsPolActive(List<CreateClaimItemViewModel> createClaimItemViewModels){
        mCreateClaimItemViewModel.addAll(createClaimItemViewModels);
        notifyDataSetChanged();
    }

    public void clearClaimsPolActive(){
        mCreateClaimItemViewModel.clear();
        notifyDataSetChanged();
    }
}
