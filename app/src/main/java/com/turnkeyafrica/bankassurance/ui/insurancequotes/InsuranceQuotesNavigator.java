package com.turnkeyafrica.bankassurance.ui.insurancequotes;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface InsuranceQuotesNavigator {

    void handleError(LocalError error);

    void setAdapter(List<InsuranceQuoteResponce> mInsuranceQuoteResponcesList);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
