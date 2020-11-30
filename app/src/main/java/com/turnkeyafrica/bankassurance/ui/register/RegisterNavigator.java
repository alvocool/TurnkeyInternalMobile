package com.turnkeyafrica.bankassurance.ui.register;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface RegisterNavigator {

    void registerCustomer();

    void handleError(LocalError error);

    void showTermsAndConditions();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

}
