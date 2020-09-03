package com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemInsuranceQuotesEmptyViewBinding;
import com.turnkeyafrica.turnkeybankassurance.databinding.ItemInsuranceQuotesViewBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.views.EmptyViewHolder;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.views.InsuranceQuotesViewHolder;

import java.util.List;

public class InsuranceQuotesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<InsuranceQuoteResponce> mInsuranceQuoteResponceList;

    private DataManager dataManager;

    private InsuranceQuotesAdapterListener mListener;

    public InsuranceQuotesAdapter(List<InsuranceQuoteResponce> InsuranceQuoteResponseList) {
        this.mInsuranceQuoteResponceList = InsuranceQuoteResponseList;
    }

    @Override
    public int getItemCount() {
        if (mInsuranceQuoteResponceList != null && mInsuranceQuoteResponceList.size() > 0) {
            return mInsuranceQuoteResponceList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mInsuranceQuoteResponceList != null && !mInsuranceQuoteResponceList.isEmpty()) {
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
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemInsuranceQuotesViewBinding insuranceQuotesViewBinding = ItemInsuranceQuotesViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new InsuranceQuotesViewHolder(insuranceQuotesViewBinding,mInsuranceQuoteResponceList, dataManager);
            case VIEW_TYPE_EMPTY:
            default:
                ItemInsuranceQuotesEmptyViewBinding emptyViewBinding = ItemInsuranceQuotesEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding,mListener);
        }
    }

    public void addItems(List<InsuranceQuoteResponce> insuranceQuoteResponceList,DataManager dataManager) {
        mInsuranceQuoteResponceList.addAll(insuranceQuoteResponceList);
        this.dataManager = dataManager;
        notifyDataSetChanged();
    }

    public void clearItems() {
        mInsuranceQuoteResponceList.clear();
    }

    public void setListener(InsuranceQuotesAdapterListener listener) {
        this.mListener = listener;
    }

    public interface InsuranceQuotesAdapterListener {

        void onRetryClick();
    }




}