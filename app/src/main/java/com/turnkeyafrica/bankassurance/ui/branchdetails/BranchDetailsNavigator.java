package com.turnkeyafrica.bankassurance.ui.branchdetails;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface BranchDetailsNavigator {

    void handleError(LocalError error);

    void openPaymentSuccessFull();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
