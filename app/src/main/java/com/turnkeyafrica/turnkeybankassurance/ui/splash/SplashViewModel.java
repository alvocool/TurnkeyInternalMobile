package com.turnkeyafrica.turnkeybankassurance.ui.splash;

import android.os.Handler;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.AppUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

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

    public void isUnique() {
        if (CommonUtils.StringIsEmpty(getDataManager().getUUID())) {
            getDataManager().setUUID(AppUtils.generateUuid());
        }
    }
}
