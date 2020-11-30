package com.turnkeyafrica.bankassurance.ui.policydetails;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface PolicyDetailsNavigator {

   void  handleError(LocalError error);

   void setAdapter(List<DetailsResponce> limits, List<DetailsResponce> excess);

   AlertDialog openLoading();

   void closeLoading(AlertDialog alertDialog);
}
