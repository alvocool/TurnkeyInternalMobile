package com.turnkeyafrica.turnkeybankassurance.ui.confirmclaimdetails;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface ConfirmClaimDetailsNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void submitClaim();

    void openDashboard();
}
