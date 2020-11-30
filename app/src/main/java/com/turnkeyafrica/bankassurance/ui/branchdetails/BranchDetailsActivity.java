package com.turnkeyafrica.bankassurance.ui.branchdetails;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.BranchResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityBranchDetailsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.certificatepickup.CertificatePickUpActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.ui.paymentsuccessful.PaymentSuccessfulActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

public class BranchDetailsActivity extends BaseActivity<ActivityBranchDetailsBinding, BranchDetailsViewModel> implements BranchDetailsNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private BranchDetailsViewModel mBranchDetailsViewModel;

    private ActivityBranchDetailsBinding mActivityBranchDetailsBinding;

    private String batchNo;
    private String policyNo;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_branch_details;
    }

    @Override
    public BranchDetailsViewModel getViewModel() {
        mBranchDetailsViewModel = new ViewModelProvider(getViewModelStore(),factory).get(BranchDetailsViewModel.class);
        return mBranchDetailsViewModel;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }


    @Override
    public void onBackPressed() {

        Intent intent = CertificatePickUpActivity.newIntent(this);
        intent.putExtra("*aspp99|", batchNo);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBranchDetailsViewModel.setNavigator(this);
        mActivityBranchDetailsBinding = getViewDataBinding();
        Toolbar toolbar = mActivityBranchDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setValuer();

    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    private void setValuer() {

        Bundle extras = getIntent().getExtras();

        String branch;

        if (extras != null) {
            branch = extras.getString("veA79&00");
            batchNo = extras.getString("pp1*9912");
            policyNo = extras.getString("7s12a$");
            if (!CommonUtils.StringIsEmpty(branch) && !CommonUtils.StringIsEmpty(batchNo)) {

                Gson gson = new Gson();
                BranchResponce branchResponce = gson.fromJson(branch, BranchResponce.class);

                getViewModel().setBranch(branchResponce, batchNo);
            }
        }

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, BranchDetailsActivity.class);
    }

    @Override
    public void handleError(LocalError error) {
        if (error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        } else {
            SessionExpired();
        }
    }

    @Override
    public void openPaymentSuccessFull() {
        finishAffinity();
        Intent intent = PaymentSuccessfulActivity.newIntent(this);
        intent.putExtra("7s12a$",policyNo);
        intent.putExtra("*aspp99|",batchNo);
        intent.putExtra("cer^tState#!",true);
        startActivity(intent);
        finish();
    }

    private void SessionExpired() {

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.sessionExpired), Toast.LENGTH_LONG).show();
        getViewModel().getDataManager().setUserAsLoggedOut();
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500, getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }
}