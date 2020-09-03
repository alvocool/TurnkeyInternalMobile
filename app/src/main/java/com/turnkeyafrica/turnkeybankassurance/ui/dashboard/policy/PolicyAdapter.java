package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.policyitem.adapter.DashboardModel;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemPolicyEmptyViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.policyitem.EmptyViewHolder;

import java.util.ArrayList;
import java.util.List;


public class PolicyAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private final List<DashboardModel> mDashboardModel;

    private PolicyAdapterListener mListener;

    public PolicyAdapter() {
        this.mDashboardModel = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        if (!mDashboardModel.isEmpty()) {
            return mDashboardModel.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!mDashboardModel.isEmpty()) {
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
        ItemPolicyEmptyViewBinding emptyViewBinding;
        emptyViewBinding = ItemPolicyEmptyViewBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EmptyViewHolder(emptyViewBinding);
    }


    public void addItems(List<DashboardModel> dashboardModels) {
        mDashboardModel.addAll(dashboardModels);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mDashboardModel.clear();
    }

    public void setListener(PolicyAdapterListener listener) {
        this.mListener = listener;
    }

    public interface PolicyAdapterListener {

    }


}