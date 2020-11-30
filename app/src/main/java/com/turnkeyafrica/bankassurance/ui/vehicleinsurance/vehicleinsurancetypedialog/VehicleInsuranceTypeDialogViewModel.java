package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class VehicleInsuranceTypeDialogViewModel extends BaseViewModel<VehicleInsuranceTypecallback> {


    public VehicleInsuranceTypeDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
