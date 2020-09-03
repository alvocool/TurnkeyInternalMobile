package com.turnkeyafrica.turnkeybankassurance.ui.createclaim;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityCreateClaimBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.createclaim.adapter.ClaimsPolActiveAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails.EnterClaimDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.util.List;
import javax.inject.Inject;

public class CreateClaimActivity extends BaseActivity<ActivityCreateClaimBinding, CreateClaimViewModel> implements CreateClaimNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private CreateClaimViewModel mCreateClaimViewModel;
    private ActivityCreateClaimBinding mActivityCreateClaimBinding;

    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    ClaimsPolActiveAdapter mClaimsPolActiveAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_claim;
    }

    @Override
    public CreateClaimViewModel getViewModel() {
        mCreateClaimViewModel = new ViewModelProvider(getViewModelStore(), factory).get(CreateClaimViewModel.class);
        return mCreateClaimViewModel;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateClaimActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCreateClaimBinding = getViewDataBinding();
        mCreateClaimViewModel.setNavigator(this);

        Toolbar toolbar = mActivityCreateClaimBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if(isNetworkConnected()){
            getViewModel().getActivePolicies();
        }

    }


    @Override
    public void setAdapter(List<CreateClaimItemViewModel> createClaimItemViewModels) {
        if(!createClaimItemViewModels.isEmpty()) {

            mClaimsPolActiveAdapter.addClaimsPolActive(createClaimItemViewModels);
            mActivityCreateClaimBinding.claimPolicies.setLayoutManager(mLayoutManager);
            mActivityCreateClaimBinding.claimPolicies.setItemAnimator(new DefaultItemAnimator());
            mActivityCreateClaimBinding.claimPolicies.setAdapter(mClaimsPolActiveAdapter);
        }
    }

    @Override
    public void openCreateClaim(PolicyResponce policyResponse) {
        Intent intent = EnterClaimDetailsActivity.newIntent(this);
        getViewModel().getDataManager().setPolicyResponse(policyResponse);
        startActivity(intent);
    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,"Loading");
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        if(alertDialog != null) {
            alertDialog.dismiss();
        }
    }


}