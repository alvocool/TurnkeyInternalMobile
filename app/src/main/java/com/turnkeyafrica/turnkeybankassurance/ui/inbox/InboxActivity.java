package com.turnkeyafrica.turnkeybankassurance.ui.inbox;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.NotificationsResponse;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityInboxBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.inbox.adapter.InboxAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.inbox.adapter.InboxModel;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class InboxActivity extends BaseActivity<ActivityInboxBinding,InboxViewModel> implements InboxNavigator {

    @Inject
    ViewModelProviderFactory factory;
    InboxViewModel mInboxViewModel;
    ActivityInboxBinding mActivityInboxBinding;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inbox;
    }

    @Override
    public InboxViewModel getViewModel() {

        mInboxViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(InboxViewModel.class);

        return mInboxViewModel;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, InboxActivity.class);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(isNetworkConnected()) {
            mInboxViewModel.getAllNotifications();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityInboxBinding = getViewDataBinding();
        mInboxViewModel.setNavigator(this);
        Toolbar toolbar = mActivityInboxBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if(isNetworkConnected()) {
            mInboxViewModel.getAllNotifications();
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
    public void setAdapter(List<NotificationsResponse> notificationsResponses) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);

        List<InboxModel> newInboxModels = new ArrayList<>();

        List<InboxModel> oldInboxModels = new ArrayList<>();

        boolean firstNew = true;
        boolean firstOld = true;

        for (NotificationsResponse response : notificationsResponses) {
            if (CommonUtils.ObjectIsNotNull(response)) {
                if(response.getRead().equalsIgnoreCase("N")){
                    newInboxModels.add(new InboxModel(0,response,firstNew));
                    firstNew = false;
                }else {
                    oldInboxModels.add(new InboxModel(1,response,firstOld));
                    firstOld = false;
                }
            }
        }

        ArrayList<InboxModel> list = new ArrayList<>(newInboxModels);

        list.addAll(oldInboxModels);

        InboxAdapter adapter = new InboxAdapter(list, getApplicationContext());

        mActivityInboxBinding.inboxListContainer.setLayoutManager(linearLayoutManager);
        mActivityInboxBinding.inboxListContainer.setItemAnimator(new DefaultItemAnimator());
        mActivityInboxBinding.inboxListContainer.setAdapter(adapter);


    }

}
