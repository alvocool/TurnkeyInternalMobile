package com.turnkeyafrica.turnkeybankassurance.ui.valuatordetails;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ValuersResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityValuatorDetailsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.FindValuerActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import javax.inject.Inject;

public class ValuatorDetailsActivity extends BaseActivity<ActivityValuatorDetailsBinding, ValuatorDetailsViewModel> implements ValuatorDetailsNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private ValuatorDetailsViewModel mValuatorDetailsViewModel;

    private ActivityValuatorDetailsBinding mActivityValuatorDetailsBinding;

    private String batchNo;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_valuator_details;
    }

    @Override
    public ValuatorDetailsViewModel getViewModel() {
        mValuatorDetailsViewModel = new ViewModelProvider(getViewModelStore(),factory).get(ValuatorDetailsViewModel.class);
        return mValuatorDetailsViewModel;
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
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    @Override
    public void onBackPressed() {

        Intent intent = FindValuerActivity.newIntent(this);
        intent.putExtra("*aspp99|", batchNo);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mValuatorDetailsViewModel.setNavigator(this);
        mActivityValuatorDetailsBinding = getViewDataBinding();
        Toolbar toolbar = mActivityValuatorDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setValuer();

    }

    private void setValuer() {

        Bundle extras = getIntent().getExtras();

        String valuer;
        String batchNo;

        if (extras != null) {
            valuer = extras.getString("veA79&00");
            batchNo = extras.getString("pp1*9912");

            this.batchNo = batchNo;

            if(!CommonUtils.StringIsEmpty(valuer) && !CommonUtils.StringIsEmpty(batchNo)){

                Gson gson = new Gson();
                ValuersResponce valuersResponce = gson.fromJson(valuer, ValuersResponce.class);

                getViewModel().setValuer(valuersResponce,batchNo);
            }
        }

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ValuatorDetailsActivity.class);
    }

    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        }else {
            SessionExpired();
        }
    }

    @Override
    public void openDashboard() {
        Intent intent = DashboardActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

   private void SessionExpired(){

        Toast.makeText(getApplicationContext(),getResources().getString(R.string.sessionExpired),Toast.LENGTH_LONG).show();
        getViewModel().getDataManager().setUserAsLoggedOut();
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
        finish();
    }@Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }
}