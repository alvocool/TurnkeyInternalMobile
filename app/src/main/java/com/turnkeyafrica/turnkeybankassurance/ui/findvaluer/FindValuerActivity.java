package com.turnkeyafrica.turnkeybankassurance.ui.findvaluer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ValuersResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityFindValuatorBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.adapter.valuersAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.locationsdialog.LocationsDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.valuatordetails.ValuatorDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class FindValuerActivity extends BaseActivity<ActivityFindValuatorBinding, FindValuerViewModel> implements FindValuatorNavigator , HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private FindValuerViewModel mFindValuerViewModel;

    private ActivityFindValuatorBinding mActivityFindValuatorBinding;

    private List<String> mLocations;

    private String batchNo;

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
        Intent intent = DashboardActivity.newIntent(this);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_valuator;
    }

    @Override
    public FindValuerViewModel getViewModel() {
        mFindValuerViewModel = new ViewModelProvider(getViewModelStore(),factory).get(FindValuerViewModel.class);
        return mFindValuerViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFindValuerViewModel.setNavigator(this);
        mActivityFindValuatorBinding = getViewDataBinding();
        Toolbar toolbar = mActivityFindValuatorBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getPolicyResponce();
        getLocations();
    }

    private void getPolicyResponce() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            batchNo = extras.getString("*aspp99|");
        }

    }

    private void getLocations() {
        if(isNetworkConnected()) {
            getViewModel().getAllLocations();
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FindValuerActivity.class);
    }

    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        }else {
            SessionExpired();
        }
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


    @Override
    public void setLocations(List<String> locations) {
        if(isNetworkConnected()) {
            mLocations = locations;
            if (!mLocations.isEmpty()) {
                mActivityFindValuatorBinding.locationName.setText(mLocations.get(0));
                getViewModel().getValuersByLocation(mLocations.get(0));
            } else {
                handleError(new LocalError(0, getResources().getString(R.string.error_locations)));
                mActivityFindValuatorBinding.cardView.setClickable(false);
            }
        }
    }

    public void setNewLocation(String location){
        if(isNetworkConnected()) {
            if (!CommonUtils.StringIsEmpty(location)) {
                getViewModel().getValuersByLocation(location);
                mActivityFindValuatorBinding.locationName.setText(location);
            }
        }
    }

    @Override
    public void setValuers(List<ValuersResponce> valuersResponcesList) {
        if (!valuersResponcesList.isEmpty()) {
            valuersAdapter valuersAdapter = new valuersAdapter(this, valuersResponcesList);
            mActivityFindValuatorBinding.valuersList.setAdapter(valuersAdapter);

            mActivityFindValuatorBinding.valuersList.setOnItemClickListener((parent, view, position, id) -> {

                ValuersResponce itemAtPosition = (ValuersResponce) mActivityFindValuatorBinding.valuersList.getItemAtPosition(position);

                Gson gson = new Gson();

                String valuer = gson.toJson(itemAtPosition);

                Intent intent = ValuatorDetailsActivity.newIntent(FindValuerActivity.this);
                intent.putExtra("veA79&00", valuer);
                intent.putExtra("pp1*9912", batchNo);
                startActivity(intent);
                finish();
            });
        }else {
            handleError(new LocalError(0,getResources().getString(R.string.error_valuers)));
        }
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
    public void loadLocations() {
        if (CommonUtils.ObjectIsNotNull(mLocations)) {
            LocationsDialog.newInstance().show(getSupportFragmentManager(), mLocations);
        }else {
            handleError(new LocalError(0,getResources().getString(R.string.error_locations)));
        }
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
