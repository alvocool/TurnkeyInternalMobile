package com.turnkeyafrica.turnkeybankassurance.ui.servicerequestdetails;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequest;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityServiceRequestDetailsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;

import javax.inject.Inject;

public class ServiceRequestDetailsActivity extends BaseActivity<ActivityServiceRequestDetailsBinding,ServiceRequestDetailsViewModel> implements ServiceRequestDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ServiceRequestDetailsViewModel mServiceRequestDetailsViewModel;
    ActivityServiceRequestDetailsBinding mActivityServiceRequestDetailsBinding;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_request_details;
    }

    @Override
    public ServiceRequestDetailsViewModel getViewModel() {

        mServiceRequestDetailsViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(ServiceRequestDetailsViewModel.class);

        return mServiceRequestDetailsViewModel;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, ServiceRequestDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mServiceRequestDetailsViewModel.setNavigator(this);
        mActivityServiceRequestDetailsBinding = getViewDataBinding();
        Toolbar toolbar = mActivityServiceRequestDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setUpDetails();
    }

    private void setUpDetails() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String extrasString = extras.getString("s18Uhh*");

            ServiceRequest serviceRequest = new Gson().fromJson(extrasString,ServiceRequest.class);

            if(!CommonUtils.StringIsEmpty(serviceRequest.getComment())){
                mActivityServiceRequestDetailsBinding.commentTitle.setVisibility(View.VISIBLE);
                mActivityServiceRequestDetailsBinding.comments.setVisibility(View.VISIBLE);
                mServiceRequestDetailsViewModel.comments.set(serviceRequest.getComment());
            }

            if(!CommonUtils.StringIsEmpty(serviceRequest.getDateSubmitted())){
                mActivityServiceRequestDetailsBinding.dateSubmittedTitle.setVisibility(View.VISIBLE);
                mActivityServiceRequestDetailsBinding.dateSubmitted.setVisibility(View.VISIBLE);
                mServiceRequestDetailsViewModel.dateSubmitted.set(serviceRequest.getDateSubmitted());
            }


            if(!CommonUtils.StringIsEmpty(serviceRequest.getDescription())) {
                mActivityServiceRequestDetailsBinding.descriptionTitle.setVisibility(View.VISIBLE);
                mActivityServiceRequestDetailsBinding.description.setVisibility(View.VISIBLE);
                mServiceRequestDetailsViewModel.description.set(serviceRequest.getDescription());
            }

            if(!CommonUtils.StringIsEmpty(serviceRequest.getSubject())) {
                mActivityServiceRequestDetailsBinding.requestCategory.setVisibility(View.VISIBLE);
                mActivityServiceRequestDetailsBinding.requestCategoryTitle.setVisibility(View.VISIBLE);
                mServiceRequestDetailsViewModel.requestCategory.set(serviceRequest.getSubject());
            }

            if(!CommonUtils.StringIsEmpty(serviceRequest.getStatus())) {
                mActivityServiceRequestDetailsBinding.status.setVisibility(View.VISIBLE);
                mActivityServiceRequestDetailsBinding.statusTitle.setVisibility(View.VISIBLE);
                mServiceRequestDetailsViewModel.status.set(serviceRequest.getStatus());
            }

            mServiceRequestDetailsViewModel.ticketNo.set(serviceRequest.getCode().toString());

            if(!CommonUtils.StringIsEmpty(serviceRequest.getRefNumber())) {
                mActivityServiceRequestDetailsBinding.polNoTitle.setVisibility(View.VISIBLE);
                mActivityServiceRequestDetailsBinding.polNo.setVisibility(View.VISIBLE);
                mServiceRequestDetailsViewModel.polNo.set(serviceRequest.getRefNumber());
            }

            if(!CommonUtils.StringIsEmpty(serviceRequest.getSubRefNumber())) {
                mActivityServiceRequestDetailsBinding.vehicle.setVisibility(View.VISIBLE);
                mActivityServiceRequestDetailsBinding.vehicleTitle.setVisibility(View.VISIBLE);
                mServiceRequestDetailsViewModel.vehicle.set(serviceRequest.getSubRefNumber());
            }

        }
    }
}
