package com.turnkeyafrica.turnkeybankassurance.ui.inbox;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class InboxViewModel extends BaseViewModel<InboxNavigator> {

    public InboxViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getAllNotifications(){
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getAllNotifications(getDataManager().getCurrentUserId().toString(),"A")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(responses -> getNavigator().setAdapter(responses), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }

}
