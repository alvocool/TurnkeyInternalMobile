package com.turnkeyafrica.bankassurance.ui.claimdetails;


import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsResponse;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class ClaimDetailsViewModel extends BaseViewModel<ClaimDetailsNavigator> {

    public ObservableField<String> insurer = new ObservableField<>();

    public ObservableField<String> coverType = new ObservableField<>();

    public ObservableField<String> claimNo = new ObservableField<>();

    public ObservableField<String> polNo = new ObservableField<>();

    public ObservableField<String> dateSubmitted = new ObservableField<>();

    public ObservableField<String> incidentDate = new ObservableField<>();

    public ObservableField<String> status = new ObservableField<>();

    public ObservableField<String> vehicle = new ObservableField<>();

    public ObservableField<String> description = new ObservableField<>();

    public ClaimDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void setClaimData(ClaimsResponse claimData) {

        insurer.set(claimData.getCmbAgentName());

        coverType.set(claimData.getCmbCovtShtDesc());

        claimNo.set(claimData.getCmbClaimNo());

        polNo.set(claimData.getCmbPolicyNo());

        dateSubmitted.set(claimData.getCmbClaimDate());

        incidentDate.set(claimData.getCmbLossDateTime());

        status.set(claimData.getCmbClaimStatus());

        description.set(claimData.getCmbLossDesc());

        vehicle.set(claimData.getCmbPropertyId());
    }
}
