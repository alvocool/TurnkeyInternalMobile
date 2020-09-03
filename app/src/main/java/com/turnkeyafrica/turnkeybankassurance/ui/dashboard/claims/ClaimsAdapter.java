package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemClaimsEmptyViewBinding;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemClaimsViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims.viewHolders.ClaimsViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims.viewHolders.EmptyClaimsViewHolder;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ClaimsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private final List<ClaimsItemViewModel> mClaimsResponseList;

    private ClaimsAdapterListener mListener;

    public ClaimsAdapter() {
        this.mClaimsResponseList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        if (!mClaimsResponseList.isEmpty()) {
            return mClaimsResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!mClaimsResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_NORMAL) {
            ItemClaimsViewBinding ClaimsViewBinding = ItemClaimsViewBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);

            return new ClaimsViewHolder(ClaimsViewBinding,mClaimsResponseList);
        }else {
            ItemClaimsEmptyViewBinding emptyViewBinding = ItemClaimsEmptyViewBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new EmptyClaimsViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<ClaimsItemViewModel> claimsList) {
        mClaimsResponseList.addAll(claimsList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mClaimsResponseList.clear();
    }

    public void setListener(ClaimsAdapterListener listener) {
        this.mListener = listener;
    }

    public interface ClaimsAdapterListener {

    }

}