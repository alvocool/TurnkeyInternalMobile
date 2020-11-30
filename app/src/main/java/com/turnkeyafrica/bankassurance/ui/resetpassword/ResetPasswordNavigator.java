package com.turnkeyafrica.bankassurance.ui.resetpassword;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface ResetPasswordNavigator {

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void setPassword();

    void handleError(LocalError error);

    void success(Boolean response);
}
