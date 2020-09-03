package com.turnkeyafrica.turnkeybankassurance.ui.notificationdetails;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.NotificationsResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityNotificationDetailsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.inject.Inject;

public class NotificationDetailsActivity extends BaseActivity<ActivityNotificationDetailsBinding,NotificationDetailsViewModel> implements NotificationDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private NotificationDetailsViewModel mNotificationDetailsViewModel;
    private ActivityNotificationDetailsBinding mActivityNotificationDetailsBinding;
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notification_details;
    }

    @Override
    public NotificationDetailsViewModel getViewModel() {
        mNotificationDetailsViewModel = new ViewModelProvider(getViewModelStore(), factory).get(NotificationDetailsViewModel.class);
        return mNotificationDetailsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotificationDetailsViewModel.setNavigator(this);
        mActivityNotificationDetailsBinding = getViewDataBinding();
        Toolbar toolbar =mActivityNotificationDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setUpView();

    }

    private void setUpView() {

        Bundle extras = getIntent().getExtras();

        String notificationString = Objects.requireNonNull(extras).getString("A51@3%^");

        if(!CommonUtils.StringIsEmpty(notificationString)){

            NotificationsResponse notificationsResponse = new Gson().fromJson(notificationString, NotificationsResponse.class);

            if(isNetworkConnected()){
                if(notificationsResponse.getRead().equalsIgnoreCase("N")){
                    notificationsResponse.setRead("Y");
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    notificationsResponse.setDateRead(format.format(new Date()));
                    mNotificationDetailsViewModel.setNotificationRead(notificationsResponse);
                }
            }

            if(!CommonUtils.StringIsEmpty(notificationsResponse.getTitle())){
                mActivityNotificationDetailsBinding.Title.setVisibility(View.VISIBLE);
                mActivityNotificationDetailsBinding.title.setVisibility(View.VISIBLE);
                mActivityNotificationDetailsBinding.title.setText(notificationsResponse.getTitle());

            }

            if(!CommonUtils.StringIsEmpty(notificationsResponse.getMessage())){
                mActivityNotificationDetailsBinding.messageTitle.setVisibility(View.VISIBLE);
                mActivityNotificationDetailsBinding.message.setVisibility(View.VISIBLE);
                mActivityNotificationDetailsBinding.message.setText(notificationsResponse.getMessage());
            }

            if(!CommonUtils.StringIsEmpty(notificationsResponse.getSubject())){
                mActivityNotificationDetailsBinding.subjectTitle.setVisibility(View.VISIBLE);
                mActivityNotificationDetailsBinding.subject.setVisibility(View.VISIBLE);
                mActivityNotificationDetailsBinding.subject.setText(notificationsResponse.getSubject());
            }

            if(!CommonUtils.StringIsEmpty(notificationsResponse.getDateRead())){
                mActivityNotificationDetailsBinding.dateReadTitle.setVisibility(View.VISIBLE);
                mActivityNotificationDetailsBinding.dateRead.setVisibility(View.VISIBLE);
                mActivityNotificationDetailsBinding.dateRead.setText(notificationsResponse.getDateRead());
            }

        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, NotificationDetailsActivity.class);
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
}
