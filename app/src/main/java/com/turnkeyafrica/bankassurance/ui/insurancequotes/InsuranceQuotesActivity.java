package com.turnkeyafrica.bankassurance.ui.insurancequotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityInsuranceQuotesBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter.InsuranceQuotesAdapter;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

public class InsuranceQuotesActivity extends BaseActivity<ActivityInsuranceQuotesBinding, InsuranceQuotesViewModel> implements InsuranceQuotesNavigator , InsuranceQuotesAdapter.InsuranceQuotesAdapterListener {


    @Inject
    InsuranceQuotesAdapter mInsuranceQuotesAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    ViewModelProviderFactory factory;
    private InsuranceQuotesViewModel mInsuranceQuotesViewModel;
    private ActivityInsuranceQuotesBinding mActivityInsuranceQuotesBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, InsuranceQuotesActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_quotes;
    }

    @Override
    public InsuranceQuotesViewModel getViewModel() {
        mInsuranceQuotesViewModel = new ViewModelProvider(getViewModelStore(),factory).get(InsuranceQuotesViewModel.class);
        return mInsuranceQuotesViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInsuranceQuotesViewModel.setNavigator(this);

        mInsuranceQuotesAdapter.setListener(this);

        mActivityInsuranceQuotesBinding = getViewDataBinding();
        Toolbar toolbar = mActivityInsuranceQuotesBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ComparisonRequest comparisonRequest = new Gson().fromJson(mInsuranceQuotesViewModel.getDataManager().getComparisonRequest(), ComparisonRequest.class);

        mInsuranceQuotesViewModel.getInsuranceQuotes(setData(comparisonRequest));

    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    private ComparisonRequest setData(ComparisonRequest comparisonRequest) {

        ComparisonRequest.ComparisonRequestQuote comparisonRequestQuote = comparisonRequest.getQuote();

        comparisonRequestQuote.setClntType("I");

        ComparisonRequest.ComparisonRequestRisk comparisonRequestRisk = comparisonRequest.getRisk();

        comparisonRequestRisk.setSclCode(BigDecimal.valueOf(701));

        comparisonRequestRisk.setProCode(BigDecimal.valueOf(1671));

        comparisonRequestQuote.setProCode(BigDecimal.valueOf(1671));

        comparisonRequestQuote.setClntCode(BigDecimal.valueOf(0));

        return comparisonRequest;
    }


    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
            this.setAdapter(null);
        }else {
            SessionExpired();
            this.setAdapter(null);
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
    public void setAdapter(List<InsuranceQuoteResponce> insuranceQuoteResponces) {

        mInsuranceQuotesAdapter.clearItems();
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mActivityInsuranceQuotesBinding.InsuranceQuoteRecyclerView.setLayoutManager(mLayoutManager);
        mActivityInsuranceQuotesBinding.InsuranceQuoteRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if(insuranceQuoteResponces != null) {
            mInsuranceQuotesAdapter.addItems(insuranceQuoteResponces,getViewModel().getDataManager());
        }
        mActivityInsuranceQuotesBinding.InsuranceQuoteRecyclerView.setAdapter(mInsuranceQuotesAdapter);
    }


    @Override
    public void onBackPressed() {
        ComparisonRequest comparisonRequest = new Gson().fromJson(mInsuranceQuotesViewModel.getDataManager().getComparisonRequest(), ComparisonRequest.class);
        mInsuranceQuotesViewModel.getDataManager().setComparisonRequest(comparisonRequest);
        super.onBackPressed();
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
    public void onRetryClick() {

        ComparisonRequest comparisonRequest = new Gson().fromJson(mInsuranceQuotesViewModel.getDataManager().getComparisonRequest(), ComparisonRequest.class);

        mInsuranceQuotesViewModel.getInsuranceQuotes(comparisonRequest);
    }

}
