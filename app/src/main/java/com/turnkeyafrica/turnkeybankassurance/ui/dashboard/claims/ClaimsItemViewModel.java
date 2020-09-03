package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClaimsResponse;

public class ClaimsItemViewModel {

    private ClaimsResponse claimsResponse;

    public final ObservableField<String> claimDate = new ObservableField<>();

    public final ObservableField<String> status = new ObservableField<>();

    public final ObservableField<String> claimInsurer = new ObservableField<>();

    public final ObservableField<String> coverType = new ObservableField<>();

    public final ObservableField<ClaimsResponse> claim = new ObservableField<>();

    private final ClaimsItemListener claimsItemNavigator;

    public ClaimsItemViewModel(ClaimsResponse claim, ClaimsItemListener claimsItemNavigator) {
        this.claimInsurer.set(claim.getCmbAgentName());
        this.claimDate.set(claim.getCmbClaimDate());
        this.status.set(claim.getCmbClaimStatus());
        this.coverType.set(claim.getCmbCovtShtDesc());
        this.claim.set(claim);
        this.claimsItemNavigator = claimsItemNavigator;
        this.claimsResponse = claim;
    }

    public void openClaimDetails(){
        claimsItemNavigator.openClaimsDetails(claimsResponse);
    }
    public interface ClaimsItemListener{
        void openClaimsDetails(ClaimsResponse claimsResponse);
    }

}
