package com.turnkeyafrica.turnkeybankassurance.ui.identification;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

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
