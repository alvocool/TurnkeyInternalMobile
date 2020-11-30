package com.turnkeyafrica.bankassurance.ui.createrequest;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface CreateRequestNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void setAdapter(List<CreateRequestItemViewModel> createRequestItemViewModels);

    void openCreateRequest(PolicyResponce policyResponce);
}
