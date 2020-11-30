package com.turnkeyafrica.bankassurance.ui.confirmclaimdetails;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface ConfirmClaimDetailsNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void submitClaim();

    void openDashboard();
}
