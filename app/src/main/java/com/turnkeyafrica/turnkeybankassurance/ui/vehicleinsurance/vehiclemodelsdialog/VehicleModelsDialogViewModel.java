package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehiclemodelsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


public class VehicleModelsDialogViewModel extends BaseViewModel<VehicleModelsDialogcallback> {


    public VehicleModelsDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
