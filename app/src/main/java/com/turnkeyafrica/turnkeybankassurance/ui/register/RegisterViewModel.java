package com.turnkeyafrica.turnkeybankassurance.ui.register;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientRegistrationRequest;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class RegisterViewModel extends BaseViewModel<RegisterNavigator> {

    public RegisterViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

  public void Register(){
        getNavigator().registerCustomer();
  }

  public void sendDetails(ClientRegistrationRequest clientRegistrationDetails){

      AlertDialog alertDialog = getNavigator().openLoading();
      getCompositeDisposable().add(getDataManager()
              .sendRegDetails(clientRegistrationDetails)
              .subscribeOn(getSchedulerProvider().io())
              .observeOn(getSchedulerProvider().ui())
              .doFinally(() -> getNavigator().closeLoading(alertDialog))
              .subscribe(response -> {
                  if (response != null) {
                      getDataManager().setNotificationToken(false);
                      getNavigator().launchOtp(clientRegistrationDetails.getClntMobileNo());
                  }
              }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable)))
      );
  }

  public void showTermsAndConditions(){
        getNavigator().showTermsAndConditions();
  }
}
