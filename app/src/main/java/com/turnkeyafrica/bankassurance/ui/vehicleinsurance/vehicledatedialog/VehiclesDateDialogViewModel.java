package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicledatedialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class VehiclesDateDialogViewModel extends BaseViewModel<VehiclesDateDialogcallback> {


    public VehiclesDateDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

    public void setDate() {
        getNavigator().setDate();
    }
}
