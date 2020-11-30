package com.turnkeyafrica.bankassurance.ui.insuranceproducts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ProductResponse;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityInsuranceProductsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.insuranceproducts.adapter.InsuranceProductsAdapter;
import com.turnkeyafrica.bankassurance.ui.insuranceproducts.adapter.InsuranceProductsModel;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class InsuranceProductsActivity extends BaseActivity<ActivityInsuranceProductsBinding, InsuranceProductsViewModel> implements InsuranceProductsNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private InsuranceProductsViewModel mInsuranceProductsViewModel;

    private ActivityInsuranceProductsBinding mActivityInsuranceProductsBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_products;
    }

    @Override
    public InsuranceProductsViewModel getViewModel() {
        mInsuranceProductsViewModel = new ViewModelProvider(getViewModelStore(),factory).get(InsuranceProductsViewModel.class);
        return mInsuranceProductsViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInsuranceProductsViewModel.setNavigator(this);
        mActivityInsuranceProductsBinding = getViewDataBinding();
        Toolbar toolbar = mActivityInsuranceProductsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getViewModel().getAllProducts("N");
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, InsuranceProductsActivity.class);
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
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    @Override
    public void setProducts(List<ProductResponse> webProductResponse, List<ProductResponse> allProductResponse) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        ArrayList<InsuranceProductsModel> list= new ArrayList<>();
        if(CommonUtils.ObjectIsNotNull(webProductResponse) && !webProductResponse.isEmpty()){

            list.add(new InsuranceProductsModel(InsuranceProductsModel.TITLE_TYPE, getResources().getString(R.string.titleTopInsuranceProducts),0));
            for (ProductResponse product : webProductResponse) {
                list.add(new InsuranceProductsModel(InsuranceProductsModel.DETAILS_TYPE, "•" +" " +product.getProDesc(),0));
            }

        }
        if(CommonUtils.ObjectIsNotNull(allProductResponse)&& !allProductResponse.isEmpty()){

            list.add(new InsuranceProductsModel(InsuranceProductsModel.TITLE_TYPE,getResources().getString(R.string.titleOtherInsuranceProducts),Math.round(getResources().getDimension(R.dimen.scale_30_dim))));
            for(ProductResponse product:allProductResponse){
                list.add(new InsuranceProductsModel(InsuranceProductsModel.DETAILS_TYPE,"•" +" " +product.getProDesc(),0));
            }
        }

        if(!list.isEmpty()) {
            InsuranceProductsAdapter adapter = new InsuranceProductsAdapter(list, this);
            mActivityInsuranceProductsBinding.insuranceProductRecycleView.setLayoutManager(linearLayoutManager);
            mActivityInsuranceProductsBinding.insuranceProductRecycleView.setItemAnimator(new DefaultItemAnimator());
            mActivityInsuranceProductsBinding.insuranceProductRecycleView.setAdapter(adapter);
        }

    }


    @Override
    public void openPage() {

        Uri webPage = Uri.parse("http://turnkeyafrica.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
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

}