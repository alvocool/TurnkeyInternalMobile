package com.turnkeyafrica.turnkeybankassurance.ui.otp;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface OtpNavigator {

    void handleError(LocalError error);

    void Login();

    void openDashBoardActivity();

    void openLoginActivity();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
