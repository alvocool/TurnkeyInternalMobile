package com.turnkeyafrica.turnkeybankassurance.ui.register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientRegistrationRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityRegisterBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.otp.OtpActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.register.termsandconditionsdialog.TermsAndConditionsDialog;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> implements RegisterNavigator , HasAndroidInjector {


    @Inject
    ViewModelProviderFactory factory;
    private RegisterViewModel mRegisterViewModel;

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    private ActivityRegisterBinding mActivityRegisterBinding;

    private EditText Surname;

    private EditText FirstName;

    private EditText MobileNo;

    private EditText EmailAddress;


    public static Intent newIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterViewModel getViewModel() {

        mRegisterViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(RegisterViewModel.class);

        return mRegisterViewModel;
    }

    @Override
    public void onBackPressed() {
        Intent intent = LoginActivity.newIntent(RegisterActivity.this);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegisterViewModel.setNavigator(this);
        mActivityRegisterBinding = getViewDataBinding();
        Toolbar toolbar = mActivityRegisterBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        isNetworkConnected();
        initComponents();
    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    private void initComponents() {

         Surname = mActivityRegisterBinding.customerSurname;

         FirstName = mActivityRegisterBinding.customerFirstName;

         MobileNo = mActivityRegisterBinding.customerMobileNo;

         EmailAddress = mActivityRegisterBinding.customerEmailAddress;

    }

    @Override
    public void registerCustomer() {

        if(isNetworkConnected()) {
            if (verifyInput()) {
                ClientRegistrationRequest clientRegistrationDetails = new ClientRegistrationRequest(
                        Surname.getText().toString(),
                        FirstName.getText().toString(),
                        MobileNo.getText().toString(),
                        EmailAddress.getText().toString());
                mRegisterViewModel.sendDetails(clientRegistrationDetails);
            }
        }
    }

    @Override
    public void handleError(LocalError error) {

            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
    }

    @Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    @Override
    public void launchOtp(String clntNumber) {
        Intent intent = OtpActivity.newIntent(RegisterActivity.this);
        intent.putExtra("f02K@2", clntNumber);
        startActivity(intent);
        finish();
    }

    @Override
    public void showTermsAndConditions() {
        TermsAndConditionsDialog.newInstance().show(getSupportFragmentManager());
    }

    private boolean verifyInput() {

        boolean state = true;

        TextInputLayout customerFirstName = mActivityRegisterBinding.customerFirstNameInputLayout;

        TextInputLayout customerSurname = mActivityRegisterBinding.customerSurnameInputLayout;

        TextInputLayout customerMobileNo = mActivityRegisterBinding.customerMobileNoInputLayout;

        TextInputLayout customerEmailAddress = mActivityRegisterBinding.customerEmailAddressInputLayout;

        customerSurname.setError(null);
        customerFirstName.setError(null);
        customerMobileNo.setError(null);
        customerEmailAddress.setError(null);

        if (CommonUtils.StringIsEmpty(FirstName.getText().toString())) {
            customerFirstName.setError(getResources().getString(R.string.error_first_name));
             state = false;
        }
        if (CommonUtils.StringIsEmpty(Surname.getText().toString())) {
            customerSurname.setError(getResources().getString(R.string.error_surname));
            state = false;
        }
        if (CommonUtils.StringIsEmpty(MobileNo.getText().toString())) {
            customerMobileNo.setError(getResources().getString(R.string.error_mobile_no));
            state = false;
        }
        if (CommonUtils.StringIsEmpty(EmailAddress.getText().toString())) {
            customerEmailAddress.setError(getResources().getString(R.string.error_email_address));
            state = false;
        }else if (CommonUtils.isEmailValid(EmailAddress.getText().toString())) {
            customerEmailAddress.setError(getResources().getString(R.string.error_invalid_email_address));
            state = false;
        }

        return state;
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}



