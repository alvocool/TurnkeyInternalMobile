package com.turnkeyafrica.turnkeybankassurance.ui.createrequest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityCreateRequestBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.createrequest.adapter.RequestPolActiveAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.enterrequestdetails.EnterServiceRequestDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.util.List;
import javax.inject.Inject;

public class CreateRequestActivity extends BaseActivity<ActivityCreateRequestBinding, CreateRequestViewModel> implements CreateRequestNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private CreateRequestViewModel mCreateRequestViewModel;
    private ActivityCreateRequestBinding mActivityCreateRequestBinding;

    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    RequestPolActiveAdapter mRequestPolActiveAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_request;
    }

    @Override
    public CreateRequestViewModel getViewModel() {
        mCreateRequestViewModel = new ViewModelProvider(getViewModelStore(), factory).get(CreateRequestViewModel.class);
        return mCreateRequestViewModel;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateRequestActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCreateRequestBinding = getViewDataBinding();
        mCreateRequestViewModel.setNavigator(this);

        Toolbar toolbar = mActivityCreateRequestBinding.toolbar;
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
    public void setAdapter(List<CreateRequestItemViewModel> createRequestItemViewModels) {
        if(!createRequestItemViewModels.isEmpty()) {

            mRequestPolActiveAdapter.addClaimsPolActive(createRequestItemViewModels);
            mActivityCreateRequestBinding.requestPolicies.setLayoutManager(mLayoutManager);
            mActivityCreateRequestBinding.requestPolicies.setItemAnimator(new DefaultItemAnimator());
            mActivityCreateRequestBinding.requestPolicies.setAdapter(mRequestPolActiveAdapter);
        }
    }

    @Override
    public void openCreateRequest(PolicyResponce policyResponce) {
        Intent intent = EnterServiceRequestDetailsActivity.newIntent(this);
        intent.putExtra("polResp@1", new Gson().toJson(policyResponce));
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