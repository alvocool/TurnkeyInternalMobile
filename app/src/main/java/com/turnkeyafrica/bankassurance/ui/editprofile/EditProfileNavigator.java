package com.turnkeyafrica.bankassurance.ui.editprofile;


import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface EditProfileNavigator {

    void handleError(LocalError error);

    void updateProfile();

}
