package com.turnkeyafrica.bankassurance.ui.splash;

import android.os.Handler;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.AppUtils;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void LaunchNextActivity() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(LaunchTask, 3000);
    }

    private Runnable LaunchTask = this::decideNextActivity;

    private void decideNextActivity() {

        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getNavigator().openLoginActivity();
        } else {
            getNavigator().openDashBoardActivity();
        }

    }
}
