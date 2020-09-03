package com.turnkeyafrica.turnkeybankassurance.ui.editprofile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientDetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientDetailsUpdateRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityEditProfileBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.otp.OtpActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding, EditProfileViewModel> implements EditProfileNavigator {


    private EditText Surname;

    private EditText FirstName;

    private EditText MobileNo;

    private EditText EmailAddress;

    @Inject
    ViewModelProviderFactory factory;
    private EditProfileViewModel mEditProfileViewModel;


    public static Intent newIntent(Context context) {
        return new Intent(context, EditProfileActivity.class);
    }

    private ActivityEditProfileBinding mActivityEditProfileBinding;

    @Override
    public int getBindingVariable() {
            return BR.viewModel;
            }

    @Override
    public int getLayoutId() {
            return R.layout.activity_edit_profile;
            }

    @Override
    public EditProfileViewModel getViewModel() {
            mEditProfileViewModel = new ViewModelProvider(getViewModelStore(),factory).get(EditProfileViewModel.class);
            return mEditProfileViewModel;
            }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mEditProfileViewModel.setNavigator(this);
            mActivityEditProfileBinding = getViewDataBinding();
            Toolbar toolbar = mActivityEditProfileBinding.toolbar;
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

            initComponents();
            setUserEditDetails();

      }

    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
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
    }@Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    @Override
    public void updateProfile() {
        if(isNetworkConnected() ){
            if(verifyInput()) {

                ClientDetailsUpdateRequest clientDetailsUpdateRequest = new ClientDetailsUpdateRequest(getViewModel().getDataManager().getCurrentUserId(),
                        EmailAddress.getText().toString(),MobileNo.getText().toString(),FirstName.getText().toString(),Surname.getText().toString());

                Gson gson = new Gson();

                String data = gson.toJson(clientDetailsUpdateRequest);

                Intent intent = OtpActivity.newIntent(EditProfileActivity.this);
                intent.putExtra("f02K@1", getViewModel().clientNumber.get());
                intent.putExtra("Cle2iat", data);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }

    }

    private void initComponents() {

        Surname = mActivityEditProfileBinding.customerEditSecondName;

        FirstName = mActivityEditProfileBinding.customerEditFirstName;

        MobileNo = mActivityEditProfileBinding.customerEditMobileNo;

        EmailAddress = mActivityEditProfileBinding.customerEditEmail;

    }

    private boolean verifyInput() {

        TextInputLayout customerFirstName = mActivityEditProfileBinding.customerFirstNameEditInputLayout;

        TextInputLayout customerSurname = mActivityEditProfileBinding.customerSecondNameEditInputLayout;

        TextInputLayout customerMobileNo = mActivityEditProfileBinding.customerMobileNoEditInputLayout;

        TextInputLayout customerEmailAddress = mActivityEditProfileBinding.customerEmailEditInputLayout;

        customerSurname.setError(null);
        customerFirstName.setError(null);
        customerMobileNo.setError(null);
        customerEmailAddress.setError(null);

        if (CommonUtils.StringIsEmpty(FirstName.getText().toString())) {
            customerFirstName.setError(getResources().getString(R.string.error_first_name));
            return false;
        }
        else if (CommonUtils.StringIsEmpty(Surname.getText().toString())) {
            customerSurname.setError(getResources().getString(R.string.error_surname));
            return false;
        }
        else if (CommonUtils.StringIsEmpty(MobileNo.getText().toString())) {
            customerMobileNo.setError(getResources().getString(R.string.error_mobile_no));
            return false;
        }
        else if (CommonUtils.StringIsEmpty(EmailAddress.getText().toString())) {
            customerEmailAddress.setError(getResources().getString(R.string.error_email_address));
            return false;
        }
        else if (CommonUtils.isEmailValid(EmailAddress.getText().toString())) {
            customerEmailAddress.setError(getResources().getString(R.string.error_invalid_email_address));
            return false;
        }

        return true;
    }

    private void setUserEditDetails(){

        Bundle extras = getIntent().getExtras();
        String details;

        if (extras != null) {
            details = extras.getString("MyDetails$%^56");
            ClientDetailsResponce clientDetailsResponce = new Gson().fromJson(details, ClientDetailsResponce.class);

            mEditProfileViewModel.setProfileDetails(clientDetailsResponce);
        }
    }
}

