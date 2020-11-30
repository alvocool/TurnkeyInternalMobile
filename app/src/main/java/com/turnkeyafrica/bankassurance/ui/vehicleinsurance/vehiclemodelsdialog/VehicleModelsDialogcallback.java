package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehiclemodelsdialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface VehicleModelsDialogcallback {

    void close();

    void handleError(LocalError error);


}
