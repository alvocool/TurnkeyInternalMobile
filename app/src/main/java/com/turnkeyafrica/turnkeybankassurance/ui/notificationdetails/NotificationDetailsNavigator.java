package com.turnkeyafrica.turnkeybankassurance.ui.notificationdetails;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

interface NotificationDetailsNavigator {
    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void handleError(LocalError error);
}
