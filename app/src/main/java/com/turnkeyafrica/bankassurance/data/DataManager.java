package com.turnkeyafrica.bankassurance.data;

import com.turnkeyafrica.bankassurance.data.local.prefs.PreferencesHelper;
import com.turnkeyafrica.bankassurance.data.remote.ApiHelper;

public interface DataManager extends PreferencesHelper, ApiHelper {

    void setUserAsLoggedOut();

    void updateApiHeader(String userId, String accessToken, String uuid);

    void updateProtectedLoginApiHeader(String uuid);

    void clearComparisonRequest();

    void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String mobileNumber,
            String clientId,
            String email);


    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(1);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
