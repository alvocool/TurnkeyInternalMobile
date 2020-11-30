package com.turnkeyafrica.bankassurance.ui.register;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class RegisterViewModel extends BaseViewModel<RegisterNavigator> {

    public RegisterViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

  public void Next(){
        getNavigator().registerCustomer();
  }

  public void showTermsAndConditions(){
        getNavigator().showTermsAndConditions();
  }
}
