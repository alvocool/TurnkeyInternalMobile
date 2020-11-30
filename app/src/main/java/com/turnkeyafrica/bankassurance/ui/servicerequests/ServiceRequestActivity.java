package com.turnkeyafrica.bankassurance.ui.servicerequests;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ServiceRequest;
import com.turnkeyafrica.bankassurance.databinding.ActivityServiceRequestBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.newrequest.NewRequestQuestionActivity;
import com.turnkeyafrica.bankassurance.ui.servicerequests.adapter.ServiceRequestAdapter;
import com.turnkeyafrica.bankassurance.ui.servicerequests.adapter.ServiceRequestModel;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ServiceRequestActivity extends BaseActivity<ActivityServiceRequestBinding,ServiceRequestViewModel> implements ServiceRequestNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ServiceRequestViewModel mServiceRequestViewModel;

    ActivityServiceRequestBinding mActivityServiceRequestBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, ServiceRequestActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_request;
    }

    @Override
    public ServiceRequestViewModel getViewModel() {

        mServiceRequestViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(ServiceRequestViewModel.class);

        return mServiceRequestViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mServiceRequestViewModel.setNavigator(this);
        mActivityServiceRequestBinding = getViewDataBinding();
        Toolbar toolbar = mActivityServiceRequestBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mServiceRequestViewModel.getAllServiceRequests();
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
    public void openServiceRequestQuestion() {
        Intent intent = NewRequestQuestionActivity.newIntent(ServiceRequestActivity.this);
        startActivity(intent);
    }

    @Override
    public void setAdapter(List<ServiceRequest> serviceRequests) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);

        List<ServiceRequestModel> completedServiceRequests = new ArrayList<>();

        List<ServiceRequestModel> otherServiceRequests = new ArrayList<>();

        for (ServiceRequest serviceRequest : serviceRequests) {
            if (CommonUtils.ObjectIsNotNull(serviceRequest)) {
                if(serviceRequest.getStatus().equalsIgnoreCase("Complete")){
                    completedServiceRequests.add(new ServiceRequestModel(ServiceRequestModel.SERVICEREQUEST, "", serviceRequest));
                }else {
                    otherServiceRequests.add(new ServiceRequestModel(ServiceRequestModel.SERVICEREQUEST, "", serviceRequest));
                }
            }
        }

        ArrayList<ServiceRequestModel> list = new ArrayList<>(otherServiceRequests);

        if(!completedServiceRequests.isEmpty()) {

            list.add(new ServiceRequestModel(ServiceRequestModel.TITLE_TYPE, getResources().getString(R.string.requestHistory), new ServiceRequest()));

            list.addAll(completedServiceRequests);
        }

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mActivityServiceRequestBinding.newRequest.getLayoutParams();
        if(!list.isEmpty() ) {
            layoutParams.verticalBias = 0.999f;
            mActivityServiceRequestBinding.newRequest.setLayoutParams(layoutParams);
            ServiceRequestAdapter adapter = new ServiceRequestAdapter(list, getApplicationContext());
            mActivityServiceRequestBinding.serviceRequestList.setLayoutManager(linearLayoutManager);
            mActivityServiceRequestBinding.serviceRequestList.setItemAnimator(new DefaultItemAnimator());
            mActivityServiceRequestBinding.serviceRequestList.setVisibility(View.VISIBLE);
            mActivityServiceRequestBinding.serviceMessage.setVisibility(View.GONE);
            mActivityServiceRequestBinding.serviceRequestList.setAdapter(adapter);
        }else {
            mActivityServiceRequestBinding.serviceMessage.setVisibility(View.VISIBLE);
            mActivityServiceRequestBinding.serviceRequestList.setVisibility(View.GONE);
            layoutParams.verticalBias = 0.58f;
            mActivityServiceRequestBinding.newRequest.setLayoutParams(layoutParams);
        }

    }

}
