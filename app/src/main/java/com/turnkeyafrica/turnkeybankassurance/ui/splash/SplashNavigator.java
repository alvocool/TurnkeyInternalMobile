package com.turnkeyafrica.turnkeybankassurance.ui.splash;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface SplashNavigator {

    void openLoginActivity();

    void openDashBoardActivity();

    void handleError(LocalError error);

}

