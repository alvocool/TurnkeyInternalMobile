package com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.BranchRegionResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.BranchResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityCertificatePickUpBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.branchdetails.BranchDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.adapter.branchesAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.branchregionsdialog.BranchRegionDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import androidx.lifecycle.ViewModelProvider;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class CertificatePickUpActivity  extends BaseActivity<ActivityCertificatePickUpBinding, CertificatePickUpViewModel> implements CertificatePickUpNavigator , HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private CertificatePickUpViewModel mCertificatePickUpViewModel;

    private ActivityCertificatePickUpBinding mActivityCertificatePickUpBinding;

    private List<BranchRegionResponce> mBranchRegions;

    private String batchNo;

    private String policyNo;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certificate_pick_up;
    }

    @Override
    public CertificatePickUpViewModel getViewModel() {
        mCertificatePickUpViewModel = new ViewModelProvider(getViewModelStore(),factory).get(CertificatePickUpViewModel.class);
        return mCertificatePickUpViewModel;
    }

    @Override
    public void onBackPressed() {
        handleError(new LocalError(0,getResources().getString(R.string.certificatePickupError)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCertificatePickUpViewModel.setNavigator(this);
        mActivityCertificatePickUpBinding = getViewDataBinding();
        getPolicyResponce();
        getLocations();
    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    private void getPolicyResponce() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            batchNo = extras.getString("*aspp99|");
            policyNo = extras.getString("7s12a$");
        }

    }

    private void getLocations() {
        if (isNetworkConnected()) {
            getViewModel().getBranchesRegions();
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, CertificatePickUpActivity.class);
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


    @Override
    public void setBranchRegions(List<BranchRegionResponce> branchRegions) {
        if (isNetworkConnected()) {
            mBranchRegions = branchRegions;
            if (!mBranchRegions.isEmpty()) {
                mActivityCertificatePickUpBinding.branchRegionName.setText(mBranchRegions.get(0).getRegName());
                getViewModel().getBranchesByLocation(mBranchRegions.get(0).getRegCode());
            } else {
                handleError(new LocalError(0, getResources().getString(R.string.error_branch_region)));
                mActivityCertificatePickUpBinding.cardView.setClickable(false);
            }
        }
    }

    public void setNewBranch(BranchRegionResponce region) {
        if (isNetworkConnected()) {
            if (CommonUtils.ObjectIsNotNull(region)) {
                getViewModel().getBranchesByLocation(region.getRegCode());
                mActivityCertificatePickUpBinding.branchRegionName.setText(region.getRegName());
            }
        }
    }

    @Override
    public void setBranches(List<BranchResponce> branchResponces) {
        if (!branchResponces.isEmpty()) {
            branchesAdapter valuersAdapter = new branchesAdapter(this, branchResponces);
            mActivityCertificatePickUpBinding.branchesList.setAdapter(valuersAdapter);

            mActivityCertificatePickUpBinding.branchesList.setOnItemClickListener((parent, view, position, id) -> {

                BranchResponce itemAtPosition = (BranchResponce) mActivityCertificatePickUpBinding.branchesList.getItemAtPosition(position);

                Gson gson = new Gson();

                String branch = gson.toJson(itemAtPosition);

                Intent intent = BranchDetailsActivity.newIntent(CertificatePickUpActivity.this);
                intent.putExtra("veA79&00", branch);
                intent.putExtra("pp1*9912", batchNo);
                intent.putExtra("7s12a$", policyNo);
                startActivity(intent);
                finish();
            });
        } else {
            handleError(new LocalError(0, getResources().getString(R.string.error_valuers)));
        }
    }

    @Override
    public void loadBranches() {
        if (CommonUtils.ObjectIsNotNull(mBranchRegions)) {
            BranchRegionDialog.newInstance().show(getSupportFragmentManager(), mBranchRegions);
        } else {
            handleError(new LocalError(0, getResources().getString(R.string.error_branch_region)));
        }
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}