package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


public class VehicleInsuranceTypeDialogViewModel extends BaseViewModel<VehicleInsuranceTypecallback> {


    public VehicleInsuranceTypeDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
