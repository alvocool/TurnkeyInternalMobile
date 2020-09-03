package com.turnkeyafrica.turnkeybankassurance.ui.newrequest;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

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
