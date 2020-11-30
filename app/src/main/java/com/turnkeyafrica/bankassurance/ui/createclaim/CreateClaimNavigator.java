package com.turnkeyafrica.bankassurance.ui.createclaim;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import java.util.List;

public interface CreateClaimNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void setAdapter(List<CreateClaimItemViewModel> createClaimItemViewModels);

    void openCreateClaim(PolicyResponce policyResponce);
}
