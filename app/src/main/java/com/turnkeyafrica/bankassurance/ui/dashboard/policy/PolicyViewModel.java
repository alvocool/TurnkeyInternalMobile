
package com.turnkeyafrica.bankassurance.ui.dashboard.policy;

import android.app.AlertDialog;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.DataWrapper;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;
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

    private void getQuotes( List<PolicyResponce> policyResponses) {

        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientsQuotes()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(quotesResponse -> {
                    if(quotesResponse != null || policyResponses != null) {
                        if(quotesResponse != null) {

                            getNavigator().setAdapter(policyResponses,quotesResponse);
                        }else {

                            getNavigator().setAdapter(policyResponses,new ArrayList<>());
                        }
                    }
                }, throwable -> {
                    getNavigator().setAdapter(policyResponses,new ArrayList<>());
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void getPolicies(DataWrapper dataWrapper) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientsPolicies(dataWrapper)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(policiesResponse -> {
                    if (policiesResponse != null) {
                        getQuotes(policiesResponse);
                    }else {
                        getQuotes(new ArrayList<>());
                    }
                }, throwable -> {
                    getQuotes(new ArrayList<>());
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }
}
