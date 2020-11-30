package com.turnkeyafrica.bankassurance.ui.claimdetails;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsResponse;
import com.turnkeyafrica.bankassurance.databinding.ActivityClaimDetailsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import javax.inject.Inject;

public class ClaimDetailsActivity extends BaseActivity<ActivityClaimDetailsBinding, ClaimDetailsViewModel> implements ClaimDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private ActivityClaimDetailsBinding mActivityClaimDetailsBinding;
    private ClaimDetailsViewModel mClaimDetailsViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_claim_details;
    }

    @Override
    public ClaimDetailsViewModel getViewModel() {
        mClaimDetailsViewModel = new ViewModelProvider(getViewModelStore(), factory).get(ClaimDetailsViewModel.class);
        return mClaimDetailsViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityClaimDetailsBinding = getViewDataBinding();
        mClaimDetailsViewModel.setNavigator(this);

        Toolbar toolbar = mActivityClaimDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setDetails();
    }

    private void setDetails() {
        Bundle extras = getIntent().getExtras();


        if (extras != null) {
          String claim = extras.getString("W4$a5%12");
          if(!CommonUtils.StringIsEmpty(claim)){
              getViewModel().setClaimData(new Gson().fromJson(claim, ClaimsResponse.class));
          }

        }
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, ClaimDetailsActivity.class);
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
