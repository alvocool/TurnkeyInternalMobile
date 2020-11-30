package com.turnkeyafrica.bankassurance.ui.successstep;

import android.app.AlertDialog;

public interface SuccessStepNavigator {

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void login();
}
