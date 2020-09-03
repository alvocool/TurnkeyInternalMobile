package com.turnkeyafrica.turnkeybankassurance.ui.createclaim;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;

public class CreateClaimItemViewModel {

    public final ObservableField<String> inceptionDate = new ObservableField<>();

    public final ObservableField<String> status = new ObservableField<>();

    public final ObservableField<String> Insurer = new ObservableField<>();

    public final ObservableField<String> coverType = new ObservableField<>();

    public final ObservableField<String> vehicleMake = new ObservableField<>();

    public final ObservableField<String> vehiclePlate = new ObservableField<>();

    private PolicyResponce policyResponse;

    private CreateClaimItemNavigator mCreateClaimItemNavigator;


    public CreateClaimItemViewModel(PolicyResponce policyResponse, CreateClaimItemNavigator listener) {
        this.inceptionDate.set("Inception: " + policyResponse.getPolicyCoverFrom());
        this.status.set(policyResponse.getCurrentStatus());
        this.Insurer.set(policyResponse.getPolWebBindName());
        if(policyResponse.getRisks().isEmpty()){
            this.coverType.set("n/a");
        }else {
            if (CommonUtils.StringIsEmpty(policyResponse.getRisks().get(0).getCovtShtDesc())){
                this.coverType.set("n/a");
            }else{
                this.coverType.set(policyResponse.getRisks().get(0).getCovtShtDesc());
            }

            if(CommonUtils.StringIsEmpty(policyResponse.getRisks().get(0).getVehicleMake())){
                this.vehicleMake.set("n/a");
            }else {
                this.vehicleMake.set(policyResponse.getRisks().get(0).getVehicleMake());
            }

            if(CommonUtils.StringIsEmpty(policyResponse.getRisks().get(0).getPropertyId())){
                this.vehiclePlate.set("n/a");
            }else {
                this.vehiclePlate.set(policyResponse.getRisks().get(0).getPropertyId());
            }
        }
        this.policyResponse = policyResponse;
        mCreateClaimItemNavigator = listener;
    }

    public void createClaim(){
        mCreateClaimItemNavigator.createClaim(policyResponse);
    }

    public interface CreateClaimItemNavigator {

        void createClaim(PolicyResponce policyResponce);
    }
}

