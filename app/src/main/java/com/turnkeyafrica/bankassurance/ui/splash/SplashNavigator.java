package com.turnkeyafrica.bankassurance.ui.splash;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface SplashNavigator {

    void openLoginActivity();

    void openDashBoardActivity();

    void handleError(LocalError error);

}

