package com.turnkeyafrica.turnkeybankassurance.ui.register.termsandconditionsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


public class TermsAndConditionsDialogViewModel extends BaseViewModel<TermsAndConditionsDialogcallback> {


    public TermsAndConditionsDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
