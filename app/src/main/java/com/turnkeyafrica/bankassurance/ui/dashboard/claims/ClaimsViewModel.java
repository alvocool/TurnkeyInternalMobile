
package com.turnkeyafrica.bankassurance.ui.dashboard.claims;

import android.app.AlertDialog;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsResponse;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;
import java.util.ArrayList;
import java.util.List;

public class ClaimsViewModel extends BaseViewModel<ClaimsNavigator> implements ClaimsItemViewModel.ClaimsItemListener {

    public ClaimsViewModel(DataManager dataManager,
                           SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void newClaim(){
        getNavigator().createClaim();
    }


    public void getClaims() {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getAllClaims()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(claimsResponse -> {
                    if (claimsResponse != null) {
                        getNavigator().setAdapter(getViewModelList(claimsResponse));
                    }else {
                        getNavigator().setAdapter(new ArrayList<>());
                    }
                }, throwable -> {
                    getNavigator().handleError(ErrorBase.Error(throwable));
                    getNavigator().setAdapter(new ArrayList<>());
                })
        );
    }

    private List<ClaimsItemViewModel> getViewModelList(List<ClaimsResponse> claimsResponseList) {

        List<ClaimsItemViewModel> claimsItemViewModels = new ArrayList<>();

        ClaimsItemViewModel claimsItemViewModel;

        for (ClaimsResponse claimsResponse: claimsResponseList){

            claimsItemViewModel =  new ClaimsItemViewModel(claimsResponse,this);

            claimsItemViewModels.add(claimsItemViewModel);

        }
        return claimsItemViewModels;
    }

    @Override
    public void openClaimsDetails(ClaimsResponse claimsResponse) {
        getNavigator().openClaimsDetails(claimsResponse);
    }
}
