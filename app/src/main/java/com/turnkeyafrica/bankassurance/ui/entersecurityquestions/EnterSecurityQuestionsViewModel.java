package com.turnkeyafrica.bankassurance.ui.entersecurityquestions;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ClientRegistrationRequest;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class EnterSecurityQuestionsViewModel extends BaseViewModel<EnterSecurityQuestionsNavigator> {
    public EnterSecurityQuestionsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void Register(){
        getNavigator().register();
    }

    public void ShowQuestions(int option){
        getNavigator().setOpinion(option);
    }

      public void sendDetails(ClientRegistrationRequest clientRegistrationDetails,
                              String device){

          AlertDialog alertDialog = getNavigator().openLoading();
          getCompositeDisposable().add(getDataManager()
                  .sendRegDetails(clientRegistrationDetails, device)
                  .subscribeOn(getSchedulerProvider().io())
                  .observeOn(getSchedulerProvider().ui())
                  .doFinally(() -> getNavigator().closeLoading(alertDialog))
                  .subscribe(response -> {
                      if (response != null) {
                          getDataManager().setNotificationToken(false);
                          getDataManager().setUUID(response.getResult());
                          getDataManager().updateProtectedLoginApiHeader(getDataManager().getUUID());
                          getDataManager().updateApiHeader("","", getDataManager().getUUID());
                          getNavigator().openSuccess(1);
                      }
                  }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable)))
          );
      }

    public void getAllQuestions(){

        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getAllQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(response -> {
                   getNavigator().setQuestions(response);
                }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable)))
        );
    }
}
