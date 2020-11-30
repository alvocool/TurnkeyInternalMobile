package com.turnkeyafrica.bankassurance.ui.resetpassword;

import android.app.AlertDialog;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.CredentialsUpdate;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class ResetPasswordViewModel extends BaseViewModel<ResetPasswordNavigator> {

   public ObservableField<String> title = new ObservableField<>();

    public ResetPasswordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void resetPassword(){
        getNavigator().setPassword();
    }

    public void updatePassword(CredentialsUpdate credentialsUpdate) {
        AlertDialog alertDialog = getNavigator().openLoading();

        getCompositeDisposable().add(getDataManager()
                .updateCredentials(credentialsUpdate)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response) {
                        getNavigator().closeLoading(alertDialog);
                        getNavigator().success(response);
                    }
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }
}
