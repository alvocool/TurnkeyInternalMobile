package com.turnkeyafrica.bankassurance.ui.certificatepickup.branchregionsdialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class BranchRegionDialogViewModel extends BaseViewModel<BranchRegionDialogcallback> {


    public BranchRegionDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
