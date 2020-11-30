package com.turnkeyafrica.bankassurance.ui.resetpassword;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import javax.inject.Inject;

import com.turnkeyafrica.bankassurance.data.model.api.CredentialsUpdate;
import com.turnkeyafrica.bankassurance.ui.entersecurityquestions.EnterSecurityQuestionsActivity;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ClientRegistrationRequest;
import com.turnkeyafrica.bankassurance.databinding.ActivityResetPasswordBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.successstep.SuccessStepActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

public class ResetPasswordActivity extends BaseActivity<ActivityResetPasswordBinding, ResetPasswordViewModel> implements ResetPasswordNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ResetPasswordViewModel mResetPasswordViewModel;
    ActivityResetPasswordBinding mActivityResetPasswordBinding;

    private EditText Password;

    private EditText ConfirmPassword;

    ClientRegistrationRequest requestRegister;

    String phone;
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    public ResetPasswordViewModel getViewModel() {

        mResetPasswordViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(ResetPasswordViewModel.class);

        return mResetPasswordViewModel;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, ResetPasswordActivity.class);
    }

  
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityResetPasswordBinding = getViewDataBinding();
        mResetPasswordViewModel.setNavigator(this);
        Toolbar toolbar = mActivityResetPasswordBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initialiseViews();
        getData();
    }

    private void getData() {

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        String fromRegister = extras.getString("p967^h@2");

        phone = extras.getString("p90hh!");

        requestRegister = new Gson().fromJson(fromRegister, ClientRegistrationRequest.class);

        if(!CommonUtils.StringIsEmpty(fromRegister)){
            mActivityResetPasswordBinding.stepTitle.setText(getResources().getText(R.string.step_2_of_3));
            mResetPasswordViewModel.title.set(getResources().getString(R.string.createPasswordTitle));
            mActivityResetPasswordBinding.turnkeyBar.setVisibility(View.GONE);
            mActivityResetPasswordBinding.turnkeyBar2.setVisibility(View.VISIBLE);
            mActivityResetPasswordBinding.Continue.setText(getResources().getString(R.string.proceed));
        }else{
            mActivityResetPasswordBinding.turnkeyBar2.setVisibility(View.GONE);
            mActivityResetPasswordBinding.turnkeyBar.setVisibility(View.VISIBLE);
            mActivityResetPasswordBinding.stepTitle.setText(getResources().getText(R.string.step_3_of_3));
            mResetPasswordViewModel.title.set(getResources().getString(R.string.reset_your_password));
        }

    }

    private void initialiseViews() {

        Password = mActivityResetPasswordBinding.customerPassword;

        Password = disableCopyAndPaste(Password);

        ConfirmPassword = mActivityResetPasswordBinding.customerConfirmPassword;

        ConfirmPassword = disableCopyAndPaste(ConfirmPassword);
    }

    private boolean verifyInputs(){

        TextInputLayout customerPassword = mActivityResetPasswordBinding.customerPasswordInputLayout;

        TextInputLayout confirmCustomerPassword = mActivityResetPasswordBinding.customerConfirmPasswordInputLayout;

        boolean state = true;

        customerPassword.setError(null);

        confirmCustomerPassword.setError(null);

        if (CommonUtils.StringIsEmpty(Password.getText().toString())) {
            customerPassword.setError(getResources().getString(R.string.error_password));
            state = false;
        }
        if (CommonUtils.StringIsEmpty(ConfirmPassword.getText().toString())) {
            confirmCustomerPassword.setError(getResources().getString(R.string.error_confirm_password));
            state = false;
        }else if (!CommonUtils.StringIsEqual(Password.getText().toString(),ConfirmPassword.getText().toString())) {
            customerPassword.setError(getResources().getString(R.string.error_password_mismatch));
            confirmCustomerPassword.setError(getResources().getString(R.string.error_password_mismatch));
            state = false;
        }else if (CommonUtils.PasswordStrength(ConfirmPassword.getText().toString())) {
            customerPassword.setError(getResources().getString(R.string.error_password_strength));
            confirmCustomerPassword.setError(getResources().getString(R.string.error_password_strength));
            state = false;
        }

        return state;
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
    public void setPassword() {

        if(verifyInputs()){
            if(CommonUtils.ObjectIsNotNull(requestRegister)){
                requestRegister.setClntPassword(Password.getText().toString());
                Intent intent = EnterSecurityQuestionsActivity.newIntent(ResetPasswordActivity.this);
                intent.putExtra("p8*7^h@9", new Gson().toJson(requestRegister));
                startActivity(intent);
            }else{
                mResetPasswordViewModel.updatePassword(new CredentialsUpdate(phone,Password.getText().toString(),
                        mResetPasswordViewModel.getDataManager().getUUID()));
            }
        }

    }

    @Override
    public void success(Boolean response) {

        Intent intent = SuccessStepActivity.newIntent(ResetPasswordActivity.this);
        intent.putExtra("p^7Ow@", 2);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

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