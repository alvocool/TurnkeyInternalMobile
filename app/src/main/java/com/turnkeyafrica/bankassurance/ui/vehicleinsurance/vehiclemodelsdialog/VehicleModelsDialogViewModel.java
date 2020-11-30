package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehiclemodelsdialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class VehicleModelsDialogViewModel extends BaseViewModel<VehicleModelsDialogcallback> {


    public VehicleModelsDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
