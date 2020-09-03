package com.turnkeyafrica.turnkeybankassurance.ui.vehiclevaluation;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface VehicleValuationNavigator {

    void handleError(LocalError error);

    void uploadValuation();

    void pictureOptions();

    void removePhoto();

    void dismissUploadDialog();

    void choosePhoto();

    void takePhoto();

    void exitDialog();
}
