package com.turnkeyafrica.bankassurance.ui.qoutedetails;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface QuoteDetailsNavigator {

    void buyQuote();

    void saveQuote();

    void  handleError(LocalError error);

    void setAdapter(List<DetailsResponce> limits, List<DetailsResponce> excess);

    void openDashboard();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

}
