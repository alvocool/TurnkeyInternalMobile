package com.turnkeyafrica.turnkeybankassurance.data;

import com.turnkeyafrica.turnkeybankassurance.data.local.prefs.PreferencesHelper;
import com.turnkeyafrica.turnkeybankassurance.data.remote.ApiHelper;

public interface DataManager extends PreferencesHelper, ApiHelper {

    void setUserAsLoggedOut();

    void updateApiHeader(Long userId, String accessToken);

    void updateProtectedLoginApiHeader(String uuid);

    void clearComparisonRequest();

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String mobilenumber,
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
