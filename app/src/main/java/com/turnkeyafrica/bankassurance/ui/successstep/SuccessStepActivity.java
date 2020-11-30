package com.turnkeyafrica.bankassurance.ui.successstep;

import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import javax.inject.Inject;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.databinding.ActivitySuccessStepBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

public class SuccessStepActivity extends BaseActivity<ActivitySuccessStepBinding, SuccessStepViewModel> implements SuccessStepNavigator {

    @Inject
    ViewModelProviderFactory factory;
    SuccessStepViewModel mSuccessStepViewModel;
    ActivitySuccessStepBinding mActivitySuccessStepBinding;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_success_step;
    }

    @Override
    public SuccessStepViewModel getViewModel() {

        mSuccessStepViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(SuccessStepViewModel.class);

        return mSuccessStepViewModel;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, SuccessStepActivity.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySuccessStepBinding = getViewDataBinding();
        mSuccessStepViewModel.setNavigator(this);

        setDescription();
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
    public void login() {
        Intent intent = LoginActivity.newIntent(getApplicationContext());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void setDescription() {

        Bundle extras = getIntent().getExtras();

        int stage = extras.getInt("p^7Ow@");

        if(stage == 1){
            mSuccessStepViewModel.title.set(getResources().getString(R.string.you_ve_successfully));
            mSuccessStepViewModel.subTitle.set(getResources().getString(R.string.registered));
        }else{
            mSuccessStepViewModel.title.set(getResources().getString(R.string.passwordReset));
            mSuccessStepViewModel.subTitle.set(getResources().getString(R.string.successful));
        }

    }

    @Override
    public void onBackPressed() {

    }
}