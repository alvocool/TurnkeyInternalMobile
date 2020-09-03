package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicledatedialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface VehiclesDateDialogcallback {

    void close();

    void handleError(LocalError error);

    void setDate();
}
