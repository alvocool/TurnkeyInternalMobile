package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicledatedialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


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
