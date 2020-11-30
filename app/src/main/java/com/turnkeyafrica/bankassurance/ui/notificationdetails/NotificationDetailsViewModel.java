package com.turnkeyafrica.bankassurance.ui.notificationdetails;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.DataWrapper;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class NotificationDetailsViewModel extends BaseViewModel<NotificationDetailsNavigator> {
    public NotificationDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setNotificationRead(DataWrapper dataWrapper) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .updateNotification(dataWrapper)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(responses -> {
                }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }
}
