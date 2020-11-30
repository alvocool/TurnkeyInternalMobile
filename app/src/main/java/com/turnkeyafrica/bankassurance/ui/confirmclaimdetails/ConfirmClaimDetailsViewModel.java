package com.turnkeyafrica.bankassurance.ui.confirmclaimdetails;


import android.app.AlertDialog;

import androidx.databinding.ObservableField;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsDetailsObject;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.data.model.api.SendClaimDetailsResponse;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class ConfirmClaimDetailsViewModel extends BaseViewModel<ConfirmClaimDetailsNavigator> {

    public ObservableField<String> insurer = new ObservableField<>();

    public ObservableField<String> polNo = new ObservableField<>();

    public ObservableField<String> incidentDate = new ObservableField<>();

    public ObservableField<String> vehicle = new ObservableField<>();

    public ObservableField<String> description = new ObservableField<>();

    public ConfirmClaimDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void submitClaim(){
        getNavigator().submitClaim();
    }

    public void setUpDetails(PolicyResponce policyResponce, ClaimsDetailsObject claimsDetailsObject){

        insurer.set(policyResponce.getPolWebBindName());

        polNo.set(policyResponce.getPolicyNo());

        incidentDate.set(claimsDetailsObject.getIncidentDate());

        vehicle.set(claimsDetailsObject.getPropertyId());

        description.set(claimsDetailsObject.getDescription());
    }

    public void submitClaimDetails(SendClaimDetailsResponse response){

            response.setClientCode(getDataManager().getCurrentClientId());

            AlertDialog alertDialog = getNavigator().openLoading();
            getCompositeDisposable().add(getDataManager()
                    .saveClaim(response)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doFinally(()->{getNavigator().closeLoading(alertDialog);})
                    .subscribe(requestTypes -> getNavigator().openDashboard(), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }
}
