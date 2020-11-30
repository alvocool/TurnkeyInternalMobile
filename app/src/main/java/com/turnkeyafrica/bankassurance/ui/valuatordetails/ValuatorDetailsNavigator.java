package com.turnkeyafrica.bankassurance.ui.valuatordetails;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface ValuatorDetailsNavigator {

    void handleError(LocalError error);

    void openDashboard();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
