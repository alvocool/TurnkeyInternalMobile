package com.turnkeyafrica.bankassurance.ui.newrequest;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class NewRequestQuestionViewModel extends BaseViewModel<NewRequestQuestionNavigator> {

    public NewRequestQuestionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void enterDetails(){
        getNavigator().openDetailsScreen();
    }

    public void selectPolicy(){
        getNavigator().openPolicyScreen();
    }
}
