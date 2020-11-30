package com.turnkeyafrica.bankassurance.ui.dashboard.claims;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.ClaimsResponse;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface ClaimsNavigator {

    void handleError(LocalError error);

    void setAdapter(List<ClaimsItemViewModel> claimsItemViewModelList);

    AlertDialog openLoading();

    void createClaim();

    void closeLoading(AlertDialog alertDialog);

    void openClaimsDetails(ClaimsResponse claimsResponse);
}
