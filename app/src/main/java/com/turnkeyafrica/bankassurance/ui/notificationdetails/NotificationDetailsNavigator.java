package com.turnkeyafrica.bankassurance.ui.notificationdetails;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

interface NotificationDetailsNavigator {
    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void handleError(LocalError error);
}
