package com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface EnterClaimDetailsNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void showDatePicker();

    void showVehicleRegs();

    void pictureOptions(int option);

    void removePhoto(int option);

    void takePhoto(String name);

    void choosePhoto();

    void verifyClaimDetails();
}
