package com.turnkeyafrica.turnkeybankassurance.ui.paymentsuccessful;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface PaymentSuccessfulNavigator {

    void handleError(LocalError error);

    void openDashBoard();

    void findValuer();

    void uploadValuation();

    void certificatePickup();
}
