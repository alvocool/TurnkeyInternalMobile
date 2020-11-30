package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface VehicleInsuranceTypecallback {

    void close();

    void handleError(LocalError error);


}
