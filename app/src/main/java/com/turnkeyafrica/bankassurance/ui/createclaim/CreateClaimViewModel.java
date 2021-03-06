package com.turnkeyafrica.bankassurance.ui.createclaim;


import android.app.AlertDialog;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.DataWrapper;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class CreateClaimViewModel extends BaseViewModel<CreateClaimNavigator> implements CreateClaimItemViewModel.CreateClaimItemNavigator {

    public CreateClaimViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getActivePolicies() {

        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientsPolicies(new DataWrapper(getDataManager().getCurrentClientId()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .toObservable()
                .flatMap((Function<List<PolicyResponce>, ObservableSource<PolicyResponce>>) Observable::fromIterable)
                .filter(policyResponse -> policyResponse.getCurrentStatus().equalsIgnoreCase("Active"))
                .toList()
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(policiesResponse -> getNavigator().setAdapter(getViewModelList(policiesResponse)), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }

    private List<CreateClaimItemViewModel> getViewModelList(List<PolicyResponce> policyResponses) {

        List<CreateClaimItemViewModel> claimsItemViewModels = new ArrayList<>();

        CreateClaimItemViewModel createClaimItemViewModel;

        for (PolicyResponce policyResponse: policyResponses){

            createClaimItemViewModel =  new CreateClaimItemViewModel(policyResponse,this);

            claimsItemViewModels.add(createClaimItemViewModel);

        }
        return claimsItemViewModels;
    }

    @Override
    public void createClaim(PolicyResponce policyResponce) {

        getNavigator().openCreateClaim(policyResponce);

    }
}
