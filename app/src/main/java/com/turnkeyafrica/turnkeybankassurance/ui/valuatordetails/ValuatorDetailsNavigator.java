package com.turnkeyafrica.turnkeybankassurance.ui.valuatordetails;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface ValuatorDetailsNavigator {

    void handleError(LocalError error);

    void openDashboard();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
