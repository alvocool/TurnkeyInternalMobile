package com.turnkeyafrica.bankassurance.ui.register.termsandconditionsdialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class TermsAndConditionsDialogViewModel extends BaseViewModel<TermsAndConditionsDialogcallback> {


    public TermsAndConditionsDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
