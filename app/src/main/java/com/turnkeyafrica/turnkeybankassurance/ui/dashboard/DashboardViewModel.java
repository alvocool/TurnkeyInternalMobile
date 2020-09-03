package com.turnkeyafrica.turnkeybankassurance.ui.dashboard;


import android.app.AlertDialog;
import android.text.TextUtils;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

import androidx.databinding.ObservableField;

public class DashboardViewModel extends BaseViewModel<DashboardNavigator> {

    public DashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    private final ObservableField<String> appVersion = new ObservableField<>();

    private final ObservableField<String> userNumber = new ObservableField<>();

    private final ObservableField<String> userName = new ObservableField<>();

    public ObservableField<String> getAppVersion() {
        return appVersion;
    }

    public ObservableField<String> getUserEmail() {
        return userNumber;
    }

    public ObservableField<String> getUserName() {
        return userName;
    }


    public void logout() {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager().doLogoutApiCall()
                .doOnSuccess(response -> getDataManager().setUserAsLoggedOut())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(response -> {
                    getDataManager().setUserAsLoggedOut();
                    getNavigator().openLoginActivity();
                }, throwable -> {
                    getDataManager().setUserAsLoggedOut();
                    getNavigator().openLoginActivity();
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void onNavMenuCreated() {
        final String currentUserName = getDataManager().getCurrentUserName();
        if (!TextUtils.isEmpty(currentUserName)) {
            userName.set(currentUserName);
        }

        final String currentUserNumber = getDataManager().getCurrentUserMobileNumber();
        if (!TextUtils.isEmpty(currentUserNumber)) {
            userNumber.set(currentUserNumber);
        }

    }

    public void updateAppVersion(String version) {
        appVersion.set(version);
    }

    public void viewProfile(){ getNavigator().viewProfile();}

    public void sendNotificationId(String token) {

        getCompositeDisposable().add(getDataManager()
                .sendNotificationID(token,getDataManager().getUUID(),getDataManager().getCurrentUserId().toString())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getDataManager().setNotificationToken(true);
                    if(!response){
                        getNavigator().handleError(new LocalError(0,"Failed to register device for notifications."));
                    }
                }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable)))
        );
    }

    public void getNotificationCount() {

        getCompositeDisposable().add(getDataManager()
                .getAllNotifications(getDataManager().getCurrentUserId().toString(),"U")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responses -> getNavigator().setNotificationCount(responses.size()), throwable -> getNavigator().setNotificationCount(0)));

    }
}