
package com.turnkeyafrica.bankassurance.ui.dashboard.policy;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.DataWrapper;
import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.FragmentPolicyBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseFragment;
import com.turnkeyafrica.bankassurance.ui.dashboard.policy.policyitem.adapter.DashboardAdapter;
import com.turnkeyafrica.bankassurance.ui.dashboard.policy.policyitem.adapter.DashboardModel;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class PolicyFragment extends BaseFragment<FragmentPolicyBinding, PolicyViewModel>
        implements PolicyNavigator, PolicyAdapter.PolicyAdapterListener {

    private FragmentPolicyBinding mFragmentPolicyBinding;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    PolicyAdapter mPolicyAdapter;
    @Inject
    ViewModelProviderFactory factory;
    private PolicyViewModel mPolicyViewModel;

    public static PolicyFragment newInstance() {
        Bundle args = new Bundle();
        PolicyFragment fragment = new PolicyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_policy;
    }

    @Override
    public PolicyViewModel getViewModel() {
        mPolicyViewModel = new ViewModelProvider(getViewModelStore(), factory).get(PolicyViewModel.class);
        return mPolicyViewModel;
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {
                ViewUtils.showDialog(getContext(), "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        }else {
            SessionExpired();
        }
    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(getContext(), getBaseActivity(),getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }


    private void SessionExpired(){
        Toast.makeText(getContext(),getResources().getString(R.string.sessionExpired),Toast.LENGTH_LONG).show();
        Intent intent = LoginActivity.newIntent(getContext());
        startActivity(intent);
        getBaseActivity().finish();
    }

    @Override
    public void setAdapter(List<PolicyResponce> policyResponces, List<InsuranceQuoteResponce> qouteResponces) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

            ArrayList<DashboardModel> list= new ArrayList<>();
            if(!policyResponces.isEmpty()){
                for(PolicyResponce policyResponce:policyResponces){

                    if(CommonUtils.ObjectIsNotNull(policyResponce)) {
                        //Do not add if policy does not contain at least one risk
                        if (CommonUtils.ObjectIsNotNull(policyResponce.getRisks()) &&
                                !policyResponce.getRisks().isEmpty()) {
                            list.add(new DashboardModel(DashboardModel.POLICY_TYPE, "", policyResponce, new InsuranceQuoteResponce()));
                        }
                    }
                }
            }

            if(!qouteResponces.isEmpty()) {
                if (CommonUtils.ObjectIsNotNull(qouteResponces.get(0))) {
                    if (CommonUtils.ObjectIsNotNull(qouteResponces.get(0).getInsuranceQuotation())) {
                        list.add(new DashboardModel(DashboardModel.TITLE_TYPE, getBaseActivity().getResources().getString(R.string.quotes), new PolicyResponce(), new InsuranceQuoteResponce()));
                        for (InsuranceQuoteResponce qouteResponce : qouteResponces) {
                            if (CommonUtils.ObjectIsNotNull(qouteResponce)) {
                                if (CommonUtils.ObjectIsNotNull(qouteResponce.getInsuranceQuotation())) {
                                    list.add(new DashboardModel(DashboardModel.QUOTE_TYPE, "", new PolicyResponce(), qouteResponce));
                                }
                            }
                        }
                    }
                }
            }
        if(!list.isEmpty() ) {
            DashboardAdapter adapter = new DashboardAdapter(list, getContext(),getViewModel().getDataManager());
            mFragmentPolicyBinding.PolicyRecyclerView.setLayoutManager(linearLayoutManager);
            mFragmentPolicyBinding.PolicyRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mFragmentPolicyBinding.addInsurance.setVisibility(View.VISIBLE);
            mFragmentPolicyBinding.PolicyRecyclerView.setAdapter(adapter);
        }else {
            mFragmentPolicyBinding.PolicyRecyclerView.setLayoutManager(mLayoutManager);
            mFragmentPolicyBinding.PolicyRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mFragmentPolicyBinding.addInsurance.setVisibility(View.GONE);
            mFragmentPolicyBinding.PolicyRecyclerView.setAdapter(mPolicyAdapter);
        }

    }

    @Override
    public void getInsured() {
        Intent intent = VehicleInsuranceActivity.newIntent(getContext());
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPolicyViewModel.setNavigator(this);
        mPolicyAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPolicyBinding = getViewDataBinding();
        if(isNetworkConnected()) {
            getViewModel().getPolicies(new DataWrapper(getViewModel().getDataManager().getCurrentClientId()));
        }else{
            setAdapter(new ArrayList<>(),new ArrayList<>());
        }
    }


}
