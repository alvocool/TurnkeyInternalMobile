package com.turnkeyafrica.turnkeybankassurance.ui.profile;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientDetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface ProfileNavigator {

    void handleError(LocalError error);

    void editProfile();

    void setClientDetails(ClientDetailsResponce profileDetails);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
