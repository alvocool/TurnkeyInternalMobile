package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface VehicleInsuranceTypecallback {

    void close();

    void handleError(LocalError error);


}
