package com.turnkeyafrica.turnkeybankassurance.ui.claimdetails;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface ClaimDetailsNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

}
