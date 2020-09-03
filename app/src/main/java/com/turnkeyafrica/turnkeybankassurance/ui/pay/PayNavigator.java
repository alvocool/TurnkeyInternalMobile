package com.turnkeyafrica.turnkeybankassurance.ui.pay;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.MpesaCheckModel;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface PayNavigator {

    void handleError(LocalError error);

    void paymentSuccessful(String policyNo,String batchNo);

    AlertDialog openLoading(String message);

    void closeLoading(AlertDialog alertDialog);

    void checkMpesaPayment(MpesaCheckModel mpesaCheckModel);

    void completePayment();

    void convertQuote();
}
