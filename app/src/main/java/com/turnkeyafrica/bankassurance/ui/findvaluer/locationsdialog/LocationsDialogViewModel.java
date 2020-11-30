package com.turnkeyafrica.bankassurance.ui.findvaluer.locationsdialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class LocationsDialogViewModel extends BaseViewModel<LocationsDialogcallback> {


    public LocationsDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
