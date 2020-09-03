package com.turnkeyafrica.turnkeybankassurance.ui.servicerequests;

import android.app.AlertDialog;

import androidx.databinding.ObservableBoolean;
import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


public class ServiceRequestViewModel extends BaseViewModel<ServiceRequestNavigator> {

    public ObservableBoolean requestType = new ObservableBoolean();

    public ServiceRequestViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        requestType.set(true);
    }


    public void getAllServiceRequests(){
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getAllServiceRequest(getDataManager().getCurrentUserId().toString())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(responses -> {
                    getNavigator().setAdapter(responses);
                }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }

    public void newServiceRequest(){
        getNavigator().openServiceRequestQuestion();
    }
}
