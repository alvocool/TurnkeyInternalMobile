package com.turnkeyafrica.bankassurance.ui.securityquestion;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface SecurityQuestionNavigator {

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void handleError(LocalError error);

    void verify();

    void Continue(Boolean response);

    void showContacts();
}
