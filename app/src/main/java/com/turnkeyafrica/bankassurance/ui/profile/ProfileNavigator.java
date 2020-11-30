package com.turnkeyafrica.bankassurance.ui.profile;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.ClientDetailsResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface ProfileNavigator {

    void handleError(LocalError error);

    void editProfile();

    void setClientDetails(ClientDetailsResponce profileDetails);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
