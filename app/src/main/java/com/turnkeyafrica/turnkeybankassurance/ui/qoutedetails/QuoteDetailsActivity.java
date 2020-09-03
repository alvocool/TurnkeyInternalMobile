package com.turnkeyafrica.turnkeybankassurance.ui.qoutedetails;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.gson.Gson;
import com.microsoft.appcenter.analytics.Analytics;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.MainDetailsQuote;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityQuoteDetailsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.policydetails.adapter.CoverDetailsAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.policydetails.adapter.Model;
import com.turnkeyafrica.turnkeybankassurance.ui.proofofownership.ProofOfOwnerShipActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public  class QuoteDetailsActivity extends BaseActivity<ActivityQuoteDetailsBinding, QuoteDetailsViewModel> implements QuoteDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private QuoteDetailsViewModel mQuoteDetailsViewModel;
    private ActivityQuoteDetailsBinding mActivityQuoteDetailsBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, QuoteDetailsActivity.class);
    }

    @Override
    public void onBackPressed() {

        if(CommonUtils.StringIsEmpty(getViewModel().getDataManager().getComparisonRequest())){
            Intent intent = DashboardActivity.newIntent(QuoteDetailsActivity.this);
            startActivity(intent);
        }else {
            super.onBackPressed();
          }

    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_quote_details;
    }

    @Override
    public QuoteDetailsViewModel getViewModel() {
        mQuoteDetailsViewModel = new ViewModelProvider(getViewModelStore(),factory).get(QuoteDetailsViewModel.class);
        return mQuoteDetailsViewModel;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityQuoteDetailsBinding = getViewDataBinding();
        mQuoteDetailsViewModel.setNavigator(this);
        Toolbar toolbar =mActivityQuoteDetailsBinding.toolbar;

        if(isNetworkConnected()) {
            setQuote();
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public void buyQuote() {
        Analytics.trackEvent("Buy Cover Clicked");

        Intent intent = ProofOfOwnerShipActivity.newIntent(QuoteDetailsActivity.this);
        startActivity(intent);
    }

    @Override
    public void saveQuote() {

        if(isNetworkConnected()) {
            getViewModel().saveQuoteToDb();
        }
        Analytics.trackEvent("Save quote clicked");

    }

    @Override
    public void openDashboard() {

        Analytics.trackEvent("Save quote successful");
        Intent intent = DashboardActivity.newIntent(QuoteDetailsActivity.this);
        startActivity(intent);
        finish();
    }

    private void setQuote() {

            String insuranceQuote = getViewModel().getDataManager().getInsuranceQuote();

            if (insuranceQuote != null) {
                Gson gson = new Gson();
                MainDetailsQuote mainDetailsQuote = new MainDetailsQuote();
                InsuranceQuoteResponce insuranceQuoteResponce = gson.fromJson(insuranceQuote, InsuranceQuoteResponce.class);

                mQuoteDetailsViewModel.getQuotebyCode(insuranceQuoteResponce.getInsuranceQuotation().getQuotCode());
                mainDetailsQuote.setInsurer(insuranceQuoteResponce.getInsuranceQuotation().getAgnName());
                mainDetailsQuote.setAmount(getResources().getString(R.string.currency_kenya) + ". " + CommonUtils.StringToCurrency(insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount().toString()));
                mainDetailsQuote.setBindCode(insuranceQuoteResponce.getInsuranceQuotation().getBindCode());
                mainDetailsQuote.setQuotSaved(insuranceQuoteResponce.getInsuranceQuotation().getQuotSaved());
                mainDetailsQuote.setCoverType(insuranceQuoteResponce.getInsuranceQuotation().getCoverType());

               if(insuranceQuoteResponce.getInsuranceQuotation().getQuotSaved().equalsIgnoreCase("Y")) {
                   mActivityQuoteDetailsBinding.saveQuoteBtn.setEnabled(false);
               }
                mQuoteDetailsViewModel.getQuoteDetails(mainDetailsQuote);
            }
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
    }@Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    @Override
    public void setAdapter(List<DetailsResponce> limits, List<DetailsResponce> excess) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        ArrayList<Model> list= new ArrayList<>();
        if(CommonUtils.ObjectIsNotNull(limits) && !limits.isEmpty()){

            list.add(new Model(Model.TITLE_TYPE,getResources().getString(R.string.headerCoverDetails),""));
            for(DetailsResponce detailsResponce:limits){
                list.add(new Model(Model.DETAILS_TYPE,"\u2022"+ " " + detailsResponce.getSchvNarration(),"\u2014"+ " " + detailsResponce.getSchvValue()));
            }
        }
        if(CommonUtils.ObjectIsNotNull(excess) && !excess.isEmpty()){

            list.add(new Model(Model.TITLE_TYPE,getResources().getString(R.string.excess),""));
            for(DetailsResponce detailsResponce:excess){
                list.add(new Model(Model.DETAILS_TYPE,"\u2022"+ " " + detailsResponce.getSchvNarration(),"\u2014"+ " " + detailsResponce.getSchvValue()));
            }
        }

        if(!list.isEmpty()) {
            CoverDetailsAdapter adapter = new CoverDetailsAdapter(list, this);
            mActivityQuoteDetailsBinding.coverDetailsList.setLayoutManager(linearLayoutManager);
            mActivityQuoteDetailsBinding.coverDetailsList.setItemAnimator(new DefaultItemAnimator());
            mActivityQuoteDetailsBinding.coverDetailsList.setAdapter(adapter);
        }
    }


}
