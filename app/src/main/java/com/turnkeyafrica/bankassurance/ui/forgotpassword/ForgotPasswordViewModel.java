package com.turnkeyafrica.bankassurance.ui.forgotpassword;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.DataWrapper;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class ForgotPasswordViewModel extends BaseViewModel<ForgotPasswordNavigator> {
    public ForgotPasswordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void proceed(){
        getNavigator().proceed();
    }

    public void getSecurityQuestion(String phone) {

        AlertDialog alertDialog = getNavigator().openLoading();

        getCompositeDisposable().add(getDataManager()
                .getQuestionByMobile(new DataWrapper(phone))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().setQuestion(response);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }
}
