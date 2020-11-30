package com.turnkeyafrica.bankassurance.ui.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivitySplashBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import javax.inject.Inject;
import androidx.lifecycle.ViewModelProvider;
import nya.security.androidsecurity.SecurityRuns;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator{
    @Inject
    ViewModelProviderFactory factory;
    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = new ViewModelProvider(getViewModelStore(),factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleError(LocalError error) {
        if (!CommonUtils.StringIsEmpty(error.getMessage())) {

            ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> {
                dialog.dismiss();
                System.exit(0);
            });
        }
    }
    
    @Override
    public void openDashBoardActivity() {
        Intent intent = DashboardActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        setStatusBarColor();

    /*    if(!SecurityRuns.isAppRoot1()
                && SecurityRuns.isAppRoot3(getPackageManager())
                && !SecurityRuns.isEmulator()) {*/
            mSplashViewModel.LaunchNextActivity();
      /*  }else {
            shutDownProtocal();
        }*/

    }

    private void setStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }}
}
