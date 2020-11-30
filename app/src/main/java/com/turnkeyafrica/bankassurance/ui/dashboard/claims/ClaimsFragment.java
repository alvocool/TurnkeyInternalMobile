
package com.turnkeyafrica.bankassurance.ui.dashboard.claims;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsResponse;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.FragmentClaimsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseFragment;
import com.turnkeyafrica.bankassurance.ui.claimdetails.ClaimDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.createclaim.CreateClaimActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ClaimsFragment extends BaseFragment<FragmentClaimsBinding, ClaimsViewModel>
        implements ClaimsNavigator, ClaimsAdapter.ClaimsAdapterListener {

    private FragmentClaimsBinding mFragmentClaimsBinding;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    ClaimsAdapter mClaimsAdapter;
    @Inject
    ViewModelProviderFactory factory;
    private ClaimsViewModel mClaimsViewModel;

    public static ClaimsFragment newInstance() {
        Bundle args = new Bundle();
        ClaimsFragment fragment = new ClaimsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_claims;
    }

    @Override
    public ClaimsViewModel getViewModel() {
        mClaimsViewModel = new ViewModelProvider(getViewModelStore(), factory).get(ClaimsViewModel.class);
        return mClaimsViewModel;
    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(getContext(), getBaseActivity(),getResources().getString(R.string.loading));
    }

    @Override
    public void createClaim() {
        Intent intent = CreateClaimActivity.newIntent(getContext());
        startActivity(intent);

    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    @Override
    public void openClaimsDetails(ClaimsResponse claimsResponse) {

        Intent intent = ClaimDetailsActivity.newIntent(getBaseActivity().getApplicationContext());
        intent.putExtra("W4$a5%12", new Gson().toJson(claimsResponse));
        startActivity(intent);
    }


    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(getContext(), "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        }
    }


    @Override
    public void setAdapter(List<ClaimsItemViewModel> claimsItemViewModelList) {
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mClaimsAdapter.clearItems();
        if(!claimsItemViewModelList.isEmpty()) {
            mClaimsAdapter.addItems(claimsItemViewModelList);
        }
        mFragmentClaimsBinding.ClaimsRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentClaimsBinding.ClaimsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentClaimsBinding.ClaimsRecyclerView.setAdapter(mClaimsAdapter);

        if(!claimsItemViewModelList.isEmpty() ) {
            mFragmentClaimsBinding.newClaim.setVisibility(View.VISIBLE);
        }else {
            mFragmentClaimsBinding.newClaim.setVisibility(View.GONE);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClaimsViewModel.setNavigator(this);
        mClaimsAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentClaimsBinding = getViewDataBinding();

       if(isNetworkConnected()) {
            mClaimsViewModel.getClaims();
        }else {
           setAdapter(new ArrayList<>());
       }
    }


}
