package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicledatedialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface VehiclesDateDialogcallback {

    void close();

    void handleError(LocalError error);

    void setDate();
}
