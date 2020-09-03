package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehiclemodelsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface VehicleModelsDialogcallback {

    void close();

    void handleError(LocalError error);


}
