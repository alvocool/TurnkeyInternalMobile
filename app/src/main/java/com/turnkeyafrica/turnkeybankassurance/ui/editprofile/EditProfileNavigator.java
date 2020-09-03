package com.turnkeyafrica.turnkeybankassurance.ui.editprofile;


import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface EditProfileNavigator {

    void handleError(LocalError error);

    void updateProfile();

}
