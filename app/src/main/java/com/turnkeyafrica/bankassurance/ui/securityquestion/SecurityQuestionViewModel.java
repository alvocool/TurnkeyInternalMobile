package com.turnkeyafrica.bankassurance.ui.securityquestion;

import android.app.AlertDialog;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.AnswerVerify;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class SecurityQuestionViewModel extends BaseViewModel<SecurityQuestionNavigator> {
    public SecurityQuestionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void verify(){
        getNavigator().verify();
    }

    public void forgotQuestion(){
        getNavigator().showContacts();
    }


    public void verifyAnswer(AnswerVerify answerVerify) {

        AlertDialog alertDialog = getNavigator().openLoading();

        getCompositeDisposable().add(getDataManager()
                .getVerifyAnswer(answerVerify)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().Continue(response);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }
}
