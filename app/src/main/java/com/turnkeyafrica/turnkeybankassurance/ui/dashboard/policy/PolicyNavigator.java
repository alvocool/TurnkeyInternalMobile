package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

import java.util.List;

public interface PolicyNavigator {

    void handleError(LocalError error);

    void setAdapter(List<PolicyResponce> policyResponces, List<InsuranceQuoteResponce> qouteResponces);

    void getInsured();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
