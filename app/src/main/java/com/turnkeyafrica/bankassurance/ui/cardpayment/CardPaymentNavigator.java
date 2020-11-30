package com.turnkeyafrica.bankassurance.ui.cardpayment;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.CardDetailsRequest;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface CardPaymentNavigator {

    void handleError(LocalError error);

    boolean verifyValues();

    void showDialogType(int dialogOption);

    void paymentSuccessful(String policyNo,String batchNo);

    CardDetailsRequest getCardDetails();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

}
