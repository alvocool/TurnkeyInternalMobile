package com.turnkeyafrica.bankassurance.ui.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ClientDetailsResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityProfileBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.editprofile.EditProfileActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding, ProfileViewModel> implements ProfileNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private ProfileViewModel mProfileViewModel;
   private  ActivityProfileBinding mActivityProfileBinding;

    private ClientDetailsResponce clientDetailsResponce;

    EditText FirstName;

    EditText Surname;

    EditText MobileNumber;

    EditText Email;

    EditText MemberSince;

    public static Intent newIntent(Context context) {
        return new Intent(context, ProfileActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    public ProfileViewModel getViewModel() {
        mProfileViewModel = new ViewModelProvider(getViewModelStore(),factory).get(ProfileViewModel.class);
        return mProfileViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProfileViewModel.setNavigator(this);
        mActivityProfileBinding = getViewDataBinding();
        Toolbar toolbar = mActivityProfileBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if(isNetworkConnected()){

            getViewModel().getClientDetails();
        }

        intialiseComponents();
    }

    private void intialiseComponents() {

        FirstName = mActivityProfileBinding.customerEditFirstName;

        FirstName = disableCopyAndPaste(FirstName);

        Surname = mActivityProfileBinding.customerEditSecondName;

        Surname = disableCopyAndPaste(Surname);

        MobileNumber = mActivityProfileBinding.customerEditMobileNo;

        MobileNumber = disableCopyAndPaste(MobileNumber);

        Email = mActivityProfileBinding.customerEditEmail;

        Email = disableCopyAndPaste(Email);

        MemberSince = mActivityProfileBinding.customerEditMemberSince;

        MemberSince = disableCopyAndPaste(MemberSince);

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
    protected void onRestart() {
        if(isNetworkConnected()){

            getViewModel().getClientDetails();
        }
        super.onRestart();
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
    }@Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    @Override
    public void editProfile() {

        if(CommonUtils.ObjectIsNotNull(this.clientDetailsResponce)) {
            Gson gson = new Gson();
            String clientdetails = gson.toJson(this.clientDetailsResponce);

            Intent intent = EditProfileActivity.newIntent(ProfileActivity.this);
            intent.putExtra("MyDetails$%^56", clientdetails);
            startActivity(intent);
        }
    }

    @Override
    public void setClientDetails(ClientDetailsResponce profileDetails) {

        this.clientDetailsResponce = profileDetails;

    }

}
          