package com.turnkeyafrica.turnkeybankassurance.ui.login;


import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface LoginNavigator {

    void handleError(LocalError error);

    void launchOtp();

    void registerNew();

    void showTermsAndConditions();

}
