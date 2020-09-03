package com.turnkeyafrica.turnkeybankassurance.ui.policydetails;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.RiskResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.MainDetailsPolicy;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityPolicyDetailsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.policydetails.adapter.CoverDetailsAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.policydetails.adapter.Model;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PolicyDetailsActivity extends BaseActivity<ActivityPolicyDetailsBinding, PolicyDetailsViewModel> implements PolicyDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private PolicyDetailsViewModel mPolicyDetailsViewModel;
    private ActivityPolicyDetailsBinding mActivityPolicyDetailsBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, PolicyDetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_policy_details;
    }

    @Override
    public PolicyDetailsViewModel getViewModel() {
        mPolicyDetailsViewModel = new ViewModelProvider(getViewModelStore(),factory).get(PolicyDetailsViewModel.class);
        return mPolicyDetailsViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityPolicyDetailsBinding = getViewDataBinding();
        mPolicyDetailsViewModel.setNavigator(this);
        Toolbar toolbar =mActivityPolicyDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if(isNetworkConnected()) {
            MainDetails();
        }

    }

    @Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
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
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
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
    }

    @Override
    public void setAdapter(List<DetailsResponce> limits, List<DetailsResponce> excess) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        ArrayList<Model> list= new ArrayList<>();
        if(CommonUtils.ObjectIsNotNull(limits) && !limits.isEmpty()){

                list.add(new Model(Model.TITLE_TYPE, getResources().getString(R.string.headerCoverDetails), ""));
                for (DetailsResponce detailsResponce : limits) {
                    list.add(new Model(Model.DETAILS_TYPE,"\u2022"+ " " + detailsResponce.getSchvNarration(), "\u2014"+ " " + detailsResponce.getSchvValue()));
                }

        }
        if(CommonUtils.ObjectIsNotNull(excess)&& !excess.isEmpty()){

            list.add(new Model(Model.TITLE_TYPE,getResources().getString(R.string.excess),""));
            for(DetailsResponce detailsResponce:excess){
                list.add(new Model(Model.DETAILS_TYPE,"\u2022"+ " " + detailsResponce.getSchvNarration(),"\u2014"+ " " + detailsResponce.getSchvValue()));
            }
        }

        if(!list.isEmpty()) {
            CoverDetailsAdapter adapter = new CoverDetailsAdapter(list, this);
            mActivityPolicyDetailsBinding.coverDetailsListPolicy.setLayoutManager(linearLayoutManager);
            mActivityPolicyDetailsBinding.coverDetailsListPolicy.setItemAnimator(new DefaultItemAnimator());
            mActivityPolicyDetailsBinding.coverDetailsListPolicy.setAdapter(adapter);
        }
    }

    public void MainDetails() {

        Bundle extras = getIntent().getExtras();
        String details;
        PolicyResponce policyResponce;

        MainDetailsPolicy mainDetailsPolicy = new MainDetailsPolicy();

        if (extras != null) {
            details = extras.getString("n12qb9*");
            policyResponce = new Gson().fromJson(details, PolicyResponce.class);

            mainDetailsPolicy.setExpired(false);

            RiskResponce riskResponce = policyResponce.getRisks().get(0);
            mainDetailsPolicy.setInsurer(policyResponce.getPolWebBindName());
            mainDetailsPolicy.setCoverType(riskResponce.getCovtShtDesc());
            mainDetailsPolicy.setAmount(getResources().getString(R.string.currency_kenya)+ " "+ CommonUtils.StringToCurrency(policyResponce.getNettPremium().toString()) +" " + getResources().getString(R.string.perYear));
            mainDetailsPolicy.setStartDate(getResources().getString(R.string.inceptionDateHeader)+ " "+ policyResponce.getPolicyCoverFrom());

         //   if (policyResponce.getPolicyStatus().equalsIgnoreCase()){
                mainDetailsPolicy.setExpiredDate(getResources().getString(R.string.expiredDateHeader)+ " "+ policyResponce.getPolicyCoverTo());
                mainDetailsPolicy.setExpired(true);
           /* }else{
                mainDetailsPolicy.setRenewableDate(getResources().getString(R.string.renewalDateHeader)+ " "+ policyResponce.getPolicyCoverTo());
            }*/

            mainDetailsPolicy.setBindCode(riskResponce.getBindCode());

            mPolicyDetailsViewModel.getPolicyDetails(mainDetailsPolicy);
        }

    }

}
