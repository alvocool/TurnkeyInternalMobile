package com.turnkeyafrica.turnkeybankassurance.ui.newrequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityNewRequestQuestionBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.createrequest.CreateRequestActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.enterrequestdetails.EnterServiceRequestDetailsActivity;

import javax.inject.Inject;

public class NewRequestQuestionActivity extends BaseActivity<ActivityNewRequestQuestionBinding,NewRequestQuestionViewModel> implements NewRequestQuestionNavigator {

    @Inject
    ViewModelProviderFactory factory;
    NewRequestQuestionViewModel mNewRequestQuestionViewModel;

    ActivityNewRequestQuestionBinding mActivityNewRequestQuestionBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, NewRequestQuestionActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_request_question;
    }

    @Override
    public NewRequestQuestionViewModel getViewModel() {

        mNewRequestQuestionViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(NewRequestQuestionViewModel.class);

        return mNewRequestQuestionViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityNewRequestQuestionBinding = getViewDataBinding();
        mNewRequestQuestionViewModel.setNavigator(this);
        Toolbar toolbar = mActivityNewRequestQuestionBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void openDetailsScreen() {
        Intent intent = EnterServiceRequestDetailsActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void openPolicyScreen() {
        Intent intent = CreateRequestActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }
}
