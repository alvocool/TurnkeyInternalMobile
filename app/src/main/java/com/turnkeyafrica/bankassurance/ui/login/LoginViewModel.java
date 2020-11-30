package com.turnkeyafrica.bankassurance.ui.login;


import android.app.AlertDialog;

import com.microsoft.appcenter.analytics.Analytics;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.LoginUserRequest;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void registerNew(){getNavigator().registerNew();}

    public void LoginClick() {
        getNavigator().launchOtp();
    }

    public void forgotPassword(){
        getNavigator().forgotPassword();
    }

    public void requestCode(LoginUserRequest request){
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .requestOTP(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(response -> {
                    if(!CommonUtils.StringIsEmpty(response.getValue())){
                        getDataManager().setUUID(response.getValue());
                    }
                    getDataManager().updateProtectedLoginApiHeader(getDataManager().getUUID());
                    getDataManager().updateApiHeader("","", getDataManager().getUUID());
                    Analytics.trackEvent("Login requested "+ request.getUsername());
                    getNavigator().openOtp(request);

                }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));

    }

}
