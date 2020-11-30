package com.turnkeyafrica.bankassurance.ui.newrequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.databinding.ActivityNewRequestQuestionBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.createrequest.CreateRequestActivity;
import com.turnkeyafrica.bankassurance.ui.enterrequestdetails.EnterServiceRequestDetailsActivity;

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
