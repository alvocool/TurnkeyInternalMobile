package com.turnkeyafrica.turnkeybankassurance.ui.createrequest;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;

public class CreateRequestItemViewModel {

    public final ObservableField<String> inceptionDate = new ObservableField<>();

    public final ObservableField<String> status = new ObservableField<>();

    public final ObservableField<String> Insurer = new ObservableField<>();

    public final ObservableField<String> coverType = new ObservableField<>();

    private PolicyResponce policyResponse;

    private CreateClaimItemNavigator mCreateClaimItemNavigator;


    public CreateRequestItemViewModel(PolicyResponce policyResponse, CreateClaimItemNavigator listener) {
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
        }
        this.policyResponse = policyResponse;
        mCreateClaimItemNavigator = listener;
    }

    public void createRequest(){
        mCreateClaimItemNavigator.createRequest(policyResponse);
    }

    public interface CreateClaimItemNavigator {

        void createRequest(PolicyResponce policyResponce);
    }
}

