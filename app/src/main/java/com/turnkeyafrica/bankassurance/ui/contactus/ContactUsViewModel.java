package com.turnkeyafrica.bankassurance.ui.contactus;

import androidx.databinding.ObservableField;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class ContactUsViewModel extends BaseViewModel<ContactUsNavigator> {

    public ObservableField<String> mainContact = new ObservableField<>();
    public ObservableField<String> cellOne = new ObservableField<>();
    public ObservableField<String> cellTwo = new ObservableField<>();

    public ContactUsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
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
