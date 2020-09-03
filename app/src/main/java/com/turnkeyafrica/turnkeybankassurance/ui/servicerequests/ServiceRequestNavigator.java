package com.turnkeyafrica.turnkeybankassurance.ui.servicerequests;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

import java.util.List;

public interface ServiceRequestNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void openServiceRequestQuestion();

    void setAdapter(List<ServiceRequest> serviceRequests);
}
