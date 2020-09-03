package com.turnkeyafrica.turnkeybankassurance.ui.createrequest;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class CreateRequestViewModel extends BaseViewModel<CreateRequestNavigator> implements CreateRequestItemViewModel.CreateClaimItemNavigator {

    public CreateRequestViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getActivePolicies() {

        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientsPolicies(BigDecimal.valueOf(getDataManager().getCurrentUserId()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .toObservable()
                .flatMap((Function<List<PolicyResponce>, ObservableSource<PolicyResponce>>) Observable::fromIterable)
                .filter(policyResponce -> policyResponce.getCurrentStatus().equalsIgnoreCase("Active"))
                .toList()
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(policiesResponse -> getNavigator().setAdapter(getViewModelList(policiesResponse)), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }

    private List<CreateRequestItemViewModel> getViewModelList(List<PolicyResponce> policyResponses) {

        List<CreateRequestItemViewModel> claimsItemViewModels = new ArrayList<>();

        CreateRequestItemViewModel createRequestItemViewModel;

        for (PolicyResponce policyResponse: policyResponses){

            createRequestItemViewModel =  new CreateRequestItemViewModel(policyResponse,this);

            claimsItemViewModels.add(createRequestItemViewModel);

        }
        return claimsItemViewModels;
    }

    @Override
    public void createRequest(PolicyResponce policyResponce) {

        getNavigator().openCreateRequest(policyResponce);

    }
}
