package com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.locationsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


public class LocationsDialogViewModel extends BaseViewModel<LocationsDialogcallback> {


    public LocationsDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
