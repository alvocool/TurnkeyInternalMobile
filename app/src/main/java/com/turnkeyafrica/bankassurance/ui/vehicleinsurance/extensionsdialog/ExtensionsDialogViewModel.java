package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.extensionsdialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class ExtensionsDialogViewModel extends BaseViewModel<ExtensionsDialogcallback> {


    public ExtensionsDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

    public void setLimit() {
        getNavigator().setLimit();
    }

}
