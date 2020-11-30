package com.turnkeyafrica.bankassurance.ui.paymentsuccessful;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface PaymentSuccessfulNavigator {

    void handleError(LocalError error);

    void openDashBoard();

    void findValuer();

    void uploadValuation();

    void certificatePickup();
}
