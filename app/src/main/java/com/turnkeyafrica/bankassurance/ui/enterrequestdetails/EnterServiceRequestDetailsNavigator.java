package com.turnkeyafrica.bankassurance.ui.enterrequestdetails;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.ServiceRequestType;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface EnterServiceRequestDetailsNavigator {
  
    void pictureOptions();

    void removePhoto();

    void choosePhoto();

    void takePhoto();

    void submitRequest();

    void showRequestCategories();

    void handleError(LocalError error);

    void closeLoading(AlertDialog alertDialog);

    AlertDialog openLoading();

    void setRequestTypes(List<ServiceRequestType> requestTypes);

    void showPropertyId();

    void openDashboard();
}
