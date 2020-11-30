package com.turnkeyafrica.bankassurance.ui.login;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.microsoft.appcenter.analytics.Analytics;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.LoginUserRequest;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityLoginBinding;
import com.turnkeyafrica.bankassurance.ui.accountlocked.AccountLockedActivity;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.forgotpassword.ForgotPasswordActivity;
import com.turnkeyafrica.bankassurance.ui.otp.OtpActivity;
import com.turnkeyafrica.bankassurance.ui.register.RegisterActivity;
import com.turnkeyafrica.bankassurance.ui.register.termsandconditionsdialog.TermsAndConditionsDialog;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    EditText mobileNumber;

    EditText password;

    String[] loginPermissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };


    private static final int PERMISSIONS_REQUEST_CODE = 1996;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    public void launchOtp() {
        if (checkAndRequestPermissions(loginPermissions,PERMISSIONS_REQUEST_CODE)) {
             if(verifyDetails()) {
                if(isNetworkConnected()) {
                    mLoginViewModel.requestCode(new LoginUserRequest(mobileNumber.getText().toString(), password.getText().toString()));
                }
            }
        }
    }

    private boolean verifyDetails() {

        boolean state = true;

        TextInputLayout mobileNumberTextInputEditText = mActivityLoginBinding.userNumberInputLayout;

        TextInputLayout passwordTextInputEditText = mActivityLoginBinding.userPasswordInputLayout;

        mobileNumberTextInputEditText.setError(null);

        passwordTextInputEditText.setError(null);

        if (CommonUtils.StringIsEmpty(mobileNumber.getText().toString())) {
            mobileNumberTextInputEditText.setError(getResources().getString(R.string.error_mobile_no));
            state = false;
        }
        if (!CommonUtils.mobileLength(mobileNumber.getText().toString())) {
            mobileNumberTextInputEditText.setError(getResources().getString(R.string.error_invalid_mobile));
            state = false;
        }
        if(CommonUtils.StringIsEmpty(password.getText().toString())) {
            passwordTextInputEditText.setError(getResources().getString(R.string.error_password));
            state = false;
        }

        return state;
    }

    @Override
    public void handleError(LocalError error) {
        if (!CommonUtils.StringIsEmpty(error.getMessage())) {
            if(CommonUtils.StringIsEqual(error.getMessage(),"1000")){
                Intent intent = AccountLockedActivity.newIntent(this);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }else {
                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
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
    public void registerNew() {
        Analytics.trackEvent("Register User Clicked");
        if (checkAndRequestPermissions(loginPermissions,PERMISSIONS_REQUEST_CODE)) {
            Intent intent = RegisterActivity.newIntent(LoginActivity.this);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);
        isNetworkConnected();
        checkAndRequestPermissions(loginPermissions,PERMISSIONS_REQUEST_CODE);

         mobileNumber = mActivityLoginBinding.etUserNumber;

         password = mActivityLoginBinding.etPassword;

         mobileNumber = disableCopyAndPaste(mobileNumber);

         password = disableCopyAndPaste(password);
    }

    @Override
    public void showTermsAndConditions() {
        TermsAndConditionsDialog.newInstance().show(getSupportFragmentManager());
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
    public void openOtp(LoginUserRequest request) {
        Intent intent = OtpActivity.newIntent(LoginActivity.this);
        intent.putExtra("f02K@1", new Gson().toJson(request));
        startActivity(intent);
    }

    @Override
    public void forgotPassword() {
        Intent intent = ForgotPasswordActivity.newIntent(LoginActivity.this);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (requestCode == PERMISSIONS_REQUEST_CODE) {

            HashMap<String, Integer> permissionsResults = new HashMap<>();
            int deniedCount = 0;

            for (int i = 0; i < grantResults.length; i++) {

                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    permissionsResults.put(permissions[i], grantResults[i]);
                    deniedCount++;
                }
            }

        if (deniedCount != 0) {

            for (Map.Entry<String, Integer> entry : permissionsResults.entrySet()) {
                String permName = entry.getKey();
                int permResult = entry.getValue();

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permName)) {

                    ViewUtils.showDialog(this, getResources().getString(R.string.permissionsTitle), getResources().getString(R.string.AcceptPermissionsInfo), getResources().getString(R.string.ok),
                            (dialog, which) -> {
                                dialog.dismiss();
                                checkAndRequestPermissions(loginPermissions,PERMISSIONS_REQUEST_CODE);
                            });
                    break;
                } else {

                    ViewUtils.showDialog(this, getResources().getString(R.string.permissionsTitle), getResources().getString(R.string.AcceptPermissionsInfo2), getResources().getString(R.string.navigate_to_settings),
                            (dialog, which) -> {
                                dialog.dismiss();

                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            });
                    break;
                }
            }
            }

        }
    }

    @Override
    public void onBackPressed() {

    }

}
