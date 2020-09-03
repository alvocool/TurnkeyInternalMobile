
package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class PolicyViewModel extends BaseViewModel<PolicyNavigator> {

    public PolicyViewModel(DataManager dataManager,
                           SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getInsured(){

        getNavigator().getInsured();
    }

    private void getQuotes(BigDecimal clientID, List<PolicyResponce> policyResponces) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientsQuotes(clientID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(quotesResponse -> {
                    if(quotesResponse != null || policyResponces != null) {
                        if(quotesResponse != null) {

                            getNavigator().setAdapter(policyResponces,quotesResponse);
                        }else {

                            getNavigator().setAdapter(policyResponces,new ArrayList<>());
                        }
                    }
                }, throwable -> {
                    getNavigator().setAdapter(policyResponces,new ArrayList<>());
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void getPolicies(BigDecimal clientID) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientsPolicies(clientID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(policiesResponse -> {
                    if (policiesResponse != null) {
                        getQuotes(clientID, policiesResponse);
                    }else {
                        getQuotes(clientID, new ArrayList<>());
                    }
                }, throwable -> {
                    getQuotes(clientID,new ArrayList<>());
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }
}
