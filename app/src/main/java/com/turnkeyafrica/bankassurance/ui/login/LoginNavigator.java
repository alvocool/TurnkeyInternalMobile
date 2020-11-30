package com.turnkeyafrica.bankassurance.ui.login;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.LoginUserRequest;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface LoginNavigator {

    void handleError(LocalError error);

    void launchOtp();

    void registerNew();

    void showTermsAndConditions();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void openOtp(LoginUserRequest request);

    void forgotPassword();
}
