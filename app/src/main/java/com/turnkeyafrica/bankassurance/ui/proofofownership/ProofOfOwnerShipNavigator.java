package com.turnkeyafrica.bankassurance.ui.proofofownership;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface ProofOfOwnerShipNavigator {

    void handleError(LocalError error);

    void nextStep();

    void pictureOptions();

    void removePhoto();

    void choosePhoto();

    void takePhoto();

    void dismissUploadDialog();

    void exitDialog();
}
