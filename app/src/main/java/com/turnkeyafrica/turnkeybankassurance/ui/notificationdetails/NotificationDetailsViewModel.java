package com.turnkeyafrica.turnkeybankassurance.ui.notificationdetails;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.NotificationsResponse;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class NotificationDetailsViewModel extends BaseViewModel<NotificationDetailsNavigator> {
    public NotificationDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setNotificationRead(NotificationsResponse notificationRead) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .updateNotification(notificationRead)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(responses -> {
                }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }
}
