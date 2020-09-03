package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.extensionsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


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
