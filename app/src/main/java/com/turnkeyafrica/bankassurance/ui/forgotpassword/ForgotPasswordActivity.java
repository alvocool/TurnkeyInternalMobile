package com.turnkeyafrica.bankassurance.ui.forgotpassword;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import javax.inject.Inject;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.SecurityQuestions;
import com.turnkeyafrica.bankassurance.databinding.ActivityForgotPasswordBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.securityquestion.SecurityQuestionActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

public class ForgotPasswordActivity extends BaseActivity<ActivityForgotPasswordBinding, ForgotPasswordViewModel> implements ForgotPasswordNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ForgotPasswordViewModel mForgotPasswordViewModel;
    ActivityForgotPasswordBinding mActivityForgotPasswordBinding;

    EditText phoneNumber;
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    public ForgotPasswordViewModel getViewModel() {

        mForgotPasswordViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(ForgotPasswordViewModel.class);

        return mForgotPasswordViewModel;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, ForgotPasswordActivity.class);
    }

  
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityForgotPasswordBinding = getViewDataBinding();
        mForgotPasswordViewModel.setNavigator(this);
        Toolbar toolbar = mActivityForgotPasswordBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        phoneNumber = mActivityForgotPasswordBinding.etUserNumber;

        phoneNumber = disableCopyAndPaste(phoneNumber);
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
    public void proceed() {
        if(verifyDetail()){
           mForgotPasswordViewModel.getSecurityQuestion(phoneNumber.getText().toString());
        }
    }

    @Override
    public void setQuestion(SecurityQuestions response) {
        Intent intent = SecurityQuestionActivity.newIntent(ForgotPasswordActivity.this);
        intent.putExtra("Mon@45@1", new Gson().toJson(response));
        intent.putExtra("q3;AS!", phoneNumber.getText().toString());
        startActivity(intent);
    }

    private boolean verifyDetail() {

        TextInputLayout textInputLayout = mActivityForgotPasswordBinding.userNumberInputLayout;

        textInputLayout.setError(null);

        if(CommonUtils.StringIsEmpty(phoneNumber.getText().toString())){
            textInputLayout.setError(getResources().getText(R.string.error_mobile_no));
            return false;
        } if (!CommonUtils.mobileLength(phoneNumber.getText().toString())) {
            textInputLayout.setError(getResources().getString(R.string.error_invalid_mobile));
            return false;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

}