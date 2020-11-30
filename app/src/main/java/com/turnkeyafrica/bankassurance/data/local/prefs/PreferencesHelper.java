package com.turnkeyafrica.bankassurance.data.local.prefs;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsDetailsObject;
import com.turnkeyafrica.bankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.data.model.api.QouteResponce;
import com.turnkeyafrica.bankassurance.data.model.others.MiscData;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserEmail();

    void setUUID(String UUID);

    String getUUID();

    void setCurrentUserEmail(String email);

    String getCurrentUserMobileNumber();

    void setInsuranceQuote(String insuranceQuote);

    String getInsuranceQuote();

    void setCurrentUserMobileNumber(String mobileNumber);

    String getCurrentUserId();

    void setCurrentUserId(String userId);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    void setCurrentClientId(String clientId);

    String getCurrentClientId();

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    void setComparisonRequest(ComparisonRequest comparisonRequest);

    void clearComparisonRequest();

    String getComparisonRequest();

    void setQuoteResponce(QouteResponce quoteResponce);

    String getQuoteResponce();

    void setPolicyResponse(PolicyResponce policyResponse);

    String getPolicyResponse();

    String getClaimsDetailsObject();

    void setClaimsDetailsObject(ClaimsDetailsObject claimsDetailsObject);

    String getMiscData();

    void setMiscData(MiscData miscData);

    void setNotificationToken(boolean state);

    Boolean getNotificationToken();
}
