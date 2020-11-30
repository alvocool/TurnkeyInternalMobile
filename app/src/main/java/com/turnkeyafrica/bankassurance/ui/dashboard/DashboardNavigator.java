package com.turnkeyafrica.bankassurance.ui.dashboard;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface DashboardNavigator {

    void openLoginActivity();

    void viewProfile();

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void setNotificationCount(int size);

}
