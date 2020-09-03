package com.turnkeyafrica.turnkeybankassurance.ui.policydetails;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

import java.util.List;

public interface PolicyDetailsNavigator {

   void  handleError(LocalError error);

   void setAdapter(List<DetailsResponce> limits, List<DetailsResponce> excess);

   AlertDialog openLoading();

   void closeLoading(AlertDialog alertDialog);
}
