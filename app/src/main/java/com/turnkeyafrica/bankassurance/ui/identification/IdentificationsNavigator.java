package com.turnkeyafrica.bankassurance.ui.identification;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface IdentificationsNavigator {

    void handleError(LocalError error);

    void proceedToPayment();

    void pictureOptions();

    void removePhoto();

    void dismissUploadDialog();

    void choosePhoto();

    void takePhoto();

    void exitDialog();
}
