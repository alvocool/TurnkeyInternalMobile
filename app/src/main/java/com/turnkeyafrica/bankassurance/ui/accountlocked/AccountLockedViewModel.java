package com.turnkeyafrica.bankassurance.ui.accountlocked;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class AccountLockedViewModel extends BaseViewModel<AccountLockedNavigator> {

    public ObservableField<String> supportOne = new ObservableField<>();

    public AccountLockedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void Login(){
        getNavigator().Login();
    }

    public void getContacts() {

      /*  getCompositeDisposable().add(getDataManager()
                .updateCredentials(credentialsUpdate)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().success(response);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));*/
    }

}
