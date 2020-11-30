package com.turnkeyafrica.bankassurance.ui.confirmandpay;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.MpesaRequest;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.math.BigDecimal;

public interface ConfirmAndPayNavigator {

    void handleError(LocalError error);

    void payNow();

    void showPaymentModes();

    void setPaymentOption(int option);

    void mpesaPayment(MpesaRequest mpesaRequest, BigDecimal quoteCode);

    void editQuoteDetails();

    void showTermsAndConditions();

    AlertDialog openLoading(String message);

    void closeLoading(AlertDialog alertDialog);
}
