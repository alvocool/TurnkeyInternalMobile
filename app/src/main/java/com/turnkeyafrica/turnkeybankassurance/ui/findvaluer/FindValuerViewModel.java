package com.turnkeyafrica.turnkeybankassurance.ui.findvaluer;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class FindValuerViewModel extends BaseViewModel<FindValuatorNavigator> {

    public FindValuerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

    public void getAllLocations() {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getLocations()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responce -> {
                    if (responce != null) {
                        getNavigator().setLocations(responce);
                    }
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void getValuersByLocation(String location) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getValuers(location)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(valuersResponces -> {
                    if (valuersResponces != null) {
                        getNavigator().setValuers(valuersResponces);
                    }
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void selectValuator(){
        getNavigator().loadLocations();
    }

}
