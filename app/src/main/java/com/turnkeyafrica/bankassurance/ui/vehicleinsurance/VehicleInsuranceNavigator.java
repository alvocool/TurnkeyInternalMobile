package com.turnkeyafrica.bankassurance.ui.vehicleinsurance;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.CoverTypesResponce;
import com.turnkeyafrica.bankassurance.data.model.api.ExtraBenefitsResponse;
import com.turnkeyafrica.bankassurance.data.model.api.VehicleMakesResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface VehicleInsuranceNavigator {

    void submitVehicleDetails();

    void handleError(LocalError error);

    void setAdapter(List<ExtraBenefitsResponse> extraBenefitsResponses);

    void showInsuranceTypes(List<CoverTypesResponce> coverTypesResponcesList);

    void showModels(List<VehicleMakesResponce> vehicleMakesResponceList);

    void showDatePicker();

    void getVehicleMakes();

    void getSclCode();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
