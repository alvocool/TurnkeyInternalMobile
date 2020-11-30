package com.turnkeyafrica.bankassurance.ui.claimdetails;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface ClaimDetailsNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

}
