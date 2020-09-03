package com.turnkeyafrica.turnkeybankassurance.ui.register;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface RegisterNavigator {

    void registerCustomer();

    void handleError(LocalError error);

    void launchOtp(String clntNumber);

    void showTermsAndConditions();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

}
