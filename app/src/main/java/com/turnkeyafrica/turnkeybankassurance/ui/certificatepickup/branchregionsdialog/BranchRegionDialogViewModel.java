package com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.branchregionsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


public class BranchRegionDialogViewModel extends BaseViewModel<BranchRegionDialogcallback> {


    public BranchRegionDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
