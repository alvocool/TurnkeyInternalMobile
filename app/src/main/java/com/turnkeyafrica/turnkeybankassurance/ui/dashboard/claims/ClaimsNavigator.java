package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClaimsResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

import java.util.List;

public interface ClaimsNavigator {

    void handleError(LocalError error);

    void setAdapter(List<ClaimsItemViewModel> claimsItemViewModelList);

    AlertDialog openLoading();

    void createClaim();

    void closeLoading(AlertDialog alertDialog);

    void openClaimsDetails(ClaimsResponse claimsResponse);
}
